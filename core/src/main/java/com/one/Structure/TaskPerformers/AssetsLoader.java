package com.one.Structure.TaskPerformers;

import com.one.Structure.TaskPerformers.LoaderClasses.MusicLoader;
import com.one.Structure.TaskPerformers.LoaderClasses.SoundLoader;
import com.one.Structure.TaskPerformers.LoaderClasses.SpriteListLoader;
import com.one.Structure.TaskPerformers.LoaderClasses.TextureLoader;

public class AssetsLoader {
    public final SpriteListLoader spriteList =new SpriteListLoader();
    public final MusicLoader music=new MusicLoader();
    public final SoundLoader sound=new SoundLoader();
    public final TextureLoader texture=new TextureLoader();

    public void dispose(){
        texture.dispose();
        music.dispose();
        sound.dispose();
    }
}
