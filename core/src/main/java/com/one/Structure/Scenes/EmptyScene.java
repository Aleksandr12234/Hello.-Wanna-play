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

    public void addObject(BasicObject object){
        if(object==null)return;
        layers.get("Basic").objects.add(object);
        Stock.getTaskRunner().scenesManager.isChangeObjects=true;
    }

    public void addObject(String layer, BasicObject object){
        if(object==null || layer==null)return;
        if(!layers.containsKey(layer))layers.put(layer, new Layer());
        layers.get(layer).objects.add(object);
        Stock.getTaskRunner().scenesManager.isChangeObjects=true;
    }

    public void addObject(BasicObject undo, BasicObject object){
        if(undo==null || object==null)return;
        for(Layer i : layers.values()){
            if(i.objects.contains(undo)){
                int index=i.objects.indexOf(undo);
                i.objects.add(index+1, object);
                Stock.getTaskRunner().scenesManager.isChangeObjects=true;
                return;
            }
        }
    }

    public void removeObject(BasicObject object){
        if(object==null)return;
        for (Map.Entry<String, Layer> i : layers.entrySet()){
            if (i.getValue().objects.remove(object)) {
                if (i.getValue().objects.isEmpty() && !Objects.equals(i.getKey(), "Basic") && !i.getValue().save) {
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
