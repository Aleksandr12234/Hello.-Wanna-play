package com.one.Structure.testClasses;

import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(true);

        addObject(new Square(0,0,1, 1));

        addObject(new TestPhysicRect(-1,0,1, 1));

        addObject(new TestControlRect(2, 0, 1, 1));
    }
}
