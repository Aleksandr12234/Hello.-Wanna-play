package com.one.Structure.Objects.Module;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Animator<T extends BasicObject> extends Module<T>{
    private final Sprite[] sprites;
    private int currentFrame=0;
    private float time;
    public float timeOnFrame=0.2f;

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
        time+=deltaTime;
        if(time>=timeOnFrame){
            currentFrame++;
            if(currentFrame>= sprites.length)currentFrame=0;
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
}
