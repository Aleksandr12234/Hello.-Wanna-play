package com.one.Structure.TestClasses;

import com.badlogic.gdx.Input;
import com.one.Structure.Stock;

public class TestControlRect extends TestPhysicRect{
    float speed=5f;

    public TestControlRect(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void doLogic() {
        super.doLogic();

        speed=5f*deltaTime;

        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.W))
            transform.position.set(transform.position.x, transform.position.y+speed);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.S))
            transform.position.set(transform.position.x, transform.position.y-speed);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.A))
            transform.position.set(transform.position.x-speed, transform.position.y);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.D))
            transform.position.set(transform.position.x+speed, transform.position.y);
    }
}
