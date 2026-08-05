package com.one.Structure.Objects.Module;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.BasicObjectComponents.Transform;

public class Module<T extends BasicObject> extends BasicObject {
    protected final T master;
    public final Transform localTransform=new Transform();

    public Module(T master){
        this.master=master;
        status= master.getStatus();
    }

    @Override
    public void setup() {
        doLogic();
    }

    @Override
    public void doLogic() {
        if(transform.position!=master.getTransform().position) updateGlobalPosition();
        if(transform.size!=master.getTransform().size)updateGlobalSize();
        if(transform.rotate!=master.getTransform().rotate)updateGlobalRotate();
    }

    public void updateGlobalPosition(){
        transform.position.set(master.getTransform().position.x+localTransform.position.x,
            master.getTransform().position.y+localTransform.position.y);
    }

    public void updateGlobalSize(){
        transform.size.set(master.getTransform().size.x*localTransform.size.x,
            master.getTransform().size.y*localTransform.size.y);
    }

    public void updateGlobalRotate(){
        transform.rotate.setRotate(
            master.getTransform().rotate.getDegreesRotate()+localTransform.rotate.getDegreesRotate());
    }

    public T getMaster(){
        return master;
    }
}
