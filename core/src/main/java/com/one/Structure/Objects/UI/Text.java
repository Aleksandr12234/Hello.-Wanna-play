package com.one.Structure.Objects.UI;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Text extends BasicObject {
    private static final GlyphLayout layout = new GlyphLayout();
    public String text="";
    public float r, g, b, a;
    public float scale=1f;
    public AnchorPoint anchorPoint = AnchorPoint.CENTER;

    public Text(String text, float x, float y, float scale){
        this(text, x, y);
        this.scale=scale;
    }

    public Text(String text, float x, float y){
        this(text);
        transform.position.set(x, y);
    }

    public Text(String text, float scale){
        this(text);
        this.scale=scale;
    }

    public Text(String text){
        this.text=text;
    }

    @Override
    public void draw() {
        Stock.getTaskRunner().serviceObjects.font.getData().setScale(scale);
        Stock.getTaskRunner().serviceObjects.font.setColor(r, g, b, a);

        layout.setText(
            Stock.getTaskRunner().serviceObjects.font,
            text
        );

        float x = (transform.position.x - layout.width/2);
        float y = (transform.position.y + layout.height/2);

        if(anchorPoint.positionX==-1)x=(transform.position.x);
        if(anchorPoint.positionX==1)x=(transform.position.x - layout.width);
        if(anchorPoint.positionY==-1)y = (transform.position.y+layout.height);
        if(anchorPoint.positionY==1)y = (transform.position.y);

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
