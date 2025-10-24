package com.one.Project.Objects.Wall;

import com.one.Project.Objects.ColorSettings.PhysicColorCollision;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.RectCollision;

public class WallColision extends PhysicColorCollision {
    public WallColision(BasicObject master) {
        super(master);
    }

    @Override
    public void action(RectCollision rectCollision) {}
}
