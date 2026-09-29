package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Layer {
    private final LinkedHashSet<Layer> masterLayers=new LinkedHashSet<>();
    private final LinkedHashSet<Layer> childLayers=new LinkedHashSet<>();

    public List<BasicObject> objects=new ArrayList<>();
    public byte number=0;
    public boolean save=false;

    private boolean isActive=true, isDraw=true;

    public void changeActive(boolean isActive, boolean isDraw){
        for (BasicObject i : objects) {
            i.getStatus().isActive=isActive;
            i.getStatus().isDraw=isDraw;
        }
        for (Layer i : childLayers) {
            i.changeActive(isActive, isDraw);
        }

        this.isActive=isActive;
        this.isDraw=isDraw;
    }

    public void changeActive(boolean isActive){
        changeActive(isActive, isActive);
    }

    public List<BasicObject> getObjects() {
        return new ArrayList<>(objects);
    }

    public void addChildLayer(String layerName){
        if(!Stock.getTaskRunner().scenesManager.activeScene.layers.containsKey(layerName))return;

        Layer layer=Stock.getTaskRunner().scenesManager.activeScene.layers.get(layerName);
        if(masterLayers.contains(layer))return;

        childLayers.add(layer);
        layer.masterLayers.add(this);
        layer.changeActive(isActive, isDraw);
    }

    public void removeChildLayer(String layerName){
        if(!Stock.getTaskRunner().scenesManager.activeScene.layers.containsKey(layerName))return;

        Layer layer=Stock.getTaskRunner().scenesManager.activeScene.layers.get(layerName);
        layer.masterLayers.remove(this);
        childLayers.remove(layer);
    }

    public boolean childLayersIsEmpty(){
        return childLayers.isEmpty();
    }

    public void dispose(){
        for (Layer i : masterLayers) {
            i.childLayers.remove(this);
        }
    }

    public void addObject(BasicObject object){
        objects.add(object);
        object.getStatus().isActive=isActive;
        object.getStatus().isDraw=isDraw;
        object.setup();
    }

    public void addObject(int index, BasicObject object){
        objects.add(index, object);
        object.getStatus().isActive=isActive;
        object.getStatus().isDraw=isDraw;
        object.setup();
    }
}
