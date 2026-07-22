package com.one.Structure.Objects;

import com.one.Structure.Objects.Module.Transform;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static float deltaTime;
    protected Transform transform = new Transform();

    /**
     * Используется для отрисовки объекта
     */
    public void draw(){}

    /**
     * Используется для действий объекта, перед отрисовкой
     */
    public void doLogic(){}

    public Transform getTransform() {
        return transform;
    }

    public void onDispose(){}
}
