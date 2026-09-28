package com.one.Structure.Objects.Particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Particle.Modules.ParticleCollision;
import com.one.Structure.Stock;

public class Particle extends BasicObject {
    private float startLifeTime;
    private float lifeTime;

    Sprite sprite=new Sprite(Stock.getTaskRunner().assetsLoader.texture.loadTexture("test/Particle.png"));

    public final ParticleCollision collision=new ParticleCollision(this);

    public final Vector2 startSpeed=new Vector2();
    public final Vector2 endSpeed=new Vector2();

    private final Color startColor=new Color(Color.WHITE);
    private final Color currentColor=new Color(Color.WHITE);
    private final Color endColor=new Color(Color.WHITE);

    private final Vector2 startSize=new Vector2();
    private final Vector2 endSize=new Vector2();

    @Override
    public void doLogic() {
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
        transform.rotate.setRotate(currentRotate);
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
        sprite.setRotation(transform.rotate.getDegreesRotate());
        sprite.setCenter(transform.position.x, transform.position.y);

        sprite.draw(Stock.getTaskRunner().serviceObjects.batch);
    }

    public Particle lifeTime(float time) {
        this.lifeTime = time;
        this.startLifeTime = time;
        return this;
    }

    public Particle startSpeed(Vector2 speed) {
        this.startSpeed.set(speed);
        return this;
    }

    public Particle startSpeed(float x, float y) {
        this.startSpeed.set(x, y);
        return this;
    }

    public Particle endSpeed(Vector2 speed) {
        this.endSpeed.set(speed);
        return this;
    }

    public Particle endSpeed(float x, float y) {
        this.endSpeed.set(x, y);
        return this;
    }

    public Particle startColor(Color color) {
        this.startColor.set(color);
        this.currentColor.set(color); // Чтобы частица сразу появилась с нужным цветом
        return this;
    }

    public Particle endColor(Color color) {
        this.endColor.set(color);
        return this;
    }

    public Particle startSize(Vector2 size) {
        this.startSize.set(size);
        this.transform.size.set(size);
        return this;
    }

    public Particle startSize(float width, float height) {
        this.startSize.set(width, height);
        this.transform.size.set(width, height);
        return this;
    }

    public Particle endSize(Vector2 size) {
        this.endSize.set(size);
        return this;
    }

    public Particle endSize(float width, float height) {
        this.endSize.set(width, height);
        return this;
    }

    public Particle sprite(String path){
        sprite=new Sprite(Stock.getTaskRunner().assetsLoader.texture.loadTexture(path));
        return this;
    }
}
