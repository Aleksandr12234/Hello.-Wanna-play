package com.one.Structure.testClasses;

import com.one.Structure.collision.Objects.PhysicalObject;

public class TestPhysicRect extends Square {
    PhysicalObject<TestPhysicRect> collision=new PhysicalObject<>(this, false);

    public TestPhysicRect(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void doLogic() {
        collision.doLogic();
    }
}
