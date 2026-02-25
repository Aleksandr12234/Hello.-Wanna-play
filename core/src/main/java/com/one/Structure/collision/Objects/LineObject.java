package com.one.Structure.collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public class LineObject<T extends BasicObject> extends CollisionObject<T>{
    protected final LineCollision<LineObject<T>> collision;

    public LineObject(T master, boolean isStatic) {
        super(master);

        collision=new LineCollision<>(this);
        if(!isStatic)manager.updateCollisions.addActiveCollision(collision);
        else manager.updateCollisions.addStaticCollision(collision);
    }

    @Override
    public void action(LineInterface lineCollision) {}
    @Override
    public void action(RectInterface rectCollision) {}
}
