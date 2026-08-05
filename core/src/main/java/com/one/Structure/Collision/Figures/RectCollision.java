package com.one.Structure.Collision.Figures;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module.Module;
import com.one.Structure.Objects.BasicObjectComponents.Transform;
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

        localTransform.size.set(0, 0);

        for(int i=0; i<4; i++){
            lines.add(new LineCollision<>(this));
        }
    }

    @Override
    public void doLogic() {
        super.doLogic();
        setPosition(transform.position.x, transform.position.y, transform.rotate.getDegreesRotate());
        setSize(transform.size.x, transform.size.y);
    }

    public void setSize(float width, float height){
        if(localTransform.size.x==width && localTransform.size.y==height)return;

        localTransform.size.x=width;
        localTransform.size.y=height;

        Vector2 vector = new Vector2(width/2, height/2);
        angleLine=vector.angleRad();
        lengthLine=vector.len();

        updateLinePosition();
    }

    public void setPosition(float x, float y, float angle){
        if(localTransform.position.x==x && localTransform.position.y==y &&
            localTransform.rotate.getDegreesRotate()==angle) return;

        localTransform.position.set(x, y);
        localTransform.rotate.setRotate(angle);

        updateLinePosition();
    }

    private void updateLinePosition(){
        float plusX= (float) (lengthLine*cos(localTransform.rotate.getRadianRotate()+angleLine));
        float minesX= (float) (lengthLine*cos(localTransform.rotate.getRadianRotate()-angleLine));

        float plusY= (float) (lengthLine*sin(localTransform.rotate.getRadianRotate()+angleLine));
        float minesY= (float) (lengthLine*sin(localTransform.rotate.getRadianRotate()-angleLine));

        lines.get(0).setCoordinates(localTransform.position.x-plusX, localTransform.position.y-plusY,
            localTransform.position.x+minesX, localTransform.position.y+minesY);
        lines.get(1).setCoordinates(localTransform.position.x+minesX, localTransform.position.y+minesY,
            localTransform.position.x+plusX, localTransform.position.y+plusY);
        lines.get(2).setCoordinates(localTransform.position.x+plusX, localTransform.position.y+plusY,
            localTransform.position.x-minesX, localTransform.position.y-minesY);
        lines.get(3).setCoordinates(localTransform.position.x-minesX, localTransform.position.y-minesY,
            localTransform.position.x-plusX, localTransform.position.y-plusY);
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

        float w1= (float) max(abs(lengthLine*cos(localTransform.rotate.getRadianRotate()+angleLine)),
            abs(lengthLine*cos(localTransform.rotate.getRadianRotate()-angleLine)));
        float h1= (float) max(abs(lengthLine*sin(localTransform.rotate.getRadianRotate()+angleLine)),
            abs(lengthLine*sin(localTransform.rotate.getRadianRotate()-angleLine)));

        float w2= (float) max(abs(other.getLengthLine()*cos(other.getTransform().rotate.getRadianRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*cos(other.getTransform().rotate.getRadianRotate()-other.getAngelLine())));
        float h2= (float) max(abs(other.getLengthLine()*sin(other.getTransform().rotate.getRadianRotate()+other.getAngelLine())),
            abs(other.getLengthLine()*sin(other.getTransform().rotate.getRadianRotate()-other.getAngelLine())));

        if(!(localTransform.position.x+w1>=other.getTransform().position.x-w2 &&
            localTransform.position.x-w1<=other.getTransform().position.x+w2
            && localTransform.position.y+h1>=other.getTransform().position.y-h2 &&
            localTransform.position.y-h1<=other.getTransform().position.y+h2)){
            return false;
        }

        LineCollision<RectCollision<T>> lineCollision=new LineCollision<>(this);
        lineCollision.setCoordinates(localTransform.position.x, localTransform.position.y,
            other.getTransform().position.x, other.getTransform().position.y);

        Vector2 point1 = null;

        boolean b=false;
        for(LineCollision<?> i : this.lines){
            point1=lineCollision.searchIntersectionPoint(i);

            for(LineCollision<?> j : other.getLines()){
                Vector2 vector2=i.searchIntersectionPoint(j);
                if(vector2!=null){
                    points.add(vector2);
                    b=true;
                }
            }
        }

        if(point1==null && !b)return true;
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
        return status.isActive;
    }

    @Override
    public boolean getDispose() {
        return status.dispose;
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
