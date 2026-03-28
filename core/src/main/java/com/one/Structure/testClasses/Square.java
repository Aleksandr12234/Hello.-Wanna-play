package com.one.Structure.testClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

//вспомогательный объект пример
public class Square extends BasicObject {
    private final Sprite sprite;

    //private final PhysicalObject collision=new PhysicalObject(this, true);

    float speed;

    public Square(float x, float y, float width, float height){
        transform.setPosition(x, y);
        transform.setSize(width, height);
        sprite=new Sprite(Stock.getTaskRunner().texturesLoader.loadTexture("test/libgdx.png"));
    }

    @Override
    public void draw() {
        sprite.setSize(transform.getSize().x,transform.getSize().y);
        sprite.setCenter(transform.getPosition().x, transform.getPosition().y);
        sprite.draw(Stock.getTaskRunner().serviceObjects.batch);
//        Stock.getTaskRunner().serviceObjects.shapeDrawer.rectangle(
//            transform.getPosition().x-transform.getSize().x/2, transform.getPosition().y-transform.getSize().y/2,
//            transform.getSize().x, transform.getSize().y);
    }
}
