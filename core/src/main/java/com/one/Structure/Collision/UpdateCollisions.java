package com.one.Structure.Collision;

import com.one.Structure.Stock;
import com.one.Structure.Collision.Figures.FigureCollision;
import com.one.Structure.Collision.Objects.Body;

import java.util.ArrayList;

public class UpdateCollisions {
    ArrayList<FigureCollision> activeCollision=new ArrayList<>();
    ArrayList<FigureCollision> staticCollision=new ArrayList<>();

    public void update(){
        for(int i=activeCollision.size()-1; i>=0; i--){
            FigureCollision one=activeCollision.get(i);
            if(one.getDispose()){
                activeCollision.remove(i);
                continue;
            }
            if(!one.isActive())continue;
            one.doLogic();

            for (int j = i-1; j >=0 ; j--) {
                FigureCollision two= activeCollision.get(j);
                if(two.getDispose() || !two.isActive())continue;
                two.doLogic();
                if(one.handlingCollision(two)){
                    action(one, two);
                }
            }

            for (int j = staticCollision.size()-1; j>=0 ; j--) {
                FigureCollision two = staticCollision.get(j);
                if(two.getDispose()){
                    staticCollision.remove(j);
                    continue;
                }
                if(!two.isActive())continue;
                two.doLogic();
                if(one.handlingCollision(two)){
                    action(one, two);
                }
            }
        }

    }

    private void action(FigureCollision one, FigureCollision two){
        ((Body)one.getMaster()).action(two);
        ((Body)two.getMaster()).action(one);
    }

    public void drawCollisions(){
        for (FigureCollision i : staticCollision) {
            if(i.isActive())
                i.draw(Stock.getTaskRunner().serviceObjects.shapeDrawer);
        }

        for (FigureCollision i : activeCollision) {
            if(i.isActive())
                i.draw(Stock.getTaskRunner().serviceObjects.shapeDrawer);
        }
    }

    public void dispose(){
        activeCollision.clear();
        staticCollision.clear();
    }

    public void addStaticCollision(FigureCollision collision){staticCollision.add(collision);}
    public void addActiveCollision(FigureCollision collision){activeCollision.add(collision);}
}
