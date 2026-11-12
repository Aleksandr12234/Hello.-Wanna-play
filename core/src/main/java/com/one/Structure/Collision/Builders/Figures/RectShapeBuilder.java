package com.one.Structure.Collision.Builders.Figures;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class RectShapeBuilder {
    private final PolygonShape shape=new PolygonShape();
    private final Vector2 localSize=new Vector2(1, 1);
    private final Vector2 localPosition=new Vector2();
    private float angle=0;

    public RectShapeBuilder(){
        shape.setAsBox(1, 1, new Vector2(), 0);//Локальное смещение и размер
    }

    public RectShapeBuilder setLocalSize(float width, float height){
        localSize.set(width, height);
        shape.setAsBox(localSize.x, localSize.y, localPosition, angle);
        return this;
    }

    public RectShapeBuilder setLocalPosition(float x, float y){
        localPosition.set(x, y);
        shape.setAsBox(localSize.x, localSize.y, localPosition, angle);
        return this;
    }

    public RectShapeBuilder setLocalAngle(float angle){
        this.angle=angle;
        shape.setAsBox(localSize.x, localSize.y, localPosition, angle);
        return this;
    }

    public PolygonShape build(){
        return shape;
    }
}
