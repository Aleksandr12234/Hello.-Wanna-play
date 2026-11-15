package com.one.Project.Scenes;

import com.one.Project.Objects.Akari.Akari;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class DefaultDevelopScene extends EmptyScene {
    @Override
    public void setup() {
        Stock.getTaskRunner().drawer.setDrawCollision(true);
        Stock.getTaskRunner().drawer.setBackgroundColor(0.5f, 0.5f, 0.5f);

        addObject(new Akari());
    }
}
