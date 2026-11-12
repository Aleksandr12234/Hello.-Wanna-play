# Коллизии

## BasicBody
Предполагается, что при создании коллизии нужно наследовать от этого тела

```java
    @Getter
public class BasicBody<T extends BasicObject> extends Module<T> {
    protected BodyWrapper body; //Тело
    
    public void beginCollision(BasicBody<?> other){} //Когда сталкивается с объектом коллизией
    public void endCollision(BasicBody<?> other){} //Когда столкновение с объектом коллизий закончено
    public void beginTrigger(BasicBody<?> other){} //Когда соприкасается с триггером
    public void endTrigger(BasicBody<?> other){} //Когда пересечение с триггером заканчивается
}
```

### BodyWrapper
Просто класс Интерфейс для адаптации под структуру, пока такой

```java
public class BodyWrapper {
    public final Body body; //Тело
    public void setFixtureDef(FixtureDef fixtureDef){}//Задать форму тела
}
```

## BodyCategories
Класс для более правильного разделения коллизии

```java
public class BodyCategories {
    public void addCategory(String name){} //Добавить категорию
    public Short getCategory(String name){} //Удалить категорию
}
```

## Настройки; классы строители для тела, значения по умолчанию

Пример:
```
new Builder().build();
```

```java
public class BodyDefBuilder {
    public BodyDefBuilder(){
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //        BodyDef.BodyType.DynamicBody - динамическое тело
        //        BodyDef.BodyType.KinematicBody - Как статическое, но может двигаться (движущаяся платформа)
        //        BodyDef.BodyType.StaticBody - Статическое тело
        bodyDef.fixedRotation = false;//если True - тело не будет крутиться
        bodyDef.bullet = false;//Непрерывный просчет коллизий, для быстрых объектов
        bodyDef.linearDamping = 0.5f; // Гашение линейной скорости
        bodyDef.angularDamping = 0.5f; // Гашение вращения
    }
}
```

```java
public class FixtureDefBuilder {
    public FixtureDefBuilder(){
        fixtureDef.restitution = 0.5f; //упругость
        fixtureDef.friction = 0.5f; //Трение
        fixtureDef.density = 0.5f;//Плотность (влияет на массу объекта)
        fixtureDef.isSensor=false;//Если true, то объект просто триггер (setTrigger)
    }

    public FixtureDefBuilder setShape(Shape shape){}//Задать фигуру

    public FixtureDefBuilder setMyCategory(String name){}//Категория коллизии
    public FixtureDefBuilder setOtherCategory(String... names){}//С кем взаимодействует
    
}
```

### Фигуры

```java
//Круг
public class CircleShapeBuilder {
    public CircleShapeBuilder setRadius(float value){} //Радиус (1 по умолчанию)
    public CircleShapeBuilder setLocalPosition(float x, float y){}//Сдвиг
}
```

```java
//Линия
public class LineShapeBuilder {
    public LineShapeBuilder setCoordinates(float x1, float y1, float x2, float y2){} //Координаты линии
}
```

```java
//Прямоугольник
public class RectShapeBuilder {
    public RectShapeBuilder setLocalSize(float width, float height){}//Размер
    public RectShapeBuilder setLocalPosition(float x, float y){}//Позиция (относительно body)
    public RectShapeBuilder setLocalAngle(float angle){}//Угл (относительно body)
}

```
