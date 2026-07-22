package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    public List<BasicObject> objects=new ArrayList<>();
    public byte number=0;
    public boolean save=false;

    public void changeActive(boolean isActive, boolean isDraw){
        for (BasicObject i : objects) {
            i.getTransform().setActive(isActive);
            i.getTransform().setDraw(isDraw);
        }
    }

    public List<BasicObject> getObjects() {
        return new ArrayList<>(objects);
    }
}
