package com.one.Structure.collision.Figures;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.Objects.Module.Transform;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Objects.CollisionObject;
import com.one.Structure.collision.Objects.NoPhysicalObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class RectCollision<T extends BasicObject> extends Module<T> implements RectInterface {
    //положение
    public Vector2 position=new Vector2();
    public Vector2 size=new Vector2();
    public float rotate;

    //Настройки для линий
    float lengthLine, angleLine;
    LineCollision<RectCollision<T>> lineCollision=new LineCollision<>(this);
    public LineInterface[] lines = new LineInterface[4];

    //конструктор
    public RectCollision(T master) {
        super(master);

        for(int i=0; i<4; i++){
            lines[i]=new LineCollision<>(this);
        }
    }

    @Override
    public void update() {
        setPosition(transform.getPosition().x, transform.getPosition().y, transform.getRotate());
        setSize(transform.getSize().x, transform.getSize().y);
    }

    public void setSize(float width, float height){
        if(size.x==width && size.y==height)return;

        size.x=width;
        size.y=height;

        Vector2 vector = new Vector2(width/2, height/2);
        angleLine=vector.angleRad();
        lengthLine=vector.len();

        updateLinePosition();
    }

    public void setPosition(float x, float y, float angle){
        if(position.x==x && position.y==y && rotate==angle) return;

        position.x=x;
        position.y=y;
        rotate=angle;

        updateLinePosition();
    }

    private void updateLinePosition(){
        float plusX= (float) (lengthLine*cos(rotate+angleLine));
        float minesX= (float) (lengthLine*cos(rotate-angleLine));

        float plusY= (float) (lengthLine*sin(rotate+angleLine));
        float minesY= (float) (lengthLine*sin(rotate-angleLine));

        lines[0].setCoordinates(position.x-plusX, position.y-plusY, position.x+minesX, position.y+minesY);
        lines[1].setCoordinates(position.x+minesX, position.y+minesY, position.x+plusX, position.y+plusY);
        lines[2].setCoordinates(position.x+plusX, position.y+plusY, position.x-minesX, position.y-minesY);
        lines[3].setCoordinates(position.x-minesX, position.y-minesY, position.x-plusX, position.y-plusY);
    }

    //обнаружение пересечения
    @Override
    public boolean handlingCollision(FigureCollision collision) {
        if(collision instanceof RectInterface) return handlingCollision((RectInterface) collision);
        if(collision instanceof LineInterface) return handlingCollision((LineInterface) collision);
        return false;
    }

    public boolean handlingCollision(RectInterface other){
        float w1= (float) max(abs(lengthLine*cos(rotate+angleLine)), abs(lengthLine*cos(rotate-angleLine)));
        float h1= (float) max(abs(lengthLine*sin(rotate+angleLine)), abs(lengthLine*sin(rotate-angleLine)));

        float w2= (float) max(abs(other.getLenghtLine()*cos(other.getTransform().getRotate()+other.getAngelLine())),
            abs(other.getLenghtLine()*cos(other.getTransform().getRotate()-other.getAngelLine())));
        float h2= (float) max(abs(other.getLenghtLine()*sin(other.getTransform().getRotate()+other.getAngelLine())),
            abs(other.getLenghtLine()*sin(other.getTransform().getRotate()-other.getAngelLine())));

        if(!(position.x+w1>=other.getTransform().getPosition().x-w2 &&
            position.x-w1<=other.getTransform().getPosition().x+w2
            && position.y+h1>=other.getTransform().getPosition().y-h2 &&
            position.y-h1<=other.getTransform().getPosition().y+h2)){
            return false;
        }

        for(LineInterface i : this.lines){
            for(LineInterface j : other.getLines()){
                if(i.searchIntersectionPoint(j)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean handlingCollision(LineInterface other){
        for(LineInterface i : this.lines){
            if(i.searchIntersectionPoint(other)!=null){
                return true;
            }
        }
        return false;
    }

    //отрисовка
    @Override
    public void draw(ShapeDrawer drawer){
        for (LineInterface i : lines) {
            i.draw(drawer);
        }
    }

    @Override
    public T getMaster() {
        return master;
    }

    @Override
    public float getLenghtLine() {
        return lengthLine;
    }

    @Override
    public float getAngelLine() {
        return angleLine;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public LineInterface[] getLines() {
        return lines;
    }
}
