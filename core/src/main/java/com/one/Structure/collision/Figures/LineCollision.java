package com.one.Structure.collision.Figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.collision.Objects.CollisionObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LineCollision {
    public CollisionObject master;

    public float x1,y1,x2,y2;
    float k,b;

    public LineCollision(CollisionObject master)
    {
        this.master=master;
    }

    public void update(){
        Vector2 vector=new Vector2(master.size.x, master.size.y);
        setCoordinates(master.position.x, master.position.y,
                   master.position.x-vector.x, master.position.y-vector.y);
    }

    public void setCoordinates(float x1,float y1, float x2, float y2){
        if((int)(x1)==(int)(x2))x1+=1;
        if((int)(y1)==(int)(y2))y1+=1;

        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;

        k=(y2-y1)/(x2-x1);
        b=y1-k*x1;
    }

    Vector2 searchIntersectionPoint(LineCollision line){
        float x=(k*x1-line.k*line.x1+line.y1-y1)/(k-line.k==0 ? 1 : k-line.k);
        float y=k*x+b;

        boolean u1= x>=min(x1,x2) && x<=max(x1,x2);
        boolean u2= x>=min(line.x1,line.x2) && x<=max(line.x1,line.x2);
        boolean u3= y>=min(y1,y2) && y<=max(y1,y2);
        boolean u4= y>=min(line.y1,line.y2) && y<=max(line.y1,line.y2);

        if(u1 && u2 && u3 && u4)return new Vector2(x,y);
        return null;
    }

    public void draw(ShapeDrawer drawer){
        drawer.line(x1, y1, x2, y2, Color.GREEN);
    }
}
