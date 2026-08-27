package com.one.Structure.Objects;

import com.one.Structure.Objects.BasicObjectComponents.StatusInfo;
import com.one.Structure.Objects.BasicObjectComponents.Transform;
import com.one.Structure.Objects.Module.Module;

import java.util.ArrayList;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static float deltaTime;
    protected StatusInfo status=new StatusInfo();
    protected Transform transform = new Transform();

    private ArrayList<Module<?>> modulesToSetup=new ArrayList<>();

    /**
     * Вызывается один раз после добавления объекта в сцену
     */
    public void setup(){
        for (Module<?> i : modulesToSetup) {
            i.setup();
        }
        modulesToSetup=null;
    }

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

    public void addModuleToSetup(Module<?> module){
        modulesToSetup.add(module);
    }
}
