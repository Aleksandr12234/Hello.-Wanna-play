package com.one.Structure.TestClasses;

import com.badlogic.gdx.graphics.Color;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;
import com.one.Structure.TaskPerformers.Debug;

public class Rect extends BasicObject {

    public Rect(){
        transform.size.set(1, 1);
    }

    @Override
    public void doLogic() {
        super.doLogic();
    }

    @Override
    public void draw() {
        super.draw();
        Debug.figure.shapeDrawer.setColor(Color.RED);
        Debug.figure.shapeDrawer.rectangle(transform.position.x, transform.position.y,
            transform.size.x, transform.size.y);
    }
}
