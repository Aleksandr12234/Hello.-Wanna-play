package com.one.Structure.collision;

import com.one.Structure.Manager;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

import java.util.ArrayList;

public class UpdateCollisions {
    ArrayList<RectCollision> rectCollisions=new ArrayList<>();
    ArrayList<LineCollision> lineCollisions=new ArrayList<>();

    public void update(){
        for (int i=rectCollisions.size()-1; i>=0; i--) {
            RectCollision one=rectCollisions.get(i);
            if(one.master.dispose){
                rectCollisions.remove(i);
                continue;
            }
            one.update();

            //rect - rect
            for(int j=rectCollisions.size()-1; j>=0; j--){
                if(i==j)continue;
                RectCollision two = rectCollisions.get(j);
                two.update();

                if(one.handlingCollision(two)){
                    one.master.action(two);
                    //two.master.action(one);
                }
            }

            //rect - line
            for (int j= lineCollisions.size()-1; j>=0; j--) {
                LineCollision two=lineCollisions.get(j);
                if(two.master.dispose){
                    lineCollisions.remove(j);
                    continue;
                }

                two.update();
                if(one.handlingCollision(two)){
                    one.master.action(two);
                    two.master.action(one);
                }
            }
        }
    }

    public void drawCollisions(){
        for (RectCollision i : rectCollisions) {
            i.draw(Manager.serviceObjects.shapeDrawer);
        }

        for (LineCollision i : lineCollisions) {
            i.draw(Manager.serviceObjects.shapeDrawer);
        }

    }

    public void addCollision(RectCollision rectCollision){rectCollisions.add(rectCollision);}
    public void addCollision(LineCollision lineCollision){lineCollisions.add(lineCollision);}
}
