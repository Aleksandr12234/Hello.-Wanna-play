package com.one.Structure.collision.Objects;

import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

public interface ColiisionObjectInterface {
    void action(LineInterface lineCollision);
    void action(RectInterface rectCollision);
}
