package com.one.Structure.Scenes;

import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public abstract class EmptyScene implements Scene{
    public static Manager manager;

    ArrayList<BasicObject> objects=new ArrayList<>();

    @Override
    public void setup() {
        Manager.serviceObjects.camera.position.set(0, 0, 0);
        Manager.serviceObjects.camera.update();
    }

    @Override
    public void addObject(BasicObject added) {
        if(added==null)return;

        manager.isActiveObjectsChange=true;
        objects.add(added);
        added.setup();
    }

    @Override
    public void addObject(BasicObject undo, BasicObject added) {
        if(added==null)return;

        manager.isActiveObjectsChange=true;
        int index=objects.indexOf(undo);
        if(index>-1) objects.add(index+1, added);
        else objects.add(added);
        added.setup();
    }

    @Override
    public void removeObject(BasicObject removed) {
        manager.isActiveObjectsChange=true;
        objects.remove(removed);
    }

    @Override
    public ArrayList<BasicObject> getObjects() {
        return new ArrayList<>(objects);
    }
}
