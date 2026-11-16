package com.one.Project.Objects.Projectile;

import com.one.Structure.Collision.BasicBody;
import com.one.Structure.Collision.Builders.BodyDefBuilder;
import com.one.Structure.Collision.Builders.Figures.RectShapeBuilder;
import com.one.Structure.Collision.Builders.FixtureDefBuilder;

import java.util.ArrayList;

public class BasicProjectileCollision extends BasicBody<BasicProjectile> {
    private final ArrayList<BasicBody<?>> alreadyAffected=new ArrayList<>();

    public BasicProjectileCollision(BasicProjectile master) {
        super(master, new BodyDefBuilder().setFixedRotation(true).setFastCheckMod(true).build());
        localTransform.size.set(0.5f, 0.5f);
    }

    @Override
    public void setup() {
        super.setup();
        body.setFixtureDef(new FixtureDefBuilder().isTrigger(true)
            .setShape(new RectShapeBuilder().setLocalSize(transform.size.x, transform.size.y).build())
            .build());
        body.body.setTransform(transform.position, 0);
    }

    @Override
    public void doLogic() {
        super.doLogic();
        master.getTransform().position.set(body.body.getPosition());
    }

    @Override
    public void beginCollision(BasicBody<?> other) {
        super.beginCollision(other);
        if(alreadyAffected.contains(other))return;
        alreadyAffected.add(other);
        master.contact(other);
    }
}
