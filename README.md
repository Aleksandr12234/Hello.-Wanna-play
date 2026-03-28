# Structure

Тут будет рассписано только то, с чем взаимодейсвуем (остальное не трогать)

## Lwjgl3Launcher
```java
//Все настройки приложения тут
private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
    Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
    
    //тут менять имя если что
    configuration.setTitle("Discovery of the end");
    
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

## ServiceObjects
```java
public SpriteBatch batch; //Если понадобиться
public OrthographicCamera camera; //Для работы с камерой, Обязательно после работы вызывать функцию camera.update()
public ShapeDrawer shapeDrawer; //Для отрисовки фигур
public BitmapFont font; //Отрисовка текста
public void setFont(String path); //Изменить шрифт
```

## Stock
```java
private static TaskRunner taskRunner; //Тут всё что нужно

public Stock(ServiceObjects serviceObjects){
    taskRunner = new TaskRunner(serviceObjects);
    
    //Тут изменить начальную сцену
    taskRunner.scenesManager.changeActiveScene(new TestScene());
}
```

## TaskRunner
```java
public final ServiceObjects serviceObjects; //Текст, фигуры и камера
public final UserInput userInput=new UserInput(); //Ввод пользователя
public final TexturesLoader texturesLoader=new TexturesLoader(); //Загрузчик текстур
public final UpdateCollisions updateCollisions=new UpdateCollisions(); //Проверить коллизии
public final ScenesManager scenesManager=new ScenesManager(); //Работа со сценами
public final Drawer drawer=new Drawer(); //Отрисовка (можно поменять цвет и добавить отрисовку колайдеров)
```

### UserInput
```java
//Как аругменты i принемает клавиши из com.badlogic.gdx.Input (интерфейс Input)
public boolean getKeysPressed(int i); //Нажата ли кнопка (Один кадр)
public boolean getKeysClamped(int i); //Зажата ли кнопка
public boolean getKeysReleased(int i); //Отпущена ли кнопка (один кадр)
public boolean getMousePressed(int i); //Нажата ли кнопка мыши (один раз при нажатии)
public boolean getMouseClamped(int i); // зажата ли кнопка мыши
public boolean getMouseReleased(int i); //Отпущена ли i-тая кнопка мыши (Один раз при отпускании)
public Vector3 getMousePosition(); //Позиция мыши (unproject вызван)
```

### TextureLoader
```java
public Texture loadTexture(String path); //Загрузка текстуры из пути
public Music loadMusic(String path); //Загрузка мызыки
public Sound loadSound(String path); //Загрузка звука
```
## Объекты
Все наследуются от `BasicObject`

Если в таком объекте понадобиться модуль, то использовать `Module<>()`

```java
void doLogic(); //Выполняется до проверок колизий и отрисовки
void draw(); //Отрисовка объекта
```
 
## Коллизии

Для работы с коллизиями нужно создать объект на основе:

LineObject - По умолчанию не делает ничего при столкновениях 

NoPhysicalObject - Прямоугольник, по умолчанию не делает ничего при столкновениях

PhysicalObject - Прямоугольник, по умолчанию отталкивает другие физические объекты

Если нужна своя реализация что делать при столкновениях, то нужно создать дочерний от необходимого тела объект и 
В одну из функций `action();` добавить свою реализацию

## Работа со сценами

Все сцены наследуются от EmptyScene

```java
public void setup(){/*Тут Создавать объекты и менять необходимые настройки*/}

public HashMap<String, Layer> layers; //Можно отключить множество объектов

public void addObject(BasicObject object); //Добавить объект в базовый слой
public void addObject(String layer, BasicObject object); //Добавить объект в определённый слой (Создаст новый, сели такого слоя нет)
public void addObject(BasicObject undo, BasicObject object); //Добавить объект после undo 
public void removeObject(BasicObject object); //Удалить объект 
public void moveObjectToLayer(BasicObject object, String layer); //Переместить объект с одного слоя на другой
```

Для доступа к активной сцене используется ScenesManager

```java
public EmptyScene activeScene; //Активная сцена (функции описаны выше)
public void changeActiveScene(EmptyScene newScene); //Поменять активную сцену (Меняется только после обработки кадра)
```

