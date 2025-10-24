package com.one.Project.Objects.Wearpons.Bullets;

import com.one.Project.Objects.ColorSettings.ColorCollision;
import com.one.Project.Objects.Creature.Creature;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.RectCollision;
import com.one.Structure.collision.Objects.LineObject;

public class EasyBulletCollision extends LineObject implements ColorCollision {
    boolean black=true, white=false;

    public EasyBulletCollision(BasicObject master) {
        super(master);
    }

    @Override
    public void action(RectCollision rectCollision) {
        if(!(rectCollision.master.master instanceof Creature)){
            master.dispose=true;
            dispose=true;
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
