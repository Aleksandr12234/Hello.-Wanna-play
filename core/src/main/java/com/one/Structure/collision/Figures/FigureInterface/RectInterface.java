package com.one.Structure.collision.Figures.FigureInterface;

import com.one.Structure.Objects.Module.Transform;
import com.one.Structure.collision.Figures.FigureCollision;

public interface RectInterface extends FigureCollision {
    float getLenghtLine();
    float getAngelLine();
    LineInterface[] getLines();

    Transform getTransform();
}
