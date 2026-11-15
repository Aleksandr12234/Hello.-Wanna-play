package com.one.Project.Objects.Akari.Modules;

import com.one.Project.Objects.Akari.Akari;
import com.one.Structure.Collision.BasicBody;
import com.one.Structure.Collision.Builders.BodyDefBuilder;
import com.one.Structure.Collision.Builders.Figures.RectShapeBuilder;
import com.one.Structure.Collision.Builders.FixtureDefBuilder;

public class AkariCollision extends BasicBody<Akari> {
    public AkariCollision(Akari master) {
        super(master, new BodyDefBuilder().setFixedRotation(true).build());
        localTransform.size.set(0.5f, 0.5f);
    }

    @Override
    public void setup() {
        super.setup();
        body.setFixtureDef(new FixtureDefBuilder().setShape(new RectShapeBuilder()
            .setLocalSize(transform.size.x, transform.size.y).build()).build());
    }

    @Override
    public void doLogic() {
        super.doLogic();
        master.getTransform().position.set(body.body.getPosition());
    }
}
