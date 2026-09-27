package com.one.Structure.TestClasses;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Objects.Particle.Particle;
import com.one.Structure.Objects.Particle.ParticleSpawner;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(true);

        addObject(new TestPhysicRect(0, 0, 0.5f, 1, true));
        addObject(new TestPhysicRect(1, 1, 1, 0.5f, true));
        addObject(new TestPhysicRect(0, 0, 1, 1, false));
        TestControlRect rect = addObject(new TestControlRect(0, 0, 1f, 1f));

        {
            addObject(ParticleSpawner.builder().timeToSpawn(0.1f).spawnFunction(()->{

                Stock.getTaskRunner().scenesManager
                    .activeScene.addObject(Particle.builder().speed(new Vector2(1, 1)).timeToLife(0.25f).build());
                System.out.println("Object add");
            }).build());
        }
    }
}
