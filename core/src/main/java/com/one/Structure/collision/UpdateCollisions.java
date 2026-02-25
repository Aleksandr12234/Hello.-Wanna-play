package com.one.Structure.collision;

import com.one.Structure.Manager;
import com.one.Structure.collision.Figures.FigureCollision;
import com.one.Structure.collision.Figures.FigureInterface.LineInterface;
import com.one.Structure.collision.Figures.FigureInterface.RectInterface;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;

import java.util.ArrayList;

public class UpdateCollisions {
    ArrayList<FigureCollision> activeCollision=new ArrayList<>();
    ArrayList<FigureCollision> staticCollision=new ArrayList<>();

    public void update(){
        for(int i=activeCollision.size()-1; i>=0; i--){
            FigureCollision one=activeCollision.get(i);
            if(one.getMaster().getDispose()){
                activeCollision.remove(i);
                continue;
            }
            one.update();

            for (int j = i-1; j >=0 ; j--) {
                FigureCollision two= activeCollision.get(j);
                if(two.getMaster().getDispose())continue;
                two.update();
                if(one.handlingCollision(two)){
                    action(one, two);
                }
            }

            for (int j = staticCollision.size()-1; j>=0 ; j--) {
                FigureCollision two = staticCollision.get(j);
                if(two.getMaster().getDispose()){
                    staticCollision.remove(j);
                    continue;
                }
                two.update();
                if(one.handlingCollision(two)){
                    action(one, two);
                }
            }
        }

    }

    private void action(FigureCollision one, FigureCollision two){
        if(two instanceof RectInterface)one.getMaster().action((RectInterface) two);
        else one.getMaster().action((LineInterface) two);

        if(one instanceof RectCollision) two.getMaster().action((RectCollision) one);
        else two.getMaster().action((LineCollision) one);
    }

    public void drawCollisions(){
        for (FigureCollision i : staticCollision) {
            i.draw(Manager.serviceObjects.shapeDrawer);
        }

        for (FigureCollision i : activeCollision) {
            i.draw(Manager.serviceObjects.shapeDrawer);
        }
    }

    public void addStaticCollision(FigureCollision collision){staticCollision.add(collision);}
    public void addActiveCollision(FigureCollision collision){activeCollision.add(collision);}
}
