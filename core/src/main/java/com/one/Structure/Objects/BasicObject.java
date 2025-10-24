package com.one.Structure.Objects;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Manager;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BasicObject {
    public static Manager manager;
    public static ShapeDrawer shapeDrawer;
    public boolean dispose=false;
    public Vector2 position=new Vector2(0, 0);
    public Vector2 size=new Vector2(0, 0);
    public float rotate;
    public static float deltaTime;

    public void draw(){}
    public void doLogic(){}
}
