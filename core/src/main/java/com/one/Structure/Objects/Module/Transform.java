package com.one.Structure.Objects.Module;

import com.badlogic.gdx.math.Vector2;

public class Transform {
    private final Vector2 position=new Vector2();
    private final Vector2 size=new Vector2();
    private float rotate=0;
    private boolean dispose=false;
    private boolean isActive=true;
    private boolean isDraw=true;

    public Vector2 getPosition(){
        return new Vector2(position);
    }
    public void setPosition(Vector2 position){
        this.position.set(position);
    }
    public void setPosition(float x, float y){
        this.position.set(x, y);
    }
    public void addPosition(float x, float y){
        this.position.add(x, y);
    }

    public Vector2 getSize(){
        return new Vector2(size);
    }
    public void setSize(Vector2 size){
        this.size.set(size);
    }
    public void setSize(float width, float height){this.size.set(width, height);}

    public float getDegreesRotate(){
        return rotate;
    }
    public float getRadianRotate(){
        return (float) Math.toRadians(rotate);
    }
    public void setRotate(float angleDeg){
        this.rotate=angleDeg;
    }
    public void addDegreesRotate(float angleDeg){rotate+=angleDeg;}

    public boolean getDispose(){
        return dispose;
    }
    public void setDispose(boolean dispose){
        this.dispose=dispose;
    }

    public boolean getActive(){
        return isActive;
    }
    public void setActive(boolean isActive){
        this.isActive=isActive;
    }

    public boolean getDraw(){return isDraw;}
    public void setDraw(boolean isDraw){this.isDraw=isDraw;}
}
