package com.one.Structure.collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public abstract class CollisionObject extends BasicObject{
    public BasicObject master;

    public CollisionObject(BasicObject master){
        this.master=master;
    }

    abstract public void action(LineCollision lineCollision);
    abstract public void action(RectCollision rectCollision);
}
