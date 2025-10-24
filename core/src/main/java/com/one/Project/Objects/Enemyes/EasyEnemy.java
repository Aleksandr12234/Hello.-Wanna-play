package com.one.Project.Objects.Enemyes;

import com.one.Structure.Objects.BasicObject;

public class EasyEnemy extends BasicObject {
    int hp=100;
    EasyEnemyCollision collision=new EasyEnemyCollision(this);

    public EasyEnemy(float x, float y, float width, float height){
        position.x=x;
        position.y=y;
        size.x=width;
        size.y=height;
    }

    @Override
    public void doLogic() {
        collision.updateStatus();
    }

    @Override
    public void draw() {
        shapeDrawer.rectangle(position.x-size.x/2, position.y-size.y/2, size.x, size.y);
    }
}
