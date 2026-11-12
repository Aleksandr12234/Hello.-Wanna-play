package com.one.Structure.Objects.BasicObjects.Particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Collision.BasicBody;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

import java.util.function.Function;

public class Particle extends BasicObject {
    private Sprite sprite= Stock.getTaskRunner().assetsLoader.sprite.loadSprite("Structure/test/Particle.png");
    private final BasicBody<Particle> body;

    private float startLifeTime;
    private float lifeTime;

    public final Vector2 startSpeed=new Vector2();
    public final Vector2 endSpeed=new Vector2();

    private final Color startColor=new Color(Color.WHITE);
    private final Color currentColor=new Color(Color.WHITE);
    private final Color endColor=new Color(Color.WHITE);

    private final Vector2 startSize=new Vector2();
    public final Vector2 endSize=new Vector2();

    public Particle(Function<Particle, BasicBody<Particle>> bodyFactory){
        body=bodyFactory.apply(this);
    }
    public Particle(){
        body=null;
    }

    @Override
    public void doLogic() {
        super.doLogic();

        lifeTime-=deltaTime;
        if(lifeTime<=0) status.dispose=true;
        float alpha = 1.0f-(lifeTime/startLifeTime);

        speedCalculate(alpha);
        colorCalculate(alpha);
        sizeCalculate(alpha);
    }

    private void speedCalculate(float alpha){
        float currentSpeedX= MathUtils.lerp(startSpeed.x, endSpeed.x, alpha);
        float currentSpeedY= MathUtils.lerp(startSpeed.y, endSpeed.y, alpha);
        float currentRotate= MathUtils.lerpAngleDeg(startSpeed.angleDeg(), endSpeed.angleDeg(), alpha);

        transform.position.add(currentSpeedX*deltaTime, currentSpeedY*deltaTime);
        transform.rotate.setDegreesAngle(currentRotate);
    }

    private void colorCalculate(float alpha){
        currentColor.r=MathUtils.lerp(startColor.r, endColor.r, alpha);
        currentColor.g=MathUtils.lerp(startColor.g, endColor.g, alpha);
        currentColor.b=MathUtils.lerp(startColor.b, endColor.b, alpha);
        currentColor.a=MathUtils.lerp(startColor.a, endColor.a, alpha);
    }

    private void sizeCalculate(float alpha){
        transform.size.x= MathUtils.lerp(startSize.x, endSize.x, alpha);
        transform.size.y= MathUtils.lerp(startSize.y, endSize.y, alpha);
    }

    @Override
    public void draw() {
        sprite.setOriginCenter();
        sprite.setColor(currentColor.r, currentColor.g, currentColor.b, currentColor.a);
        sprite.setSize(transform.size.x, transform.size.y);
        sprite.setRotation(transform.rotate.getDegreesAngle());
        sprite.setCenter(transform.position.x, transform.position.y);

        sprite.draw(Stock.getTaskRunner().drawer.batch);
    }

    public Particle setLifeTime(float time) {
        this.lifeTime = time;
        this.startLifeTime = time;
        return this;
    }

    public Particle setStartSpeed(Vector2 speed) {
        this.startSpeed.set(speed);
        return this;
    }

    public Particle setStartSpeed(float x, float y) {
        this.startSpeed.set(x, y);
        return this;
    }

    public Particle setPosition(float x, float y){
        transform.position.set(x, y);
        return this;
    }

    public Particle setEndSpeed(Vector2 speed) {
        this.endSpeed.set(speed);
        return this;
    }

    public Particle setEndSpeed(float x, float y) {
        this.endSpeed.set(x, y);
        return this;
    }

    public Particle setStartColor(Color color) {
        this.startColor.set(color);
        this.currentColor.set(color);
        return this;
    }

    public Particle setEndColor(Color color) {
        this.endColor.set(color);
        return this;
    }

    public Particle setStartSize(Vector2 size) {
        this.startSize.set(size);
        this.transform.size.set(size);
        return this;
    }

    public Particle setStartSize(float width, float height) {
        this.startSize.set(width, height);
        this.transform.size.set(width, height);
        return this;
    }

    public Particle setEndSize(Vector2 size) {
        this.endSize.set(size);
        return this;
    }

    public Particle setEndSize(float width, float height) {
        this.endSize.set(width, height);
        return this;
    }

    public Particle setSprite(String path){
        sprite=Stock.getTaskRunner().assetsLoader.sprite.loadSprite(path);
        return this;
    }
}
