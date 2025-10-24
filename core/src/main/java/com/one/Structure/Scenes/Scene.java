package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;

import java.util.ArrayList;

public interface Scene {
    void setup();
    void removeObject(BasicObject removed);
    void addObject(BasicObject added);
    void addObject(BasicObject undo ,BasicObject added);
    ArrayList<BasicObject> getObjects();
}
