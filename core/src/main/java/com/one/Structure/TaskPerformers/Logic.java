package com.one.Structure.TaskPerformers;

import com.badlogic.gdx.Gdx;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

import java.util.List;

public class Logic {
    public void update(){
        BasicObject.deltaTime= Gdx.graphics.getDeltaTime();

        List<BasicObject> activeObjects= Stock.getTaskRunner().scenesManager.getActiveObjects();

        if(activeObjects==null)return;

        for (BasicObject i : activeObjects) {
            if (i.getStatus().dispose) {
                i.onDispose();
                Stock.getTaskRunner().scenesManager.getActiveScene().removeObject(i);
                continue;
            }
            if(i.getStatus().isActive)i.doLogic();
        }
    }
}
