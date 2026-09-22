# Structure

Тут будет расписано только то, с чем взаимодействуем (остальное не трогать).
Тут нет вещей которые уже есть в документации LibGDX.

## Создание проекта
1) Копируем данную структуру с репозитория командой:
```
git clone https://github.com/Aleksandr12234/Structure
```

2) В файле build.gradle меняем следующие две строки:
```groovy
//Меняем Structure на нужное имя
ext.appName = 'Structure'

eclipse.project.name = 'Structure' + '-parent'
```

3) Изменить репозиторий структуры на собственный
```
git remote set-url origin <URL_нового_репозитория>
```

4) Проект лучше делать в директории "Project"

## Lwjgl3Launcher
```java
//Все настройки приложения тут
private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
    Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
    
    //тут менять имя проекта
    configuration.setTitle("Structure");
    
    //тут менять частоту кадров
    configuration.setForegroundFPS(60);

    //менять размер окна (верхний - полный экран)
    //configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
    configuration.setWindowedMode(800, 500);

    // Смена иконок в lwjgl3/src/main/resources/ .
    configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
    
    return configuration;
}
```

## Stock
```java
private static TaskRunner taskRunner; //Тут все "рычаги" управления (доступ через геттер)

public Stock(ServiceObjects serviceObjects){
    taskRunner = new TaskRunner(serviceObjects);
    
    //Тут установить начальную сцену
    taskRunner.scenesManager.changeActiveScene(new TestScene());
}
```

### TaskRunner
```java
public final ServiceObjects serviceObjects; //Текст, фигуры и камера
public final UserInput userInput=new UserInput(); //Ввод пользователя
public final AssetsLoader assetsLoader =new AssetsLoader(); //Загрузчик текстур и звука
public final ScenesManager scenesManager=new ScenesManager(); //Работа со сценами
public final Drawer drawer=new Drawer(); //Работа с выводом на экран
```

#### ServiceObjects
```java
public SpriteBatch batch; //нужен в некоторых случаях для отрисовки
public OrthographicCamera camera; //Для работы с камерой, Обязательно после работы вызывать функцию camera.update()
public ExtendViewport viewport;// окно просмотра. По умолчанию 8x5 единиц 
public ShapeDrawer shapeDrawer; //Для отрисовки фигур
public BitmapFont font; //Отрисовка текста
public void setFont(String path); //Изменить шрифт
```

#### UserInput
```java
//Как аругменты i принимает клавиши из com.badlogic.gdx.Input (интерфейс Input)
public boolean getKeysPressed(int i); //Нажата ли кнопка (Один кадр)
public boolean getKeysClamped(int i); //Зажата ли кнопка
public boolean getKeysReleased(int i); //Отпущена ли кнопка (один кадр)
public boolean getMousePressed(int i); //Нажата ли кнопка мыши (один раз при нажатии)
public boolean getMouseClamped(int i); // зажата ли кнопка мыши
public boolean getMouseReleased(int i); //Отпущена ли i-тая кнопка мыши (Один раз при отпускании)
public Vector3 getMousePosition(); //Позиция мыши (unproject вызван)
```

#### AssetsLoader
Все пути указывать так, словно исходят из assets, например "test/libgdx.png"

```java
public final SpriteListLoader spriteList =new SpriteListLoader();//Загрузка спрайт листов
public final MusicLoader music=new MusicLoader();//Загрузка музыки (длинных звуковых файлов)
public final SoundLoader sound=new SoundLoader();//Загрузка звука (коротких звуковых файлов)
public final TextureLoader texture=new TextureLoader();//Загрузка текстур
```

#### ScenesManager
```java
public EmptyScene activeScene;//Активная сцена
public void changeActiveScene(EmptyScene newScene);//Сменить активную сцену
```

#### drawer
```java
public PostProcessor postProcessor;//Пост обработка (по умолчанию выключена)
public void setBackgroundColor(float r, float g, float b);//Задать цвет заднего фона
public void setDrawCollision(boolean drawCollision);//Рисовать ли коллизии
```

##### Пост обработка

