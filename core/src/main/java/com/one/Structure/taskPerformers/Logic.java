package com.one.Structure.taskPerformers;

import com.badlogic.gdx.Gdx;
import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public class Logic {
    private final Manager manager;

    public Logic(Manager manager){
        this.manager=manager;
    }

    public void update(ArrayList<BasicObject> activeObjects){
        BasicObject.deltaTime= Gdx.graphics.getDeltaTime();

        //Можно будет разделить на потоки
        for (BasicObject i : activeObjects) {
            if(i.dispose){
                manager.deleteObject(i);
                continue;
            }
            i.doLogic();
        }
    }
}
