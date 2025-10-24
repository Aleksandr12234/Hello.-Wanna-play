package com.one.Structure.Scenes;

import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public abstract class EmptyScene implements Scene{
    public static Manager manager;

    ArrayList<BasicObject> objects=new ArrayList<>();

    @Override
    public void addObject(BasicObject added) {
        objects.add(added);
    }

    @Override
    public void addObject(BasicObject undo, BasicObject added) {
        int index=objects.indexOf(undo);
        if(index>-1) objects.add(index+1, added);
        else objects.add(added);
    }

    @Override
    public void removeObject(BasicObject removed) {
        objects.remove(removed);
    }

    @Override
    public ArrayList<BasicObject> getObjects() {
        return new ArrayList<>(objects);
    }
}
