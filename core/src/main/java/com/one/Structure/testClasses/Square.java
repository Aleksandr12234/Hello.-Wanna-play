package com.one.Structure.testClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Objects.PhysicalObject;

//вспомогательный объект пример
public class Square extends BasicObject {
    private final Sprite sprite;

    private final PhysicalObject collision=new PhysicalObject(this, true);

    float speed;

    public Square(float x, float y, float width, float height){
        size.set(width, height);

        position.set(x,y);
        sprite=new Sprite(manager.texturesLoader.loadTexture("test/libgdx.png"));
    }

    @Override
    public void doLogic(){
        collision.updateStatus();
    }

    @Override
    public void draw() {
        sprite.setSize(100,100);
        sprite.setCenter(position.x, position.y);
        //sprite.draw(Manager.serviceObjects.batch);
        shapeDrawer.rectangle(position.x-size.x/2, position.y-size.y/2, size.x, size.y);
    }
}
