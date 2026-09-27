package com.one.Structure.Objects.Particle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;
import lombok.Builder;

@Builder
public class Particle extends BasicObject {
    private float timeToLife;
    private Vector2 speed;
    @Builder.Default
    Sprite sprite=new Sprite(Stock.getTaskRunner().assetsLoader.texture.loadTexture("test/Particle.png"));

    @Override
    public void doLogic() {
        timeToLife-=deltaTime;
        if(timeToLife<=0) status.dispose=true;

        transform.position.add(new Vector2(speed).scl(deltaTime));
        transform.rotate.setRotate(speed.angleDeg());
    }

    @Override
    public void draw() {
        sprite.setOriginCenter();
        sprite.setSize(0.1f, 0.1f);
        sprite.setRotation(transform.rotate.getDegreesRotate());
        sprite.setCenter(transform.position.x, transform.position.y);

        sprite.draw(Stock.getTaskRunner().serviceObjects.batch);
    }
}
