package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    public List<BasicObject> objects=new ArrayList<>();
    public byte number=0;

    public void chageActive(boolean isActive){
        for (BasicObject i : objects) {
            i.transform.setActive(isActive);
        }
    }
}
