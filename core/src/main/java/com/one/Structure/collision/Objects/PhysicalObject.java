package com.one.Structure.collision.Objects;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public class PhysicalObject<T extends BasicObject> extends NoPhysicalObject<T>{
    Vector2 undoPosition=new Vector2();

    public PhysicalObject(T master, boolean isStatic) {
        super(master, isStatic);
    }

    @Override
    public void doLogic() {
        undoPosition.set(transform.getPosition());
    }

    @Override
    public void action(RectCollision<?> rectCollision) {
        if(!(rectCollision.getMaster() instanceof PhysicalObject<?>))return;
        if(transform.getPosition().x== undoPosition.x && transform.getPosition().y==undoPosition.y)return;
        transform.setPosition(undoPosition);
    }
}
