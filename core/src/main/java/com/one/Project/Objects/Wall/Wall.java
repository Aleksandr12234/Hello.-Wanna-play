package com.one.Project.Objects.Wall;

import com.one.Structure.Objects.BasicObject;

public class Wall extends BasicObject {
    WallColision collision= new WallColision(this);

    public Wall(float x, float y, float width, float height, float angle){
        position.set(x, y);
        size.set(width, height);
        rotate=angle;

        collision.updateStatus();
    }

    @Override
    public void draw() {
        shapeDrawer.rectangle(position.x-size.x/2, position.y- size.y/2, size.x, size.y);
    }

    public Wall setColor(boolean black, boolean white){
        collision.setColor(black, white);
        return this;
    }
}
