package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public interface Scene {
    ArrayList<BasicObject> getObjects();
    void setup();
    void removeObject(BasicObject removed);
    void addObject(BasicObject added);
}
