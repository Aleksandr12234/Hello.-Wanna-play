package com.one.Structure.Objects.Module;

import com.badlogic.gdx.math.Vector2;

public class Transform {
    private final Vector2 position=new Vector2();
    private final Vector2 size=new Vector2();
    private float rotate=0;
    private boolean dispose=false;

    public Vector2 getPosition(){
        return new Vector2(position);
    }

    public Transform setPosition(Vector2 position){
        this.position.set(position);
        return this;
    }

    public Vector2 getSize(){
        return new Vector2(size);
    }

    public Transform setSize(Vector2 size){
        this.size.set(size);
        return this;
    }

    public float getRotate(){
        return rotate;
    }

    public Transform setRotate(float rotate){
        this.rotate=rotate;
        return this;
    }

    public boolean getDispose(){
        return dispose;
    }

    public Transform setDispose(boolean dispose){
        this.dispose=dispose;
        return this;
    }
}
