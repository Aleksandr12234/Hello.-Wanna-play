package com.one.Structure.Collision.Objects;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Collision.Figures.RectCollision;

public class PhysicalObject<T extends BasicObject> extends NoPhysicalObject<T>{
    private Vector2 direction=new Vector2();
    public float mass=1;

    public PhysicalObject(T master, boolean isStatic) {
        super(master, isStatic);
    }

    @Override
    public void action(RectCollision<?> rectCollision) {
        if(isStatic || collision.points.size() <= 1)return;
        if(!(rectCollision.getMaster() instanceof PhysicalObject<?>))return;
        if(direction.len()==0) {
            direction= findVector(rectCollision);
            float otherMass= ((PhysicalObject<?>) rectCollision.getMaster()).mass;
            float multiple=otherMass/(mass+otherMass);
            if (((PhysicalObject<?>) rectCollision.getMaster()).isStatic) multiple=1;
            ((PhysicalObject<?>) rectCollision.getMaster()).setDirection((new Vector2(direction)).scl(multiple-1f));
            direction.scl(multiple);
        }
        transform.addPosition(direction.x, direction.y);

        direction.set(0, 0);
    }

    public void setDirection(Vector2 direction){
        this.direction=direction;
    }

    private Vector2 findVector(RectCollision<?> otherCollision){
        Vector2 normal = getNormal();
        normal.rotateDeg(90).nor();

        Vector2[] thisAngles=new Vector2[]{
            new Vector2(transform.getSize().x/2, transform.getSize().y/2),
            new Vector2(transform.getSize().x/2, -transform.getSize().y/2),
            new Vector2(-transform.getSize().x/2, -transform.getSize().y/2),
            new Vector2(-transform.getSize().x/2, transform.getSize().y/2),
        };
        float thisCenter=transform.getPosition().dot(normal);
        float minAngle=thisCenter;
        float maxAngle=thisCenter;
        for (Vector2 i : thisAngles) {
            i.setAngleRad(i.angleRad()+transform.getRadianRotate());
            i.add(transform.getPosition().x, transform.getPosition().y);
            float angle=i.dot(normal);
            minAngle=Math.min(minAngle, angle);
            maxAngle=Math.max(maxAngle, angle);
        }

        float otherX=otherCollision.getTransform().getPosition().x;
        float otherY=otherCollision.getTransform().getPosition().y;
        float otherWidth=otherCollision.getMaster().getTransform().getSize().x/2;
        float otherHeight=otherCollision.getMaster().getTransform().getSize().y/2;
        float otherRotate=otherCollision.getTransform().getRadianRotate();
        Vector2[] otherAngle=new Vector2[]{
            new Vector2(otherWidth, otherHeight),
            new Vector2(otherWidth, -otherHeight),
            new Vector2(-otherWidth, -otherHeight),
            new Vector2(-otherWidth, otherHeight)
        };
        float otherCenter=otherCollision.getTransform().getPosition().dot(normal);
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
}
