package com.one.Structure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ServiceObjects extends Game {
    public SpriteBatch batch;
    public ExtendViewport viewport;
    public OrthographicCamera camera;

    //фигуры
    public ShapeDrawer shapeDrawer;
    Texture textureForShapes;

    //текст
    public BitmapFont font;

    @Override
    public void create() {
        camera=new OrthographicCamera(640, 480);
        batch = new SpriteBatch();
        viewport=new ExtendViewport(640, 480, camera);

        //пиксель для фигур
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        textureForShapes = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion region = new TextureRegion(textureForShapes, 0, 0, 1, 1);
        shapeDrawer=new ShapeDrawer(batch, region);

        //Текст
        font = new BitmapFont();
        font.setUseIntegerPositions(false);

        setScreen(new Manager(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        textureForShapes.dispose();
    }
}
