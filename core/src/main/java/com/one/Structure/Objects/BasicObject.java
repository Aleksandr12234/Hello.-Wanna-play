package com.one.Structure.Objects;

import com.one.Structure.Manager;
import com.one.Structure.Objects.Module.Transform;
import space.earlygrey.shapedrawer.ShapeDrawer;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static Manager manager;
    public static ShapeDrawer shapeDrawer;
    public static float deltaTime;

    public Transform transform = new Transform();

    /**
     * Используется один раз, после создания объекта
     */
    public void setup(){}

    /**
     * Используется для отрисовки объекта
     */
    public void draw(){}

    /**
     * Используется для действий объекта, перед отрисовкой
     */
    public void doLogic(){}

    public void doOnFreeze(){}

    public boolean getDispose(){return transform.getDispose();}
    public void setDispose(boolean value){transform.setDispose(value);}
}
