package com.one.Structure.TestClasses;

import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;
import com.one.Structure.Objects.UI.Button;
import com.one.Structure.Objects.UI.Text;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(true);

        addObject(new TestPhysicRect(0, 0, 0.5f, 1, true));
        addObject(new TestPhysicRect(1, 1, 1, 0.5f, true));
        addObject(new TestPhysicRect(0, 0, 1, 1, false));
        addObject(new TestControlRect(0, 0, 1f, 1f));
    }
}
