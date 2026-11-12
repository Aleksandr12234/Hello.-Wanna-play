package com.one.Structure.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObjectComponents.StatusInfo;
import com.one.Structure.Objects.BasicObjectComponents.Transform;
import com.one.Structure.Stock;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Базовый объект, методы которого вызываются в структуре
 */
public class BasicObject {
    public static float deltaTime;

    @Getter protected StatusInfo status=new StatusInfo();
    @Getter protected Transform transform = new Transform();

    private final ArrayList<Module<?>> modules = new ArrayList<>();

    public void setup(){
        for (Module<?> i : modules) {
            i.setup();
        }
    }

    public void doLogic(){
        for (Module<?> i : modules) {
            i.doLogic();
        }
    }

    public void draw(){
        for (Module<?> i : modules) {
            i.draw();
        }
    }

    public void onDispose(){
        for (Module<?> i : modules) {
            i.onDispose();
        }
    }

    public void addModule(Module<?> module){
        modules.add(module);
    }
}
