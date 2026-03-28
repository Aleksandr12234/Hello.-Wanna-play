package com.one.Structure.Objects;

import com.one.Structure.Objects.Module.Transform;
import space.earlygrey.shapedrawer.ShapeDrawer;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static float deltaTime;
    public Transform transform = new Transform();

    /**
     * Используется для отрисовки объекта
     */
    public void draw(){}

    /**
     * Используется для действий объекта, перед отрисовкой
     */
    public void doLogic(){}
}
