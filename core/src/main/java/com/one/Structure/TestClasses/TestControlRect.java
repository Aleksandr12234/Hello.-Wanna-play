package com.one.Structure.TestClasses;

import com.badlogic.gdx.Input;
import com.one.Structure.Stock;

public class TestControlRect extends TestPhysicRect{
    float speed=5f;

    public TestControlRect(float x, float y, float width, float height) {
        super(x, y, width, height);
        transform.setRotate(0);
    }

    @Override
    public void doLogic() {
        super.doLogic();

        speed=5f*deltaTime;

        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.W))
            transform.setPosition(transform.getPosition().x, transform.getPosition().y+speed);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.S))
            transform.setPosition(transform.getPosition().x, transform.getPosition().y-speed);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.A))
            transform.setPosition(transform.getPosition().x-speed, transform.getPosition().y);
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.D))
            transform.setPosition(transform.getPosition().x+speed, transform.getPosition().y);
    }
}
