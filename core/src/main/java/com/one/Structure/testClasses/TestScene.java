package com.one.Structure.testClasses;

import com.one.Structure.Scenes.EmptyScene;

public class TestScene extends EmptyScene {
    @Override
    public void setup(){
        //addObject(new Square(-100,0,1));
        //addObject(new Square(-160,0,1));
        addObject(new Square(-200,0,50, 50));
        //addObject(new Square(25,0,0));
    }
}
