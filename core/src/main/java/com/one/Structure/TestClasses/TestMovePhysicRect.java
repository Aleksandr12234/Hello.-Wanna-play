package com.one.Structure.TestClasses;

import com.badlogic.gdx.math.Vector2;

public class TestMovePhysicRect extends TestPhysicRect{
    Vector2 direction;
    float speed=1f;

    public TestMovePhysicRect(float x, float y, float width, float height, Vector2 direction) {
        super(x, y, width, height);
        this.direction=direction;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        Vector2 frameDirection=direction.cpy();
        frameDirection.scl(speed*deltaTime);

        transform.addPosition(frameDirection.x, frameDirection.y);
    }
}
