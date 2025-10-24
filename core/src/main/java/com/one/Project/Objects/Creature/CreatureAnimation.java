package com.one.Project.Objects.Creature;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

import static java.lang.Math.*;

public class CreatureAnimation extends BasicObject {
    Sprite arm;
    Sprite[] moveSprites=new Sprite[9];
    Sprite[] eyesMoveSprites =new Sprite[9];
    Sprite[] sprites=new Sprite[12];
    Sprite[] eyesSprites=new Sprite[12];

    BasicObject master;
    public byte activeSprite=0;
    boolean isPlus=true;
    float time;
    boolean isWhite=true;

    public boolean isMove=false;
    public byte direction=1;
    public byte armDirection=1;

    public CreatureAnimation(BasicObject master){
        this.master=master;
        position=master.position;
        size=master.size;
        rotate=master.rotate;

        Texture bodyTexture =manager.texturesLoader.loadTexture("Creature/creatureBody.png");
        Texture eyesTexture =manager.texturesLoader.loadTexture("Creature/creatureEyes.png");
        for(int i=0; i<12;i++){
            sprites[i]=new Sprite(bodyTexture, i*128,0,128,128);
            eyesSprites[i]=new Sprite(eyesTexture, i*128,0,128,128);
            eyesSprites[i].setColor(Color.BLACK);
        }

        bodyTexture = manager.texturesLoader.loadTexture("Creature/creatureBodyMove.png");
        eyesTexture = manager.texturesLoader.loadTexture("Creature/creatureEyesMove.png");
        for(int i=0; i<9;i++){
            moveSprites[i]=new Sprite(bodyTexture, i*128,0,128,128);
            eyesMoveSprites[i]=new Sprite(eyesTexture, i*128,0,128,128);
            eyesMoveSprites[i].setColor(Color.BLACK);
        }

        arm=new Sprite(bodyTexture, 24, 89, 11,11);
    }

    public void switchColor(){
        if(isWhite) {
            for (int i = 0; i < 12; i++) {
                sprites[i].setColor(Color.BLACK);
                eyesSprites[i].setColor(Color.WHITE);
                if(i<moveSprites.length) {
                    moveSprites[i].setColor(Color.BLACK);
                    eyesMoveSprites[i].setColor(Color.WHITE);
                }
                arm.setColor(Color.BLACK);
            }
            isWhite=false;
        }
        else {
            for (int i = 0; i < 12; i++) {
                sprites[i].setColor(Color.WHITE);
                eyesSprites[i].setColor(Color.BLACK);
                if(i<moveSprites.length) {
                    moveSprites[i].setColor(Color.WHITE);
                    eyesMoveSprites[i].setColor(Color.BLACK);
                }
                arm.setColor(Color.WHITE);
            }
            isWhite=true;
        }
    }

    @Override
    public void draw() {
        if(!isMove)creatureDefault();
        else {
            creatureMove();
        }
        arm();

        time-=deltaTime;
        if(time<=0){
            if(isPlus){
                boolean a= !isMove && activeSprite<sprites.length-1;
                boolean b= isMove && activeSprite<moveSprites.length-1;
                if(a || b)activeSprite++;
                else isPlus=false;
            }else {
                boolean a = !isMove && activeSprite>0;
                boolean b = isMove && activeSprite>2;
                if(a || b)activeSprite--;
                else isPlus=true;
            }
            time=0.1f;
        }
    }

    private void creatureDefault(){
        sprites[activeSprite].setSize((int)max(size.x, size.y)*1.5f*armDirection, (int)max(size.x, size.y)*1.5f);
        sprites[activeSprite].setCenter((int)position.x, (int)position.y);
        sprites[activeSprite].draw(Manager.serviceObjects.batch);

        eyesSprites[activeSprite].setSize((int)max(size.x, size.y)*1.5f*armDirection, (int)max(size.x, size.y)*1.5f);
        eyesSprites[activeSprite].setCenter((int)position.x, (int)position.y);
        eyesSprites[activeSprite].draw(Manager.serviceObjects.batch);
    }

    private void  creatureMove(){
        moveSprites[activeSprite].setSize((int)max(size.x, size.y)*1.5f*direction, (int)max(size.x, size.y)*1.5f);
        moveSprites[activeSprite].setCenter((int)position.x, (int)position.y);
        moveSprites[activeSprite].draw(Manager.serviceObjects.batch);

        eyesMoveSprites[activeSprite].setSize((int)max(size.x, size.y)*1.5f*direction, (int)max(size.x, size.y)*1.5f);
        eyesMoveSprites[activeSprite].setCenter((int)position.x, (int)position.y);
        eyesMoveSprites[activeSprite].draw(Manager.serviceObjects.batch);
    }

    private void arm(){
        Vector3 vector = Manager.user.getMousePosition();
        Vector2 napr=new Vector2(vector.x-(position.x), vector.y-(position.y+20));

        arm.setSize((int)max(size.x, size.y)*1.5f/12, (int)max(size.x, size.y)*1.5f/12);
        arm.setCenter((float) (position.x+90*cos(napr.angleRad())), (float) (position.y+20+80*sin(napr.angleRad())));
        arm.draw(Manager.serviceObjects.batch);

        if(napr.x<0)armDirection=-1;
        else armDirection=1;
    }
}
