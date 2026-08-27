package com.one.Structure.Objects.Module;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Animator<T extends BasicObject> extends Module<T>{
    private final Sprite[] sprites;
    private int currentFrame=0;
    private float time;
    public float timeOnFrame=0.2f;
    public boolean isLoop=false;
    public boolean pause=false;

    public Animator(T master, int widthOneSprite, int heightOneSprite, int countFrame, String path){
        super(master);
        sprites=Stock.getTaskRunner().assetsLoader.spriteList.loadSpriteList(
            widthOneSprite, heightOneSprite, countFrame, path
        );
    }

    public Animator(T master, int widthOneSprite, int heightOneSprite, String path){
        this(master, widthOneSprite, heightOneSprite, -1, path);
    }

    public Animator(T master, String path) {
        this(master, -1, -1, -1, path);
    }

    @Override
    public void draw() {
        if(!pause)time+=deltaTime;
        if(time>=timeOnFrame){
            currentFrame++;
            if(currentFrame>= sprites.length){
                if(isLoop)currentFrame=0;
                else currentFrame=sprites.length-1;
            }
            time=0;
        }

        sprites[currentFrame].setOriginCenter();
        sprites[currentFrame].setRotation(transform.rotate.getDegreesRotate());
        sprites[currentFrame].setSize(transform.size.x, transform.size.y);
        sprites[currentFrame].setCenter(transform.position.x, transform.position.y);
        sprites[currentFrame].draw(Stock.getTaskRunner().serviceObjects.batch);
    }

    public void reset(){
        currentFrame=0;
        time=0;
    }

    public boolean isEnd(){
        return currentFrame>= sprites.length;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }
}
