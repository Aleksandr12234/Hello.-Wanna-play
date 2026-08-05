package com.one.Structure.Objects;

import com.one.Structure.Objects.BasicObjectComponents.StatusInfo;
import com.one.Structure.Objects.BasicObjectComponents.Transform;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static float deltaTime;
    protected StatusInfo status=new StatusInfo();
    protected Transform transform = new Transform();

    /**
     * Вызывается один раз после добавления объекта в сцену
     */
    public void setup(){}

    /**
     * Используется для действий объекта, перед отрисовкой
     */
    public void doLogic(){}

    /**
     * Используется для отрисовки объекта
     */
    public void draw(){}

    public void onDispose(){}

    public Transform getTransform() {
        return transform;
    }

    public StatusInfo getStatus(){
        return status;
    }
}
