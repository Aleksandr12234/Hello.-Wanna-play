package com.one.Structure.Collision.Builders;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.one.Structure.Collision.UpdateCollisions;
import com.one.Structure.Stock;

public class FixtureDefBuilder {
    private final FixtureDef fixtureDef=new FixtureDef();

    public FixtureDefBuilder(){
        fixtureDef.restitution = 0.5f; //упругость
        fixtureDef.friction = 0.5f; //Трение
        fixtureDef.density = 0.5f;//Плотность (влияет на массу объекта)
        fixtureDef.isSensor=false;//Если true, то объект просто триггер
    }

    public FixtureDefBuilder setRestitution(float value){
        fixtureDef.restitution=value;
        return this;
    }

    public FixtureDefBuilder setFriction(float value){
        fixtureDef.friction=value;
        return this;
    }

    public FixtureDefBuilder setDensity(float value){
        fixtureDef.density=value;
        return this;
    }

    public FixtureDefBuilder isTrigger(boolean value){
        fixtureDef.isSensor=value;
        return this;
    }

    public FixtureDefBuilder setShape(Shape shape){
        fixtureDef.shape=shape; //фигура
        return this;
    }

    public FixtureDefBuilder setMyCategory(String name){
        UpdateCollisions updateCollisions=Stock.getTaskRunner().updateCollisions;
        fixtureDef.filter.categoryBits= updateCollisions.getCategories().getCategory(name);//кто я (Степени 2)
        return this;
    }

    public FixtureDefBuilder setOtherCategory(String... names){
        UpdateCollisions updateCollisions=Stock.getTaskRunner().updateCollisions;
        short mask=0;
        for(String i : names){
            mask |= updateCollisions.getCategories().getCategory(i);
        }
        fixtureDef.filter.maskBits=mask;//с кем взаимодействую (степени двойки)
        return this;
    }

    public FixtureDef build(){
        return fixtureDef;
    }
}
