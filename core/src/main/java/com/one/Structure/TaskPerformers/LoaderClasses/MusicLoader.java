package com.one.Structure.TaskPerformers.LoaderClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

public class MusicLoader {
    private final HashMap<String, Music> musicHashMap=new HashMap<>();

    public Music loadMusic(String path){
        if(musicHashMap.containsKey(path))return musicHashMap.get(path);

        musicHashMap.put(path, Gdx.audio.newMusic(Gdx.files.internal(path)));
        return musicHashMap.get(path);
    }

    public void dispose(){
        for(String i : musicHashMap.keySet()){
            musicHashMap.get(i).dispose();
        }
        musicHashMap.clear();
    }
}
