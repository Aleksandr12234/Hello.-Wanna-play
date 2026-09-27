package com.one.Structure.Objects.Particle;

import com.one.Structure.Objects.BasicObject;
import lombok.Builder;

@Builder
public class ParticleSpawner extends BasicObject {
    private float timeToSpawn;
    @Builder.Default
    private float time=0;
    @Builder.Default
    private float countOfParticle=1;
    @Builder.Default
    private float countOfSpawn=-1;
    private SpawnFunction spawnFunction;

    @Override
    public void doLogic() {
        time-=deltaTime;
        if(time<=0){
            time=timeToSpawn;
            for (int i = 0; i < countOfParticle; i++) {
                if(spawnFunction!=null)spawnFunction.spawn();
            }

            if(countOfSpawn==0)status.dispose=true;
            else if(countOfSpawn>-1)countOfSpawn--;
        }
    }
}