Взята с [этого репозитория](https://github.com/Anuken/gdx-postprocessing)

Пример как создавать эффект:
```java
Bloom bloom = new Bloom( (int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f) );
postProcessor.addEffect( bloom );
```

Эффекты:
- Antialiasing.java - базовое сглаживание пиксельных «лесенк» на краях объектов.
- Bloom.java - эффект свечения ярких областей сцены (эффект «неонового» или солнечного сияния).
- CameraMotion.java - размытие изображения при быстром движении или повороте игровой камеры.
- CrtMonitor.java - симуляция старого ЭЛТ-телевизора или монитора с характерными горизонтальными полосами сканирования.
- Curvature.java - искажение экрана, изгибающее изображение по краям («эффект выпуклого рыбьего глаза» или пузатого монитора).
- Fxaa.java (Fast Approximate Anti-Aliasing) - быстрый алгоритм сглаживания, работающий прямо по пикселям готового кадра.
- LensFlare.java / LensFlare2.java - симуляция бликов на линзе виртуальной камеры от ярких источников света.
- Light.java - простейшее наложение карт освещения, виньетирования света или базовых световых масок на сцену.
- MotionBlur.java - классическое размытие в движении для динамичных объектов или при быстром перемещении.
- Nfaa.java (Normal Filter Anti-Aliasing) - альтернативный алгоритм сглаживания краев, ориентирующийся на контрастность соседних пикселей.
- Vignette.java - затемнение изображения ближе к углам экрана.
- Zoomer.java - эффект радиального размытия от центра к краям при резком приближении или взрыве.


## Сцены
Все сцены наследуются от EmptyScene
```java
public abstract class EmptyScene {
    public HashMap<String, Layer> layers;//Слои, по умолчанию всё добавляется в слой "Basic"
    public abstract void setup();//Именно тут нужно создавать и дальше работать с объектами
    public void addObject(BasicObject object);//Добавить объект, имеет перегрузки
    public void removeObject(BasicObject object);//Удалить объект
    public void moveObjectToLayer(BasicObject object, String layer);//Сменить слой объекта
}
```
Сцены работают со слоями, слой:
```java
public class Layer {
    public List<BasicObject> objects=new ArrayList<>();//объекты слоя
    public byte number=0;//порядок слоя (чем меньше тем раньше вызовет отрисовку и логику объекта)
    public boolean save=false;//Если true, то слой не удалиться после удаления всех объектов в нём

    public void changeActive(boolean isActive, boolean isDraw);
    //если поставить isActive false - у объектов не будет выполнятся логика
    //если поставить isDraw false - объекты не будут отрисовываться

    public void addChildLayer(String layerName);//Добавить дочерний слой
    public void removeChildLayer(String layerName);//Удалить дочерний слой
}
```

## Объекты
Все наследуются от `BasicObject`

Если в таком объекте понадобиться модуль, то использовать `Module<>()`

```java
public class BasicObject {
    public static float deltaTime;//Время между кадрами
    protected StatusInfo status=new StatusInfo();//Хранит состояние объекта, доступ через геттер
    protected Transform transform = new Transform();//Хранит позицию и размер объекта, доступ через геттер

    public void setup();//Вызывается один раз после добавления объекта в сцену
    public void doLogic();//Выполняется до проверок коллизий и отрисовки
    public void draw();//Отрисовка объекта
    public void onDispose(){}//При удалении объекта
}
```
Если в таком объекте понадобиться модуль, то можно использовать `Module<>()`
```java
public class Module<T extends BasicObject> extends BasicObject {
    private final T master;//Объект "родитель", доступ через геттер
    public final Transform localTransform=new Transform();//Изменения относительно родителя
    public void doLogic();//Обновление глобальной позиции модуля по умолчанию
}
```
 
## Коллизии

Для работы с коллизиями нужно создать объект на основе:

LineBody - линия, по умолчанию не делает ничего при столкновениях 

RectBody - прямоугольник, по умолчанию не делает ничего при столкновениях

RectPhysicalBody - прямоугольник, по умолчанию отталкивает другие физические объекты

Если нужна своя реализация, что делать при столкновениях, то нужно создать дочерний от необходимого тела объект и 
В одну из функций `action();` добавить свою реализацию

## Анимации
Класс Animator:

```java

public class Animator<T extends BasicObject> extends Module<T>{
    public float timeOnFrame=0.2f;//Время на один кадр
    @Override
    public void draw();//Отрисовка анимации
    public void reset();//Вернуть к первому кадру
}
```
