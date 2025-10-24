package com.one.Project.Objects.UI.Camera;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

public class CameraController extends BasicObject {
    Observed observed;


    public CameraController(Observed observed){
        this.observed=observed;
        position.set(observed.getPosition().x, observed.getPosition().y);
    }

    @Override
    public void doLogic() {
        Vector2 observedPosition = observed.getPosition();

        Vector2 moveTo=new Vector2((observedPosition.x-position.x)/10, (observedPosition.y-position.y)/10);

        if(moveTo.len()<0.1) position.set(observedPosition.x, observedPosition.y);

        position.set(position.x+moveTo.x, position.y+moveTo.y);

        Manager.serviceObjects.camera.position.set(position.x, position.y, 0);
        Manager.serviceObjects.camera.update();
    }
}
