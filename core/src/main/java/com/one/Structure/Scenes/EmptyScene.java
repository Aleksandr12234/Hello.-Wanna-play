package com.one.Structure.Scenes;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;
import java.util.*;

public abstract class EmptyScene{
    public HashMap<String, Layer> layers =new HashMap<>();

    public EmptyScene(){
        layers.put("Basic", new Layer());
    }

    public abstract void setup();

    public <T extends BasicObject> T addObject(T object){
        return addObject("Basic", object);
    }

    public <T extends BasicObject> T addObject(String layer, T object){
        if(object==null || layer==null)return null;
        if(!layers.containsKey(layer))layers.put(layer, new Layer());
        layers.get(layer).addObject(object);
        Stock.getTaskRunner().scenesManager.isChangeObjects=true;
        return object;
    }

    public <T extends BasicObject> T addObject(BasicObject undo, T object){
        if(undo==null || object==null)return null;
        for(Layer i : layers.values()){
            if(i.objects.contains(undo)){
                int index=i.objects.indexOf(undo);
                i.addObject(index+1, object);
                Stock.getTaskRunner().scenesManager.isChangeObjects=true;
                return object;
            }
        }
        return null;
    }

    public void removeObject(BasicObject object){
        if(object==null)return;
        for (Map.Entry<String, Layer> i : layers.entrySet()){
            if (i.getValue().objects.remove(object)) {
                if (i.getValue().objects.isEmpty() && !Objects.equals(i.getKey(), "Basic")
                    && !i.getValue().save && i.getValue().childLayersIsEmpty()) {
                    i.getValue().dispose();
                    layers.remove(i.getKey());
                }
                Stock.getTaskRunner().scenesManager.isChangeObjects = true;
                return;
            }
        }
    }

    public void moveObjectToLayer(BasicObject object, String layer){
        removeObject(object);
        addObject(layer, object);
    }

    public void addLayer(String name){
        if(!layers.containsKey(name))layers.put(name, new Layer());
        layers.get(name).save=true;
    }

    public TreeMap<Byte, List<BasicObject>> getObjects(){
        TreeMap<Byte, List<BasicObject>> output=new TreeMap<>();
        for(Layer i : layers.values()){
            if(output.containsKey(i.number)){
                output.get(i.number).addAll(i.objects);
            }else {
                output.put(i.number, new ArrayList<>(i.objects));
            }
        }
        return output;
    }

    public void dispose(){}
}
