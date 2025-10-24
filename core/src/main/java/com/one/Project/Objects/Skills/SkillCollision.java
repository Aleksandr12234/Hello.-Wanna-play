package com.one.Project.Objects.Skills;

import com.one.Project.Objects.Creature.Creature;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.RectCollision;
import com.one.Structure.collision.Objects.PhysicalObject;

public class SkillCollision extends PhysicalObject {
    BasicObject newMaster;

    public SkillCollision(BasicObject master) {
        super(master);
    }

    @Override
    public void action(RectCollision rectCollision) {
        if(rectCollision.master.master instanceof Creature) {
            newMaster= rectCollision.master.master;
            ((Creature) rectCollision.master.master).setSkill((EasySkill) master);
        }
    }

    public BasicObject getNewMaster() {
        return newMaster;
    }
}
