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

        //Можно будет разделить на потоки
        for (BasicObject i : activeObjects) {
            if (i.getTransform().getDispose()) {
                i.onDispose();
                Stock.getTaskRunner().scenesManager.activeScene.removeObject(i);
                continue;
            }
            if(i.getTransform().getActive())i.doLogic();
        }
    }
}
