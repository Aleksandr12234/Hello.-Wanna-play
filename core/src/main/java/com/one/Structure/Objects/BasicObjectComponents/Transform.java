package com.one.Structure.Objects.BasicObjectComponents;

import com.badlogic.gdx.math.Vector2;

public class Transform {
    public Vector2 position=new Vector2();
    public Vector2 size=new Vector2(1,1);
    public Rotate rotate=new Rotate();

    public void synchronize(Transform other){
        position=other.position;
        size=other.size;
        rotate=other.rotate;
    }
}
