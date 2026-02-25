package com.one.Structure.taskPerformers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TexturesLoader {
    HashMap<String, Music> musicHashMap=new HashMap<>();
    HashMap<String, Sound> soundHashMap=new HashMap<>();
    HashMap<String, Texture> textureHashMap=new HashMap<>();

    public Texture loadTexture(String path){
        if(textureHashMap.containsKey(path))return textureHashMap.get(path);

        textureHashMap.put(path, new Texture(path));
        return textureHashMap.get(path);
    }

    public Music loadMusic(String path){
        if(musicHashMap.containsKey(path))return musicHashMap.get(path);

        musicHashMap.put(path, Gdx.audio.newMusic(Gdx.files.internal(path)));
        return musicHashMap.get(path);
    }

    public Sound loadSound(String path){
        if(soundHashMap.containsKey(path))return soundHashMap.get(path);

        soundHashMap.put(path, Gdx.audio.newSound(Gdx.files.internal(path)));
        return soundHashMap.get(path);
    }

    public void dispose(){
        for(String i : textureHashMap.keySet()){
            textureHashMap.get(i).dispose();
        }
        for(String i : musicHashMap.keySet()){
            musicHashMap.get(i).dispose();
        }
        for (String i : soundHashMap.keySet()) {
            soundHashMap.get(i).dispose();
        }
    }
}
