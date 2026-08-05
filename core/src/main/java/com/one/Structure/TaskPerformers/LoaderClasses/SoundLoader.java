package com.one.Structure.TaskPerformers.LoaderClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundLoader {
    private final HashMap<String, Sound> soundHashMap=new HashMap<>();

    public Sound loadSound(String path){
        if(soundHashMap.containsKey(path))return soundHashMap.get(path);

        soundHashMap.put(path, Gdx.audio.newSound(Gdx.files.internal(path)));
        return soundHashMap.get(path);
    }

    public void dispose(){
        for (String i : soundHashMap.keySet()) {
            soundHashMap.get(i).dispose();
        }
        soundHashMap.clear();
    }
}
