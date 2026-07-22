package com.one.Structure.TestClasses;

import com.one.Structure.Collision.Objects.PhysicalObject;

public class TestPhysicRect extends Square {
    PhysicalObject<TestPhysicRect> collision;

    public TestPhysicRect(float x, float y, float width, float height, boolean isStatic) {
        super(x, y, width, height);
        collision=new PhysicalObject<>(this, isStatic);
    }
    public TestPhysicRect(float x, float y, float width, float height){
        this(x, y, width, height, false);
    }

    @Override
    public void doLogic() {
        collision.doLogic();
    }
}
