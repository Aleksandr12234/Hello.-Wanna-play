package com.one.Structure.collision.Figures;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.Objects.Module.Transform;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class RectCollision<T extends BasicObject> extends Module<T> implements FigureCollision {
    private final Transform localTransform=new Transform();

    //Настройки для линий
    float lengthLine, angleLine;
    List<LineCollision<RectCollision<T>>> lines = new ArrayList<>();

    //конструктор
    public RectCollision(T master) {
        super(master);

        for(int i=0; i<4; i++){
            lines.add(new LineCollision<>(this));
        }
    }

    @Override
    public void doLogic() {
        setPosition(transform.getPosition().x, transform.getPosition().y, transform.getRotate());
        setSize(transform.getSize().x, transform.getSize().y);
    }

    public void setSize(float width, float height){
        if(localTransform.getSize().x==width && localTransform.getSize().y==height)return;

        localTransform.getSize().x=width;
        localTransform.getSize().y=height;

        Vector2 vector = new Vector2(width/2, height/2);
        angleLine=vector.angleRad();
        lengthLine=vector.len();

        updateLinePosition();
    }

    public void setPosition(float x, float y, float angle){
        if(localTransform.getPosition().x==x && localTransform.getPosition().y==y && localTransform.getRotate()==angle) return;

        localTransform.setPosition(x, y);
        localTransform.setRotate(angle);

        updateLinePosition();
    }

    private void updateLinePosition(){
        float plusX= (float) (lengthLine*cos(localTransform.getRotate()+angleLine));
        float minesX= (float) (lengthLine*cos(localTransform.getRotate()-angleLine));

        float plusY= (float) (lengthLine*sin(localTransform.getRotate()+angleLine));
        float minesY= (float) (lengthLine*sin(localTransform.getRotate()-angleLine));

        lines.get(0).setCoordinates(localTransform.getPosition().x-plusX, localTransform.getPosition().y-plusY,
            localTransform.getPosition().x+minesX, localTransform.getPosition().y+minesY);
        lines.get(1).setCoordinates(localTransform.getPosition().x+minesX, localTransform.getPosition().y+minesY,
            localTransform.getPosition().x+plusX, localTransform.getPosition().y+plusY);
        lines.get(2).setCoordinates(localTransform.getPosition().x+plusX, localTransform.getPosition().y+plusY,
            localTransform.getPosition().x-minesX, localTransform.getPosition().y-minesY);
        lines.get(3).setCoordinates(localTransform.getPosition().x-minesX, localTransform.getPosition().y-minesY,
            localTransform.getPosition().x-plusX, localTransform.getPosition().y-plusY);
    }

    //обнаружение пересечения
    @Override
    public boolean handlingCollision(FigureCollision collision) {
        if(collision instanceof RectCollision<?>) return handlingCollision((RectCollision<?>) collision);
        if(collision instanceof LineCollision<?>) return handlingCollision((LineCollision<?>) collision);
        return false;
    }

    public boolean handlingCollision(RectCollision<?> other){
        float w1= (float) max(abs(lengthLine*cos(localTransform.getRotate()+angleLine)),
            abs(lengthLine*cos(localTransform.getRotate()-angleLine)));
        float h1= (float) max(abs(lengthLine*sin(localTransform.getRotate()+angleLine)),
            abs(lengthLine*sin(localTransform.getRotate()-angleLine)));

        float w2= (float) max(abs(other.getLengthLine()*cos(other.getTransform().getRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*cos(other.getTransform().getRotate()-other.getAngelLine())));
        float h2= (float) max(abs(other.getLengthLine()*sin(other.getTransform().getRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*sin(other.getTransform().getRotate()-other.getAngelLine())));

        if(!(localTransform.getPosition().x+w1>=other.getTransform().getPosition().x-w2 &&
            localTransform.getPosition().x-w1<=other.getTransform().getPosition().x+w2
            && localTransform.getPosition().y+h1>=other.getTransform().getPosition().y-h2 &&
            localTransform.getPosition().y-h1<=other.getTransform().getPosition().y+h2)){
            return false;
        }

        for(LineCollision<?> i : this.lines){
            for(LineCollision<?> j : other.getLines()){
                if(i.searchIntersectionPoint(j)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean handlingCollision(LineCollision<?> other){
        for(LineCollision<?> i : this.lines){
            if(i.searchIntersectionPoint(other)!=null){
                return true;
            }
        }
        return false;
    }

    //отрисовка
    @Override
    public void draw(ShapeDrawer drawer){
        for (LineCollision<?> i : lines) {
            i.draw(drawer);
        }
    }

    @Override
    public boolean isActive() {
        return transform.getActive();
    }

    @Override
    public boolean getDispose() {
        return transform.getDispose();
    }

    public float getLengthLine() {
        return lengthLine;
    }

    public float getAngelLine() {
        return angleLine;
    }

    public Transform getTransform() {
        return localTransform;
    }

    public List<LineCollision<RectCollision<T>>> getLines() {
        return lines;
    }
}
