package com.one.Structure.TaskPerformers;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ScenesManager {
    @Getter private EmptyScene activeScene;
    @Getter private List<BasicObject> activeObjects=new ArrayList<>();

    private EmptyScene newScene;
    public boolean isChangeObjects=false;

    public void update(){
        if(newScene!=null){
            Stock.getTaskRunner().assetsLoader.dispose();
            Stock.getTaskRunner().updateCollisions.dispose();
            Stock.getTaskRunner().updateCollisions.setup();
            if(activeScene!=null)activeScene.dispose();
            activeScene=newScene;
            activeScene.setup();
            isChangeObjects=true;
            newScene=null;
        }

        if(isChangeObjects) {
            activeObjects=new ArrayList<>();
            TreeMap<Byte, List<BasicObject>> input = activeScene.getObjects();
            for (List<BasicObject> i : input.values()) {
                activeObjects.addAll(i);
            }
            isChangeObjects=false;
        }
    }

    public void changeActiveScene(EmptyScene newScene){
        if(this.newScene==null)this.newScene=newScene;
    }
}
