package com.one.Structure.collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public class LineObject extends CollisionObject{
    private final LineCollision collision;

    public LineObject(BasicObject master) {
        super(master);

        position=master.position;
        size=master.size;
        rotate=master.rotate;

        collision=new LineCollision(this);
        manager.updateCollisions.addCollision(collision);
    }

    public void updateStatus(){dispose= master.dispose;}

    @Override
    public void action(LineCollision lineCollision) {}
    @Override
    public void action(RectCollision rectCollision) {}
}
