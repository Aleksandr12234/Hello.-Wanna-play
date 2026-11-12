package com.one.Structure.Objects.BasicObjects.UI.Text;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.BasicObjects.UI.AnchorPoint;
import com.one.Structure.Stock;

public class BasicText extends BasicObject {
    public static FontSettings fontSettings=new FontSettings();

    private static final GlyphLayout layout = new GlyphLayout();
    public String text;
    public float r=1, g=1, b=1, a=1;
    public float scale=1f;
    public AnchorPoint anchorPoint = AnchorPoint.CENTER;

    public BasicText(String text, float x, float y, float scale){
        this(text, x, y);
        this.scale=scale;
    }

    public BasicText(String text, float x, float y){
        this(text);
        transform.position.set(x, y);
    }

    public BasicText(String text, float scale){
        this(text);
        this.scale=scale;
    }

    public BasicText(String text){
        this.text=text;
    }

    @Override
    public void draw() {
        fontSettings.font.getData().setScale(scale);
        fontSettings.font.setColor(r, g, b, a);

        layout.setText(
            fontSettings.font,
            text
        );

        float x = (transform.position.x - layout.width/2);
        float y = (transform.position.y + layout.height/2);

        if(anchorPoint.positionX==-1)x=(transform.position.x);
        if(anchorPoint.positionX==1)x=(transform.position.x - layout.width);
        if(anchorPoint.positionY==-1)y = (transform.position.y+layout.height);
        if(anchorPoint.positionY==1)y = (transform.position.y);

        fontSettings.font.draw(Stock.getTaskRunner().drawer.batch, text, x, y);
    }

    public void setColor(float r, float g, float b, float a){
        this.r=r;
        this.g=g;
        this.b=b;
        this.a=a;
    }
}
