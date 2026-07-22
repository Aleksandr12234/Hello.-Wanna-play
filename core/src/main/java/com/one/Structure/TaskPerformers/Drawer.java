package com.one.Structure.TaskPerformers;

import com.badlogic.gdx.utils.ScreenUtils;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

import java.util.List;

public class Drawer {
    private float red, green, blue;
    private boolean drawCollision=false;

    public void setBackgroundColor(float r, float g, float b){
        red=r;
        green=g;
        blue=b;
    }

    public void setDrawCollision(boolean drawCollision){
        this.drawCollision=drawCollision;
    }

    public void draw(){
        Stock.getTaskRunner().serviceObjects.viewport.apply();
        Stock.getTaskRunner().serviceObjects.batch.setProjectionMatrix(Stock.getTaskRunner().serviceObjects.camera.combined);

        ScreenUtils.clear(red, green, blue, 1);

        List<BasicObject> activeObjects=Stock.getTaskRunner().scenesManager.getActiveObjects();

        Stock.getTaskRunner().serviceObjects.batch.begin();
        for (BasicObject i : activeObjects) {
            if(i.getTransform().getDraw())i.draw();
        }

        if(drawCollision)Stock.getTaskRunner().updateCollisions.drawCollisions();

        Debug.drawTask();
        Stock.getTaskRunner().serviceObjects.batch.end();
    }

}
