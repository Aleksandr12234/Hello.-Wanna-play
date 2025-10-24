package com.one.Structure.collision.Figures;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.collision.Objects.PhysicalObject;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static java.lang.Math.*;

public class RectCollision {
    //тот кто использует
    public PhysicalObject master;

    //положение
    public Vector2 position=new Vector2();
    public Vector2 size=new Vector2();
    public float rotate;

    //Настройки для линий
    float lengthLine, angleLine;
    public LineCollision[] lines=new LineCollision[4];

    //конструктор
    public RectCollision(PhysicalObject master) {
        this.master = master;

        for(int i=0; i<lines.length; i++){
            lines[i]=new LineCollision(this.master);
        }

        setSize(master.size.x, master.size.y);
        setPosition(master.position.x, master.position.y, master.rotate);
    }

    //изменения
    public void update(){
        setSize(master.size.x, master.size.y);
        setPosition(master.position.x, master.position.y, master.rotate);
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
    public boolean handlingCollision(RectCollision other){
        float w1= (float) max(abs(lengthLine*cos(rotate+angleLine)), abs(lengthLine*cos(rotate-angleLine)));
        float h1= (float) max(abs(lengthLine*sin(rotate+angleLine)), abs(lengthLine*sin(rotate-angleLine)));

        float w2= (float) max(abs(other.lengthLine*cos(other.rotate+other.angleLine)), abs(other.lengthLine*cos(other.rotate-other.angleLine)));
        float h2= (float) max(abs(other.lengthLine*sin(other.rotate+other.angleLine)), abs(other.lengthLine*sin(other.rotate-other.angleLine)));

        if(!(position.x+w1>=other.position.x-w2 && position.x-w1<=other.position.x+w2
            && position.y+h1>=other.position.y-h2 && position.y-h1<=other.position.y+h2)){
            return false;
        }

        for(LineCollision i : this.lines){
            for(LineCollision j : other.lines){
                if(i.searchIntersectionPoint(j)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean handlingCollision(LineCollision other){
        for(LineCollision i : this.lines){
            if(i.searchIntersectionPoint(other)!=null){
                return true;
            }
        }
        return false;
    }

    public void draw(ShapeDrawer drawer){
        for (LineCollision i : lines) {
            i.draw(drawer);
        }

    }
}
