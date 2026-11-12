package com.one.Structure.TaskPerformers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.utils.ShaderLoader;
import com.one.Project.BasicSettings;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.BasicObjects.Camera.BasicCamera;
import com.one.Structure.Stock;
import lombok.Setter;

import java.util.List;

public class Drawer {
    public final SpriteBatch batch;
    public final ExtendViewport viewport;

    public final PostProcessor postProcessor;

    private float red, green, blue;
    @Setter private boolean drawCollision=false;

    public Drawer(){
        batch = new SpriteBatch();
        viewport=new ExtendViewport(BasicSettings.VIEWPORT[0], BasicSettings.VIEWPORT[1]);

        ShaderLoader.BasePath="Structure/shaders/";
        postProcessor=new PostProcessor(false, false, false);
        postProcessor.setEnabled(false);
    }

    public void draw(){
        viewport.apply();
        batch.setProjectionMatrix(BasicCamera.camera.combined);

        ScreenUtils.clear(red, green, blue, 1);

        List<BasicObject> activeObjects=Stock.getTaskRunner().scenesManager.getActiveObjects();

        if(postProcessor.isEnabled()) postProcessor.capture();
        batch.begin();
        {
            for (BasicObject i : activeObjects) {
                if (i.getStatus().isDraw) i.draw();
            }
            Debug.drawTask();

        }
        batch.end();
        if(postProcessor.isEnabled()) postProcessor.render();

        if(drawCollision) Stock.getTaskRunner().updateCollisions.debug();
    }

    public void setBackgroundColor(float r, float g, float b){
        red=r;
        green=g;
        blue=b;
    }

    public void dispose(){
        postProcessor.dispose();
        batch.dispose();
    }
}
