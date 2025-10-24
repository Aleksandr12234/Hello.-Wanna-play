package com.one.Project.Objects.Skills;

import com.one.Structure.Objects.BasicObject;

public class EasySkill extends BasicObject {
    BasicObject master;

    SkillCollision skillCollision=new SkillCollision(this);

    public EasySkill(float x, float y){
        position.set(x, y);
        size.set(10, 10);
    }

    public int use(int energy){
        System.out.println("Умение использовано");
        dispose=true;
        return energy;
    }

    @Override
    public void doLogic() {
        master= skillCollision.getNewMaster();
        if(master!=null)skillCollision.dispose=true;
    }

    @Override
    public void draw() {
        if(master!=null)return;
        shapeDrawer.rectangle(position.x-size.x/2, position.y- size.y/2, size.x, size.y);
    }
}
