package com.one.Structure.TestClasses;

import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(true);
        addObject(new Rect());
    }
}
