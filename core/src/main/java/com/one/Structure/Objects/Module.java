package com.one.Structure.Objects;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObjectComponents.Transform;

public class Module<T extends BasicObject> extends BasicObject {
    public final T master;
    public final Transform localTransform=new Transform();

    public Module(T master){
        this.master=master;
        status= master.getStatus();
        master.addModule(this);
    }

    @Override
    public void setup() {
        super.setup();
        if(transform.position!=master.getTransform().position) updateGlobalPosition();
        if(transform.size!=master.getTransform().size)updateGlobalSize();
        if(transform.rotate!=master.getTransform().rotate)updateGlobalRotate();
    }

    @Override
    public void doLogic() {
        super.doLogic();
        if(transform.position!=master.getTransform().position) updateGlobalPosition();
        if(transform.size!=master.getTransform().size)updateGlobalSize();
        if(transform.rotate!=master.getTransform().rotate)updateGlobalRotate();
    }

    public void updateGlobalPosition(){
        Vector2 updatePosition=new Vector2(localTransform.position);
        updatePosition.rotateDeg(master.getTransform().rotate.getDegreesAngle());
        transform.position.set(master.getTransform().position.x+updatePosition.x,
            master.getTransform().position.y+updatePosition.y);
    }

    public void updateGlobalSize(){
        transform.size.set(master.getTransform().size.x*localTransform.size.x,
            master.getTransform().size.y*localTransform.size.y);
    }

    public void updateGlobalRotate(){
        transform.rotate.setDegreesAngle(
            master.getTransform().rotate.getDegreesAngle()+localTransform.rotate.getDegreesAngle());
    }
}
