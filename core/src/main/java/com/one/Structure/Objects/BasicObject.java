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

    public void setup(){
        for (Module<?> i : modulesToSetup) {
            i.setup();
        }
        modulesToSetup=null;
    }

    public void doLogic(){}

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
