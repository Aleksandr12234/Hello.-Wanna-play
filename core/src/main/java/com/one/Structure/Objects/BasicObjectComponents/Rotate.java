package com.one.Structure.Objects.BasicObjectComponents;

public class Rotate {
    private float rotate=0;

    public float getDegreesRotate(){
        return rotate;
    }
    public float getRadianRotate(){
        return (float) Math.toRadians(rotate);
    }
    public void setRotate(float angleDeg){
        this.rotate=angleDeg;
    }
    public void addDegreesRotate(float angleDeg){
        rotate+=angleDeg;
    }
}
