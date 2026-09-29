package com.one.Structure.TaskPerformers.LoaderClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.one.Structure.Stock;

public class SpriteListLoader {
    public Sprite[] loadSpriteList(int widthOneSprite, int heightOneSprite, int startFrame, int countFrame, String path){
        Texture spriteList= Stock.getTaskRunner().assetsLoader.texture.loadTexture(path);

        if(widthOneSprite==-1)widthOneSprite=spriteList.getWidth();
        if(heightOneSprite==-1)heightOneSprite=spriteList.getHeight();
        int allFrame=spriteList.getWidth()*spriteList.getHeight()/(widthOneSprite*heightOneSprite);

        Sprite[] sprites=new Sprite[allFrame];
        for (int i = 0; i < spriteList.getHeight()/heightOneSprite; i++) {
            for (int j = 0; j < spriteList.getWidth()/widthOneSprite; j++) {
                sprites[i*spriteList.getWidth()/widthOneSprite+j]=new Sprite(spriteList,
                    (j)*widthOneSprite,i*heightOneSprite, widthOneSprite, heightOneSprite);
            }
        }

        Sprite[] result=new Sprite[countFrame];
        System.arraycopy(sprites, startFrame, result, 0, countFrame);

        return result;
    }

    public Sprite[] loadSpriteList(int widthOneSprite, int heightOneSprite, String path){
        return loadSpriteList(widthOneSprite, heightOneSprite, 0, -1, path);
    }

    public Sprite[] loadSpriteList(String path){
        return loadSpriteList(-1, -1, path);
    }
}
