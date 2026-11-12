package com.one.Structure.Collision;

import com.badlogic.gdx.physics.box2d.*;
import com.one.Structure.Stock;

public class BodyWrapper {
    public final Body body;

    public BodyWrapper(BasicBody<?> object, BodyDef bodyDef){
        body = Stock.getTaskRunner().updateCollisions.getWorld().createBody(bodyDef);
        body.setUserData(object);
    }

    public void setFixtureDef(FixtureDef fixtureDef){
        if(body.getFixtureList()!=null && body.getFixtureList().notEmpty())body.destroyFixture(body.getFixtureList().get(0));
        body.createFixture(fixtureDef);
    }
}
