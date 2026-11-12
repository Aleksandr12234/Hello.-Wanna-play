package com.one.Structure.Collision.Builders.Figures;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;

public class CircleShapeBuilder {
    private final CircleShape shape=new CircleShape();

    public CircleShapeBuilder(){
        shape.setRadius(1);
        shape.setPosition(new Vector2(0, 0));
    }

    public CircleShapeBuilder setRadius(float value){
        shape.setRadius(value);
        return this;
    }

    public CircleShapeBuilder setLocalPosition(float x, float y){
        shape.setPosition(new Vector2(x, y));
        return this;
    }

    public CircleShape build(){
        return shape;
    }
}
