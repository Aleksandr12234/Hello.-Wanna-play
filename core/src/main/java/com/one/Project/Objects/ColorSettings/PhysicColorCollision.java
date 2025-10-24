package com.one.Project.Objects.ColorSettings;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.RectCollision;
import com.one.Structure.collision.Objects.PhysicalObject;

public class PhysicColorCollision extends PhysicalObject implements ColorCollision{
    boolean black=false, white=false;

    public PhysicColorCollision(BasicObject master) {
        super(master);
    }

    @Override
    public void action(RectCollision rectCollision) {
        if(!(rectCollision.master instanceof ColorCollision)) {
            super.action(rectCollision);
        }else if(
            (((ColorCollision) rectCollision.master).getWhite() && white)
            || (((ColorCollision) rectCollision.master).getBlack() && black)
        ){
            super.action(rectCollision);
        }
    }

    @Override
    public boolean getBlack() {
        return black;
    }

    @Override
    public boolean getWhite() {
        return white;
    }

    @Override
    public void setColor(boolean black, boolean white) {
        this.black=black;
        this.white=white;
    }
}
