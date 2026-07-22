package com.one.Structure.Collision.Figures;

import com.one.Structure.Objects.BasicObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface FigureCollision {
    boolean handlingCollision(FigureCollision collision);
    void draw(ShapeDrawer drawer);
    BasicObject getMaster();
    void doLogic();
    boolean isActive();
    boolean getDispose();
}
