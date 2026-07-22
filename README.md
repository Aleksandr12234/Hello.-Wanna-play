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

3) (Необязательно) Создать директорию core.src.main.java.com.one.Project 
и начать в ней работать

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
public final TexturesLoader texturesLoader=new TexturesLoader(); //Загрузчик текстур и звука
public final ScenesManager scenesManager=new ScenesManager(); //Работа со сценами
public final Drawer drawer=new Drawer(); //Отрисовка (можно поменять цвет и добавить отрисовку колайдеров)
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

#### TextureLoader
```java
public Texture loadTexture(String path); //Загрузка текстуры из пути
public Music loadMusic(String path); //Загрузка музыки
public Sound loadSound(String path); //Загрузка звука
```

#### ScenesManager
```java
public EmptyScene activeScene;//Активная сцена
public void changeActiveScene(EmptyScene newScene);//Сменить активную сцену
```

#### drawer
```java
public void setBackgroundColor(float r, float g, float b);//Задать цвет заднего фона
public void setDrawCollision(boolean drawCollision);//Рисовать ли коллизии (полезна для дебага)
```

## Сцены
Все сцены наследуются от EmptyScene
```java
public abstract class EmptyScene {
    public HashMap<String, Layer> layers;//Слои, по умолчанию всё добавляется в слой "Basic"
    public abstract void setup();//Именно тут нужно создавать и дальше работать с объектами
    public void addObject(BasicObject object);//Добавить объект, имеет перегрузки
    public void removeObject(BasicObject object);//Удалить объект
    public void moveObjectToLayer(BasicObject object, String layer);//Сменить слой объекта (не настроено изменение
    // состояния объекта под новый слой)
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
}
```

## Объекты
Все наследуются от `BasicObject`

Если в таком объекте понадобиться модуль, то использовать `Module<>()`

```java
public class BasicObject {
    public static float deltaTime;//Время между кадрами
    protected Transform transform = new Transform();//Хранит состояние объекта, доступ через геттер
    
    public void doLogic();//Выполняется до проверок коллизий и отрисовки
    public void draw();//Отрисовка объекта
    public void onDispose(){}//При удалении объекта
}
```
Если в таком объекте понадобиться модуль, то можно использовать `Module<>()`
```java
public class Module<T extends BasicObject> extends BasicObject {
    private final T master;//Объект "родитель", доступ через геттер
}
```
 
## Коллизии

Для работы с коллизиями нужно создать объект на основе:

LineObject - линия, по умолчанию не делает ничего при столкновениях 

NoPhysicalObject - прямоугольник, по умолчанию не делает ничего при столкновениях

PhysicalObject - прямоугольник, по умолчанию отталкивает другие физические объекты

Если нужна своя реализация, что делать при столкновениях, то нужно создать дочерний от необходимого тела объект и 
В одну из функций `action();` добавить свою реализацию

