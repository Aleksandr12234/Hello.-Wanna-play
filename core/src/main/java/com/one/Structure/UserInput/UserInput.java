package com.one.Structure.UserInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.one.Structure.Stock;

import java.util.LinkedList;

public class UserInput implements InputProcessor {
    //Нажата зажата и отпущена
    LinkedList<Integer> keysPressed =new LinkedList<>();
    LinkedList<Integer> keysClamped =new LinkedList<>();
    LinkedList<Integer> keysReleased =new LinkedList<>();

    LinkedList<Integer> mousePressed=new LinkedList<>();
    LinkedList<Integer> mouseClamped=new LinkedList<>();
    LinkedList<Integer> mouseReleased=new LinkedList<>();

    public UserInput(){
        Gdx.input.setInputProcessor(this);
    }

    //обновления
    public void update(){
        keysPressed.clear();
        keysReleased.clear();
        mousePressed.clear();
        mouseReleased.clear();
    }

    //геттеры
    public boolean getKeysPressed(int i){
        return keysPressed.contains(i);
    }
    public boolean getKeysClamped(int i) {
        return keysClamped.contains(i);
    }
    public boolean getKeysReleased(int i) {
        return keysReleased.contains(i);
    }
    public boolean getMousePressed(int i){
        return mousePressed.contains(i);
    }
    public boolean getMouseClamped(int i) {
        return mouseClamped.contains(i);
    }
    public boolean getMouseReleased(int i) {
        return mouseReleased.contains(i);
    }

    public Vector3 getMousePosition(){
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Stock.getTaskRunner().serviceObjects.camera.unproject(mousePos);
        return mousePos;
    }

    //Обработка нажатий
    @Override
    public boolean keyDown(int i) { //вызывается при нажатии клавиши
        keysPressed.add(i);
        if(!keysClamped.contains(i)) keysClamped.add(i);
        return true;
    }

    @Override
    public boolean keyUp(int i) { //при отпускании клавиши
        keysReleased.add(i);
        keysClamped.remove((Integer) i);
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) { //При нажатии кнопки мыши
        mousePressed.add(button);
        if(!mouseClamped.contains(button)) mouseClamped.add(button);
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) { //При отпускании кнопки мыши
        mouseReleased.add(button);
        mouseClamped.remove((Integer) button);
        return true;
    }

    @Override
    public boolean scrolled(float v, float v1) {//При повороте колеса мыши
        return false;
    }

    //То что скорее всего не понадобиться
    @Override
    public boolean touchDragged(int x, int y, int pointer) {//при перемещении мыши с нажатой кнопкой
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {//При перемещении без нажатой кнопки
        return false;
    }

    @Override
    public boolean keyTyped(char c) { //вызывается при вводе с клавиатуры символа Unicode.
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) { //при отмене нажатия сенсора (Андроид)
        return false;
    }
}
