package com.one.Structure.TaskPerformers;

import com.one.Structure.TaskPerformers.LoaderClasses.*;

public class AssetsLoader {
    public final SpriteLoader sprite =new SpriteLoader();
    public final MusicLoader music=new MusicLoader();
    public final SoundLoader sound=new SoundLoader();
    public final TextureLoader texture=new TextureLoader();
    public final JsonLoader json=new JsonLoader();

    public void dispose(){
        texture.dispose();
        music.dispose();
        sound.dispose();
        sprite.dispose();
        json.dispose();
    }
}
