package com.one.Structure.testClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Objects.PhysicalObject;

//вспомогательный объект пример
public class Square extends BasicObject {
    private final Sprite sprite;

    private final PhysicalObject collision=new PhysicalObject(this);

    float speed;

    public Square(float x, float y, float speed){
        size.x=50;
        size.y=50;

        position.x=x;
        position.y=y;
        sprite=new Sprite(manager.texturesLoader.loadTexture("test/libgdx.png"));
        this.speed=speed;
    }

    @Override
    public void doLogic(){
        collision.updateStatus();

        //position.x+=speed;
//        if(position.x>200){
//            dispose=true;
//            collision.dispose=true;
//            manager.changeScene(new TestScene());
//            //manager.addObject(new Square(position.x-50, position.y, speed));
//        }
    }

    @Override
    public void draw() {
        sprite.setSize(100,100);
        sprite.setCenter(position.x, position.y);
        //sprite.draw(Manager.serviceObjects.batch);
        shapeDrawer.rectangle(position.x-size.x/2, position.y-size.y/2, size.x, size.y);
    }
}
