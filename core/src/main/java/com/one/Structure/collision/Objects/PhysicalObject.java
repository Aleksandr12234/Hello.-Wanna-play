package com.one.Structure.collision.Objects;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public class PhysicalObject extends CollisionObject{
    public Vector2 undoPosition =new Vector2();
    private final RectCollision rectCollision;

    public PhysicalObject(BasicObject master){
        super(master);

        position=master.position;
        size=master.size;
        rotate=master.rotate;

        rectCollision=new RectCollision(this);

        manager.updateCollisions.addCollision(rectCollision);
    }

    public void updateStatus(){
        this.dispose=master.dispose;
        undoPosition.x=master.position.x;
        undoPosition.y=master.position.y;
        this.rotate= master.rotate;
    }

    @Override
    public void action(LineCollision lineCollision) {}

    @Override
    public void action(RectCollision rectCollision) {
        if(master.position.x== undoPosition.x && master.position.y==undoPosition.y)return;

        float activeX=master.position.x;

        master.position.x= undoPosition.x;
        this.rectCollision.update();
        if(!this.rectCollision.handlingCollision(rectCollision))return;
        master.position.x=activeX;

        master.position.y= undoPosition.y;
        this.rectCollision.update();
        if(!this.rectCollision.handlingCollision(rectCollision))return;
        master.position.x= undoPosition.x;
    }
}
