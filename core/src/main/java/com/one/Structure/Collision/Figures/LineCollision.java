package com.one.Structure.Collision.Figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import space.earlygrey.shapedrawer.ShapeDrawer;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class LineCollision<T extends  BasicObject> extends Module<T> implements FigureCollision {
    //Поля
    private float x1,y1,x2,y2;
    private float k,b;

    //настройки
    public LineCollision(T master) {super(master);}

    @Override
    public void draw(ShapeDrawer drawer){
        drawer.line(x1, y1, x2, y2, Color.GREEN);
    }

    @Override
    public boolean isActive() {
        return status.isActive;
    }

    @Override
    public boolean getDispose() {
        return status.dispose;
    }

    public void setCoordinates(float x1,float y1, float x2, float y2){
        if(x1==x2)x1+=0.01f;
        if(y1==y2)y1+=0.01f;

        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;

        k=(y2-y1)/(x2-x1);
        b=y1-k*x1;
    }

    public Vector2 searchIntersectionPoint(LineCollision<?> line){
        float x=(line.getB()-b)/(k-line.getK()==0 ? 1 : k-line.getK());
        float y=k*x+b;

        boolean u1= x>=min(x1,x2) && x<=max(x1,x2);
        boolean u2= x>=min(line.getX1(),line.getX2()) && x<=max(line.getX1(),line.getX2());
        boolean u3= y>=min(y1,y2) && y<=max(y1,y2);
        boolean u4= y>=min(line.getY1(),line.getY2()) && y<=max(line.getY1(),line.getY2());

        if(u1 && u2 && u3 && u4){
            return new Vector2(x,y);
        }
        return null;
    }

    @Override
    public boolean handlingCollision(FigureCollision collision) {
        if(collision instanceof RectCollision<?>)return ((RectCollision<?>)collision).handlingCollision(this);
        return false;
    }

    public float getK() {
        return k;
    }

    public float getB() {
        return b;
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }
}
