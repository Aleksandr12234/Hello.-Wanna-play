package com.one.Structure.collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Figures.RectCollision;

public class NoPhysicalObject<T extends BasicObject> extends CollisionObject<T> {
    protected final RectCollision<NoPhysicalObject<T>> rectCollision=new RectCollision<>(this);

    public NoPhysicalObject(T master, boolean isStatic){
        super(master);

        if(!isStatic)manager.updateCollisions.addActiveCollision(rectCollision);
        else manager.updateCollisions.addStaticCollision(rectCollision);
    }

    @Override
    public void action(LineInterface lineCollision) {}
    @Override
    public void action(RectInterface rectCollision) {}
}
