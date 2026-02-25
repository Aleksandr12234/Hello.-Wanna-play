package com.one.Structure.collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public abstract class CollisionObject<T extends BasicObject> extends Module<T> implements ColiisionObjectInterface {

    public CollisionObject(T master) {super(master);}

    abstract public void action(LineInterface lineCollision);
    abstract public void action(RectInterface rectCollision);
}
