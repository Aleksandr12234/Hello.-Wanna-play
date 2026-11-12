package com.one.Structure.Objects.BasicObjects.UI.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontSettings {
    public BitmapFont font;

    public FontSettings(){
        font = new BitmapFont();
        font.setUseIntegerPositions(false);
        font.getData().setScale(0.5f);
    }

    public void setFont(String path){
        font = new BitmapFont(Gdx.files.internal(path));
        font.getData().setScale(0.5f);
        font.setUseIntegerPositions(false);
    }
}
