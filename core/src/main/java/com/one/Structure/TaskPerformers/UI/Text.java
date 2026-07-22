package com.one.Structure.TaskPerformers.UI;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Text extends BasicObject {
    private static final GlyphLayout layout = new GlyphLayout();
    public String text="";
    public float r, g, b, a;
    public float scale=1f;

    @Override
    public void draw() {
        Stock.getTaskRunner().serviceObjects.font.getData().setScale(scale);
        Stock.getTaskRunner().serviceObjects.font.setColor(r, g, b, a);

        layout.setText(
            Stock.getTaskRunner().serviceObjects.font,
            text
        );
        float x = (transform.getPosition().x - layout.width/2);
        float y = (transform.getPosition().y + layout.height/2);

        Stock.getTaskRunner().serviceObjects.font.draw(
            Stock.getTaskRunner().serviceObjects.batch,
            text,
            x,
            y
        );
    }

    public void setColor(float r, float g, float b, float a){
        this.r=r;
        this.g=g;
        this.b=b;
        this.a=a;
    }
}
