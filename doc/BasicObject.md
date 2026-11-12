# Объекты
Если нужно добавить что-то в сцену, то оно должно наследоваться от `BasicObject`

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

## UI
### Текст
Шрифт менять через [ServiceObject](mainInstruments.md)
```java
public class Text extends BasicObject {
    public String text=""; //Какой текст
    public float r, g, b, a; //Цвет
    public float scale=1f; //Размер
    public AnchorPoint anchorPoint = AnchorPoint.CENTER; //Точка "якорь"
}
```

### Кнопка
Все действия задаются через лямбда выражения

```java
public class Button extends BasicObject {
    public ButtonPressed pressed; //выполняется один раз когда нажата
    public ButtonAimed aimed; //выполняется когда кнопка зажата
    public ButtonReleased released; //выполняется один раз, когда отпущена
}
```

## Частицы

### Частица
Все параметры задаются при создании частицы с помощью соответствующих функций.
Функции возвращают сам объект

```java
public class Particle extends BasicObject {
    private float lifeTime; //Время существования

    Sprite sprite=new Sprite(Stock.getTaskRunner().assetsLoader.texture.loadTexture("test/Particle.png"));//спрайт частицы

    public final ParticleCollision collision=new ParticleCollision(this);//Настройка взаимодействия с другими объектами

    public final Vector2 startSpeed=new Vector2();//Начальная скорость
    public final Vector2 endSpeed=new Vector2();//Конечная скорость

    private final Color startColor=new Color(Color.WHITE);//Начальный цвет
    private final Color endColor=new Color(Color.WHITE);//Конечный цвет

    private final Vector2 startSize=new Vector2();//Начальный размер
    private final Vector2 endSize=new Vector2();//Конечный размер
}
```

### Создатель частиц

```java
@Builder
public class ParticleSpawner <T extends BasicObject> extends Module<T> {
    public boolean pause=false;//Пауза в создании
    public float timeToSpawn;//Время между созданием частиц
    public int countOfParticle=1;//Количество вызова spawnFunction, когда прошло время
    public int countOfSpawn=-1;//Количество итераций цикла (прошло время - спавн частицы), "-1" - бесконечно 
    public SpawnFunction spawnFunction;//Предполагается что будет лямбда функция с описанием какую частицу создавать
}
```
