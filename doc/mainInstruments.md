# Основные инструменты

## Stock
```java
//Тут все "рычаги" управления
public static TaskRunner getTaskRunner(){
    return taskRunner; 
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

[Описание пост обработки](PostProcessing.md)
