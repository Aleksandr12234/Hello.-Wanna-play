package com.one.Structure.collision.Figures;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Objects.ColiisionObjectInterface;
import com.one.Structure.collision.Objects.CollisionObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

public interface FigureCollision {
    boolean handlingCollision(FigureCollision collision);
    ColiisionObjectInterface getMaster();
    void update();
    void draw(ShapeDrawer drawer);
}
