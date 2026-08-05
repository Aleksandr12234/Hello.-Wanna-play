package com.one.Structure.TaskPerformers.LoaderClasses;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureLoader {
    private final HashMap<String, Texture> textureHashMap=new HashMap<>();

    public Texture loadTexture(String path){
        if(textureHashMap.containsKey(path))return textureHashMap.get(path);

        textureHashMap.put(path, new Texture(path));
        return textureHashMap.get(path);
    }

    public void dispose(){
        for(String i : textureHashMap.keySet()){
            textureHashMap.get(i).dispose();
        }
        textureHashMap.clear();
    }
}
