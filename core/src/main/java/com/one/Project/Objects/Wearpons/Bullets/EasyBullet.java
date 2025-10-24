package com.one.Project.Objects.Wearpons.Bullets;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;

public class EasyBullet extends BasicObject {
    float speed=20;
    float distance;
    EasyBulletCollision collision=new EasyBulletCollision(this);

    public EasyBullet(float angle, float distance, Vector2 position){
        size.set((float) Math.cos(angle), (float)Math.sin(angle));
        this.distance=distance;
        this.position.set(position);
    }

    @Override
    public void doLogic() {
        position.x+=size.x;
        position.y+=size.y;

        size.nor().scl(speed);
        distance-=size.len();
        if(distance<=0)dispose=true;

        collision.updateStatus();
    }

    @Override
    public void draw() {
        shapeDrawer.line(position.x, position.y, position.x-size.x, position.y-size.y);
    }
}
