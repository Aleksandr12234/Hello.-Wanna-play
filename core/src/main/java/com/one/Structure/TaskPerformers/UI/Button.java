package com.one.Structure.TaskPerformers.UI;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Button extends BasicObject {
    private boolean isAimed =false;

    public ButtonPressed pressed;
    public ButtonAimed aimed;
    public ButtonReleased released;

    public Button(float x, float y, float width, float height){
        transform.setPosition(x,y);
        transform.setSize(width, height);
        pressed = () -> {};
        aimed = () -> {};
        released = () -> {};
    }

    @Override
    public void doLogic() {
        Vector3 mouse= Stock.getTaskRunner().userInput.getMousePosition();

        boolean b1=transform.getPosition().x-transform.getSize().x/2 <= mouse.x
            && transform.getPosition().x+transform.getSize().x/2 >= mouse.x;
        boolean b2=transform.getPosition().y-transform.getSize().y/2 <= mouse.y
            && transform.getPosition().y+transform.getSize().y/2 >= mouse.y;

        if(isAimed && Stock.getTaskRunner().userInput.getMouseReleased(Input.Buttons.LEFT))released.action();
        isAimed =false;
        if(b1 && b2){
            isAimed =true;
            aimed.action();
            if(Stock.getTaskRunner().userInput.getMousePressed(Input.Buttons.LEFT)) pressed.action();
        }
    }

    @Override
    public void draw() {
        if(isAimed)Stock.getTaskRunner().serviceObjects.shapeDrawer.filledRectangle(
            transform.getPosition().x-transform.getSize().x/2, transform.getPosition().y-transform.getSize().y/2,
            transform.getSize().x, transform.getSize().y, Color.RED
        );
        else Stock.getTaskRunner().serviceObjects.shapeDrawer.filledRectangle(
            transform.getPosition().x-transform.getSize().x/2, transform.getPosition().y-transform.getSize().y/2,
            transform.getSize().x, transform.getSize().y, Color.BLUE
        );
    }
}
