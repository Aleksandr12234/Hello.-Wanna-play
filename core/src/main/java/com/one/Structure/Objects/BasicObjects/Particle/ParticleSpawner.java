package com.one.Structure.Objects.BasicObjects.Particle;

import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Objects.Module;

import java.util.ArrayList;

public class ParticleSpawner<T extends BasicObject> extends Module<T> {
    public boolean isEnd=false;
    public boolean pause=false;
    public float timeToSpawn;
    public int countOfParticle=1;
    public int countOfSpawn=-1;
    public SpawnFunction spawnFunction;

    private final ArrayList<Particle> particles=new ArrayList<>();
    private float time=0;

    public ParticleSpawner(T master) {
        super(master);
    }

    @Override
    public void doLogic() {
        super.doLogic();
        for (int j = particles.size()-1; j>=0; j--) {
            if(particles.get(j).getStatus().dispose){
                particles.get(j).onDispose();
                particles.remove(j);
            }else particles.get(j).doLogic();
        }
        if(!pause && !isEnd) {
            time -= deltaTime;
            if (time <= 0) {
                time = timeToSpawn;

                if (countOfSpawn == 0) isEnd = true;
                else if (countOfSpawn > -1) countOfSpawn--;

                for (int i = 0; i < countOfParticle; i++) {
                    if (spawnFunction != null) {
                        Particle particle = spawnFunction.spawn();
                        particle.setup();
                        particles.add(particle);
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        for (Particle i : particles) {
            i.draw();
        }
    }
}
