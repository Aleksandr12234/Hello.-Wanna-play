package com.one.Structure.UserInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.one.Structure.Manager;

import java.util.Arrays;

public class KeyBoardInput implements InputProcessor {
    //Настройка
    boolean[] keysPressed=new boolean[6];
    boolean[] keysClamped=new boolean[6];
    boolean[] keysReleased=new boolean[6];

    public KeyBoardInput(){
        Arrays.fill(keysPressed, false);
        Arrays.fill(keysClamped, false);
        Arrays.fill(keysReleased, false);

        Gdx.input.setInputProcessor(this);
    }

    //обновления
    public void update(){
        Arrays.fill(keysPressed, false);
        Arrays.fill(keysReleased, false);
    }

    //геттеры
    public boolean getKeysPressed(int i){
        return keysPressed[i];
    }

    public boolean getKeysClamped(int i) {
        return keysClamped[i];
    }

    public boolean getKeysReleased(int i) {
        return keysReleased[i];
    }

    public Vector3 getMousePosition(){
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Manager.serviceObjects.camera.unproject(mousePos);
        return mousePos;
    }

    //Обработка нажатий
    @Override
    public boolean keyDown(int i) { //вызывается при нажатии клавиши
        switch (i) {
            case Input.Keys.W:
                keysPressed[0]=true; // Движение вверх
                keysClamped[0]=true;
                break;
            case Input.Keys.S:
                keysPressed[1]=true; // Движение вниз
                keysClamped[1]=true;
                break;
            case Input.Keys.A:
                keysPressed[2]=true; // Движение влево
                keysClamped[2]=true;
                break;
            case Input.Keys.D:
                keysPressed[3]=true; // Движение вправо
                keysClamped[3]=true;
                break;
            case Input.Keys.SPACE:
                keysPressed[4]=true; //особое умение
                keysClamped[4]=true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) { //при отпускании клавиши
        switch (i) {
            case Input.Keys.W:
                keysReleased[0]=true; // Движение вверх
                keysClamped[0]=false;
                break;
            case Input.Keys.S:
                keysReleased[1]=true; // Движение вниз
                keysClamped[1]=false;
                break;
            case Input.Keys.A:
                keysReleased[2]=true; // Движение влево
                keysClamped[2]=false;
                break;
            case Input.Keys.D:
                keysReleased[3]=true; // Движение вправо
                keysClamped[3]=false;
                break;
            case Input.Keys.SPACE:
                keysReleased[4]=true; //особое умение
                keysClamped[4]=false;
                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) { //При нажатии кнопки мыши
        if(button == Input.Buttons.LEFT) {
            keysClamped[5] = true;
            keysPressed[5] = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) { //При отпускании кнопки мыши
        if(button == Input.Buttons.LEFT) {
            keysClamped[5] = false;
            keysReleased[5] = true;
        }
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
