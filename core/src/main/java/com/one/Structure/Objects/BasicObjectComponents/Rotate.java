package com.one.Structure.Objects.BasicObjectComponents;

public class Rotate {
    private float rotate=0;

    public float getDegreesAngle(){
        return rotate;
    }

    public float getRadianAngle(){
        return (float) Math.toRadians(rotate);
    }

    public void setDegreesAngle(float angleDeg){
        this.rotate=angleDeg;
    }

    public void setRadiansAngle(float angleRad){
        this.rotate=(float) Math.toDegrees(angleRad);
    }

    public void addDegreesAngle(float angleDeg){
        rotate+=angleDeg;
    }
}
