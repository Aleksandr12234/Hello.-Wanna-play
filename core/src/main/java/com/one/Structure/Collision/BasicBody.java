package com.one.Structure.Collision;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.one.Structure.Collision.Builders.BodyDefBuilder;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module;
import com.one.Structure.Stock;
import lombok.Getter;

@Getter
public class BasicBody<T extends BasicObject> extends Module<T> {
    protected BodyWrapper body;

    public BasicBody(T master, BodyDef bodyDef) {
        super(master);
        body=new BodyWrapper(this, bodyDef);
    }

    public BasicBody(T master) {
        this(master, new BodyDefBuilder().build());
    }

    public void beginCollision(BasicBody<?> other){}
    public void endCollision(BasicBody<?> other){}
    public void beginTrigger(BasicBody<?> other){}
    public void endTrigger(BasicBody<?> other){}

    @Override
    public void onDispose() {
        super.onDispose();
        Stock.getTaskRunner().updateCollisions.getWorld().destroyBody(body.body);
    }
}
