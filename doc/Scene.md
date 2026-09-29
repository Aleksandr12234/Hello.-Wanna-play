# Сцены

Все сцены необходимо наследовать от EmptyScene
```java
public abstract class EmptyScene{
    public HashMap<String, Layer> layers =new HashMap<>();//Слои
    
    public abstract void setup();//Функция в которой и нужно создавать и добавлять необходимые BasicObject

    public <T extends BasicObject> T addObject(/*...*/);//Добавить объект, возвращает его же
    public void removeObject(BasicObject object);//Удалить объект
    public void moveObjectToLayer(BasicObject object, String layer);//Переместить объект в слой
    public void addLayer(String name);//Добавить слой
    
    public void dispose(){}//(Необязательно) Можно наследовать функцию, если в сцене нужно что-то уничтожить
}
```

## Слой
Если слой создается через функцию ___addObject___, то он является __временным__, т.е. Удалиться, если не будет объектов 
и дочерних слоев

Слои, которые создаются через функцию ___addLayer___, временными не являются

```java
public class Layer {
    public List<BasicObject> objects=new ArrayList<>();//Массив объектов слоя
    public byte number=0;//Номер отрисовки (и логики) слоя
    public boolean save=false;//Если false, то слой временный

    //Активность слоя: вызывать ли doLogic или draw у объектов слоя и объектов дочернего слоя, или нет
    public void changeActive(/*...*/);

    public List<BasicObject> getObjects();//Возвращает копию массива объектов слоя

    public void addChildLayer(String layerName);//Добавить дочерний слой
    public void removeChildLayer(String layerName);//Удалить дочерний слой
    public boolean childLayersIsEmpty();//Имеет ли дочерние слои
}
```
