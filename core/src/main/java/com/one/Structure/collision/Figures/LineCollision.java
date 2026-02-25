package com.one.Structure.collision.Figures;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Objects.LineObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LineCollision<T extends  BasicObject> extends Module<T> implements LineInterface {
    //Поля
    public float x1,y1,x2,y2;
    float k,b;

    //настройки
    public LineCollision(T master) {super(master);}

    @Override
    public void draw(ShapeDrawer drawer){
        drawer.line(x1, y1, x2, y2, Color.RED);
    }

    @Override
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

    @Override
    public Vector2 searchIntersectionPoint(LineInterface line){
        float x=(line.getB()-b)/(k-line.getK()==0 ? 1 : k-line.getK());
        float y=k*x+b;

        boolean u1= x>=min(x1,x2) && x<=max(x1,x2);
        boolean u2= x>=min(line.getX1(),line.getX2()) && x<=max(line.getX1(),line.getX2());
        boolean u3= y>=min(y1,y2) && y<=max(y1,y2);
        boolean u4= y>=min(line.getY1(),line.getY2()) && y<=max(line.getY1(),line.getY2());

        if(u1 && u2 && u3 && u4)return new Vector2(x,y);
        return null;
    }

    @Override
    public boolean handlingCollision(FigureCollision collision) {
        if(collision instanceof RectInterface)return ((RectInterface)collision).handlingCollision(this);
        return false;
    }

    @Override
    public T getMaster() {
        return master;
    }

    @Override
    public float getK() {
        return k;
    }

    @Override
    public float getB() {
        return b;
    }

    @Override
    public float getX1() {
        return x1;
    }

    @Override
    public float getX2() {
        return x2;
    }

    @Override
    public float getY1() {
        return y1;
    }

    @Override
    public float getY2() {
        return y2;
    }
}
