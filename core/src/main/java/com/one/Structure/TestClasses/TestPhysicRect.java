package com.one.Structure.TestClasses;

import com.one.Structure.Collision.Body.RectPhysicalBody;

public class TestPhysicRect extends Square {
    RectPhysicalBody<TestPhysicRect> collision;

    public TestPhysicRect(float x, float y, float width, float height, boolean isStatic){
        super(x, y, width, height);
        collision=new RectPhysicalBody<>(this, isStatic);
    }

    public TestPhysicRect(float x, float y, float width, float height){
        this(x, y, width, height, false);
    }

    @Override
    public void doLogic() {
        collision.doLogic();
    }
}
