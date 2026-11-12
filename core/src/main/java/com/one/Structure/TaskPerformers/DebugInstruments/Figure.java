package com.one.Structure.TaskPerformers.DebugInstruments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.one.Structure.Stock;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Figure {
    public final ShapeDrawer shapeDrawer;
    public final Texture textureForShapes;

    public Figure(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        textureForShapes = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion region = new TextureRegion(textureForShapes, 0, 0, 1, 1);
        shapeDrawer=new ShapeDrawer(Stock.getTaskRunner().drawer.batch, region);
        shapeDrawer.setDefaultLineWidth(0.01f);
    }

    public void dispose(){
        textureForShapes.dispose();
    }
}
