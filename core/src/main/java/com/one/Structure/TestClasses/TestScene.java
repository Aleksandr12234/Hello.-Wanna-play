package com.one.Structure.TestClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Collision.Body.RectPhysicalBody;
import com.one.Structure.Objects.Particle.Particle;
import com.one.Structure.Objects.Particle.ParticleSpawner;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(false);

        addObject(new TestPhysicRect(-1, 0, 0.5f, 1, true));
        addObject(new TestPhysicRect(-2, 1, 1, 0.5f, true));
        addObject(new TestPhysicRect(-3, 0, 1, 1, false));
        TestControlRect rect = addObject(new TestControlRect(1, 0, 1f, 1f));

        {
            addObject(ParticleSpawner.builder().countOfParticle(1).timeToSpawn(0.001f).spawnFunction(()->{
                float a= (float) Math.random();
                Particle particle = Stock.getTaskRunner().scenesManager
                    .activeScene.addObject(new Particle().lifeTime(1f)
                        .startSpeed(new Vector2( -0.4f+0.8f*a, 4))
                        .endSpeed(new Vector2((-0.2f+0.4f*a)/2f, -5))
                        .startColor(Color.YELLOW)
                        .endColor(Color.YELLOW)
                        .startSize(new Vector2(0.4f, 0.01f))
                        .endSize(new Vector2(0.4f*a, 0.01f*a)));

                particle.collision.isDispose=false;
                particle.collision.classes.add(RectPhysicalBody.class);
            }).build());
        }
    }
}
