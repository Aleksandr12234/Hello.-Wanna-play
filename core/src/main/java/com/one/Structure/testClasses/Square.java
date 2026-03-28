package com.one.Structure.testClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

//вспомогательный объект пример
public class Square extends BasicObject {
    private final Sprite sprite;

    public Square(float x, float y, float width, float height){
        transform.setPosition(x, y);
        transform.setSize(width, height);
        sprite=new Sprite(Stock.getTaskRunner().texturesLoader.loadTexture("test/libgdx.png"));
    }

    @Override
    public void draw() {
        sprite.setOriginCenter();
        sprite.setRotation(transform.getDegreesRotate());
        sprite.setSize(transform.getSize().x,transform.getSize().y);
        sprite.setCenter(transform.getPosition().x, transform.getPosition().y);

//        Stock.getTaskRunner().serviceObjects.shapeDrawer.rectangle(
//            transform.getPosition().x-transform.getSize().x/2, transform.getPosition().y-transform.getSize().y/2,
//            transform.getSize().x, transform.getSize().y);

        sprite.draw(Stock.getTaskRunner().serviceObjects.batch);
    }
}
