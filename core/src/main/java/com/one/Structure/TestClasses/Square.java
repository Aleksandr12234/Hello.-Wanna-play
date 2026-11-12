package com.one.Structure.TestClasses;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

//вспомогательный объект пример
public class Square extends BasicObject {
    private final Sprite sprite;

    public Square(float x, float y, float width, float height){
        transform.position.set(x, y);
        transform.size.set(width, height);
        sprite=new Sprite(Stock.getTaskRunner().assetsLoader.texture.loadTexture("Structure/test/libgdx.png"));
    }

    @Override
    public void draw() {
        sprite.setOriginCenter();
        sprite.setRotation(transform.rotate.getDegreesAngle());
        sprite.setSize(transform.size.x,transform.size.y);
        sprite.setCenter(transform.position.x, transform.position.y);

        sprite.draw(Stock.getTaskRunner().drawer.batch);
    }
}
