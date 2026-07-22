package com.one.Structure.Collision.Objects;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.Stock;
import com.one.Structure.Collision.Figures.FigureCollision;
import com.one.Structure.Collision.Figures.LineCollision;
import com.one.Structure.Collision.Figures.RectCollision;

public class LineObject<T extends BasicObject> extends Module<T> implements Body {
    protected final LineCollision<LineObject<T>> collision=new LineCollision<>(this);
    protected boolean isStatic;

    public LineObject(T master, boolean isStatic) {
        super(master);

        this.isStatic=isStatic;

        if(!isStatic) Stock.getTaskRunner().updateCollisions.addActiveCollision(collision);
        else Stock.getTaskRunner().updateCollisions.addStaticCollision(collision);
    }

    protected void action(LineCollision<?> lineCollision){}
    protected void action(RectCollision<?> rectCollision){}

    @Override
    public void action(FigureCollision collision) {
        if(collision instanceof LineCollision<?>)action((LineCollision<?>) collision);
        if(collision instanceof RectCollision<?>)action((RectCollision<?>) collision);
    }
}
