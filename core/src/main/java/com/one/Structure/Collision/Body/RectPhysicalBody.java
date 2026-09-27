package com.one.Structure.Collision.Body;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Collision.Figures.RectCollision;
import lombok.Setter;

public class RectPhysicalBody<T extends BasicObject> extends RectBody<T> {
    @Setter private Vector2 direction=new Vector2();
    public float mass=1;

    public RectPhysicalBody(T master, boolean isStatic) {
        super(master, isStatic);
    }

    @Override
    public void doLogic() {
        super.doLogic();
    }

    @Override
    public void action(RectCollision<?> rectCollision) {
        if(isStatic || !(rectCollision.getMaster() instanceof RectPhysicalBody<?>))return;

        if(collision.points.isEmpty() ){
            direction=RemoveFromInside(rectCollision);
            if(direction!=null) {
                getMaster().getTransform().position.add(direction.x, direction.y);
                collision.handlingCollision(rectCollision);

            }
            direction=new Vector2();
        }

        if(collision.points.size()<=1)return;

        if(direction.len()==0) {
            direction= findVector(rectCollision);
            float otherMass= ((RectPhysicalBody<?>) rectCollision.getMaster()).mass;
            float multiple=otherMass/(mass+otherMass);
            if (((RectPhysicalBody<?>) rectCollision.getMaster()).isStatic) multiple=1;
            ((RectPhysicalBody<?>) rectCollision.getMaster()).setDirection((new Vector2(direction)).scl(multiple-1f));
            direction.scl(multiple);
        }
        getMaster().getTransform().position.add(direction.x, direction.y);

        direction.set(0, 0);
    }

    private Vector2 findVector(RectCollision<?> otherCollision){
        Vector2 normal = getNormal();
        normal.rotateDeg(90).nor();

        Vector2[] thisAngles=new Vector2[]{
            new Vector2(transform.size.x/2, transform.size.y/2),
            new Vector2(transform.size.x/2, -transform.size.y/2),
            new Vector2(-transform.size.x/2, -transform.size.y/2),
            new Vector2(-transform.size.x/2, transform.size.y/2),
        };
        float thisCenter=transform.position.dot(normal);
        float minAngle=thisCenter;
        float maxAngle=thisCenter;
        for (Vector2 i : thisAngles) {
            i.setAngleRad(i.angleRad()+transform.rotate.getRadianRotate());
            i.add(transform.position.x, transform.position.y);
            float angle=i.dot(normal);
            minAngle=Math.min(minAngle, angle);
            maxAngle=Math.max(maxAngle, angle);
        }

        float otherX=otherCollision.getTransform().position.x;
        float otherY=otherCollision.getTransform().position.y;
        float otherWidth=otherCollision.getMaster().getTransform().size.x/2;
        float otherHeight=otherCollision.getMaster().getTransform().size.y/2;
        float otherRotate=otherCollision.getTransform().rotate.getRadianRotate();
        Vector2[] otherAngle=new Vector2[]{
            new Vector2(otherWidth, otherHeight),
            new Vector2(otherWidth, -otherHeight),
            new Vector2(-otherWidth, -otherHeight),
            new Vector2(-otherWidth, otherHeight)
        };
        float otherCenter=otherCollision.getTransform().position.dot(normal);
        float otherMinAngle=otherCenter;
        float otherMaxAngle=otherCenter;
        for (Vector2 i : otherAngle) {
            i.setAngleRad(i.angleRad()+otherRotate);
            i.add(otherX, otherY);
            float angle=i.dot(normal);
            otherMinAngle=Math.min(otherMinAngle, angle);
            otherMaxAngle=Math.max(otherMaxAngle, angle);
        }

        float length=Math.abs(maxAngle-minAngle)+Math.abs(otherMaxAngle-otherMinAngle)
            -Math.abs(Math.max(maxAngle, otherMaxAngle)-Math.min(minAngle, otherMinAngle));

        byte direction;
        if(thisCenter<otherCenter)direction=-1;
        else direction=1;

        length*=direction;
        return new Vector2(normal.x*length, normal.y*length);
    }

    private Vector2 getNormal() {
        Vector2 point1=collision.points.get(0);
        Vector2 point2=collision.points.get(1);
        Vector2 lengthBetweenPoints=new Vector2(point1.x-point2.x, point1.y-point2.y);
        if(collision.points.size()>2){
            for (int i=0; i<collision.points.size(); i++) {
                point1=collision.points.get(i);
                for (int j = 0; j < collision.points.size(); j++) {
                    if(i==j)continue;
                    point2=collision.points.get(j);
                    Vector2 vector2=new Vector2(point1.x-point2.x, point1.y-point2.y);
                    if(vector2.len()>lengthBetweenPoints.len())lengthBetweenPoints=new Vector2(vector2);
                }
            }
        }
        return lengthBetweenPoints;
    }

    private Vector2 RemoveFromInside(RectCollision<?> otherCollision){
        float otherX=otherCollision.getTransform().position.x;
        float otherY=otherCollision.getTransform().position.y;
        float otherWidth=otherCollision.getMaster().getTransform().size.x/2;
        float otherHeight=otherCollision.getMaster().getTransform().size.y/2;
        float otherRotate=otherCollision.getTransform().rotate.getRadianRotate();

        if(!((RectPhysicalBody<?>) otherCollision.getMaster()).isStatic &&
            otherHeight*otherWidth<transform.size.x*transform.size.y)return null;

        Vector2[] points=new Vector2[]{
            new Vector2(otherWidth, otherHeight),
            new Vector2(otherWidth, -otherHeight),
            new Vector2(-otherWidth, -otherHeight),
            new Vector2(-otherWidth, otherHeight),
            new Vector2(otherWidth, 0),
            new Vector2(0, -otherHeight),
            new Vector2(-otherWidth, 0),
            new Vector2(0, otherHeight)
        };

        Vector2 result=new Vector2();

        for (Vector2 i : points){
            i.setAngleRad(i.angleRad()+otherRotate);
            i.add(otherX, otherY);
            Vector2 a=new Vector2(i.x-transform.position.x, i.y-transform.position.y);
            if(result.len()==0 || a.len()<result.len())result=a;
        }

        return result;
    }
}
