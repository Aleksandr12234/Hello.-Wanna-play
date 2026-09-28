package com.one.Structure.Objects.Particle.Modules;

import com.one.Structure.Collision.Body.RectBody;
import com.one.Structure.Collision.Figures.RectCollision;
import com.one.Structure.Objects.Particle.Particle;

import java.util.ArrayList;
import java.util.List;

public class ParticleCollision extends RectBody<Particle> {
    public float reverseX=0.1f, reverseY=0.1f;
    public boolean isDispose=true;
    public final List<Class<?>> classes=new ArrayList<>();

    public ParticleCollision(Particle master) {
        super(master, false);
        transform.synchronize(master.getTransform());
    }

    @Override
    protected void action(RectCollision<?> rectCollision) {
        for (Class<?> i : classes) {
            if(i.isInstance(rectCollision.getMaster())){
                if(isDispose) status.dispose=true;
                else {
                    master.startSpeed.scl(-reverseX);
                    master.endSpeed.scl(-reverseY);
                }
                return;
            }
        }

    }
}
