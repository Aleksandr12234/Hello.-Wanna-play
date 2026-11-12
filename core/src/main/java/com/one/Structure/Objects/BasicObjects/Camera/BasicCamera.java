package com.one.Structure.Objects.BasicObjects.Camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class BasicCamera extends BasicObject {
    public static final OrthographicCamera camera = (OrthographicCamera) Stock.getTaskRunner().drawer.viewport.getCamera();

    public BasicCamera(){
        updatePosition();
    }

    private void updatePosition(){
        camera.position.set(transform.position, 0);
        camera.update();
    }
}
