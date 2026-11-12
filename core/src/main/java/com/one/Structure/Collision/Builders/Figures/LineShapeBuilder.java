package com.one.Structure.Collision.Builders.Figures;

import com.badlogic.gdx.physics.box2d.EdgeShape;

public class LineShapeBuilder {
    private final EdgeShape shape=new EdgeShape();

    public LineShapeBuilder(){
        shape.set(0, 0, 0, 0);
    }

    public LineShapeBuilder setCoordinates(float x1, float y1, float x2, float y2){
        shape.set(x1, y1, x2, y2);
        return this;
    }

    public EdgeShape build(){
        return shape;
    }
}
