package com.one.Structure.taskPerformers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public class Drawer {
    private static float red, green, blue;
    public static Manager manager;
    public static SpriteBatch batch;
    public static OrthographicCamera camera;
    public static boolean drawCollision=false;

    public static void setBackgroundColor(float r, float g, float b){
        red=r;
        green=g;
        blue=b;
    }

    public static void draw(ArrayList<BasicObject> basicObjects){
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(red, green, blue, 1);

        batch.begin();
        for (BasicObject i : basicObjects) {
            i.draw();
        }
        if(drawCollision)manager.updateCollisions.drawCollisions();
        batch.end();
    }

}
