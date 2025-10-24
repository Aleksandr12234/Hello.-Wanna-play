package com.one.Structure.testClasses;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Scenes.EmptyScene;

import java.util.ArrayList;

public class TestScene extends EmptyScene {
    private final ArrayList<Square> squares=new ArrayList<>();

    @Override
    public void setup(){
        //addObject(new Square(-100,0,1));
        //addObject(new Square(-160,0,1));
        addObject(new Square(-200,0,0.1f));
        //addObject(new Square(25,0,0));
    }

    @Override
    public void addObject(BasicObject added) {
        if(added instanceof Square)
            squares.add((Square) added);
    }

    @Override
    public void removeObject(BasicObject removed) {
        squares.remove((Square) removed);
    }

    @Override
    public ArrayList<BasicObject> getObjects() {
        ArrayList<BasicObject> out =new ArrayList<>();
        out.addAll(squares);
        return out;
    }
}
