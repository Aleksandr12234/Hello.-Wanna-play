package com.one.Structure.testClasses;

import com.one.Structure.Scenes.EmptyScene;

public class TestScene extends EmptyScene {

    public void setup(){
        addObject(new Square(0,0,1, 1));
        addObject(new Square(0,-1,1, 1));
        addObject(new Square(0,-2,1, 1));
        addObject(new Square(0,1,1, 1));
        addObject(new Square(0,2,1, 1));

        addObject(new Square(-1,0,1, 1));
        addObject(new Square(-2,0,1, 1));
        addObject(new Square(-3,0,1, 1));
        addObject(new Square(-4,0,1, 1));
    }
}
