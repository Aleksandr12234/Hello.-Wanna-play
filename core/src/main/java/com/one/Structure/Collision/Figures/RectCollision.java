package com.one.Structure.Collision.Figures;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.Objects.Module.Transform;
import space.earlygrey.shapedrawer.ShapeDrawer;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class RectCollision<T extends BasicObject> extends Module<T> implements FigureCollision {
    public ArrayList<Vector2> points=new ArrayList<>();

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
        setPosition(transform.getPosition().x, transform.getPosition().y, transform.getDegreesRotate());
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
        if(localTransform.getPosition().x==x && localTransform.getPosition().y==y && localTransform.getDegreesRotate()==angle) return;

        localTransform.setPosition(x, y);
        localTransform.setRotate(angle);

        updateLinePosition();
    }

    private void updateLinePosition(){
        float plusX= (float) (lengthLine*cos(localTransform.getRadianRotate()+angleLine));
        float minesX= (float) (lengthLine*cos(localTransform.getRadianRotate()-angleLine));

        float plusY= (float) (lengthLine*sin(localTransform.getRadianRotate()+angleLine));
        float minesY= (float) (lengthLine*sin(localTransform.getRadianRotate()-angleLine));

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
        points=new ArrayList<>();
        other.points=points;
        float w1= (float) max(abs(lengthLine*cos(localTransform.getRadianRotate()+angleLine)),
            abs(lengthLine*cos(localTransform.getRadianRotate()-angleLine)));
        float h1= (float) max(abs(lengthLine*sin(localTransform.getRadianRotate()+angleLine)),
            abs(lengthLine*sin(localTransform.getRadianRotate()-angleLine)));

        float w2= (float) max(abs(other.getLengthLine()*cos(other.getTransform().getRadianRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*cos(other.getTransform().getRadianRotate()-other.getAngelLine())));
        float h2= (float) max(abs(other.getLengthLine()*sin(other.getTransform().getRadianRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*sin(other.getTransform().getRadianRotate()-other.getAngelLine())));

        if(!(localTransform.getPosition().x+w1>=other.getTransform().getPosition().x-w2 &&
            localTransform.getPosition().x-w1<=other.getTransform().getPosition().x+w2
            && localTransform.getPosition().y+h1>=other.getTransform().getPosition().y-h2 &&
            localTransform.getPosition().y-h1<=other.getTransform().getPosition().y+h2)){
            return false;
        }

        boolean b=false;
        for(LineCollision<?> i : this.lines){
            for(LineCollision<?> j : other.getLines()){
                Vector2 vector2=i.searchIntersectionPoint(j);
                if(vector2!=null){
                    points.add(vector2);
                    b=true;
                }
            }
        }
        return b;
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

    public Transform getLocalTransform() {
        return localTransform;
    }

    public List<LineCollision<RectCollision<T>>> getLines() {
        return lines;
    }
}
