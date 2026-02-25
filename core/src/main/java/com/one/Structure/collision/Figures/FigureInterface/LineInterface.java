package com.one.Structure.collision.Figures.FigureInterface;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.collision.Figures.FigureCollision;

public interface LineInterface extends FigureCollision {
    void setCoordinates(float x1,float y1, float x2, float y2);
    Vector2 searchIntersectionPoint(LineInterface line);
    float getK();
    float getB();
    float getX1();
    float getX2();
    float getY1();
    float getY2();
}
