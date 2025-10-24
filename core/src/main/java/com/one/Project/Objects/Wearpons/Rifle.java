package com.one.Project.Objects.Wearpons;

import com.one.Project.Objects.Wearpons.Bullets.CreatureBullet;
import com.one.Structure.Objects.BasicObject;

public class Rifle extends EasyWeapon {
    float time=0;

    public Rifle(BasicObject shooter) {
        super(shooter);
    }

    @Override
    public void shoot() {
        manager.addObject(new CreatureBullet(rotate, 200, position, shooter));
    }

    public int shoot(int energy, boolean isShooting){
        time-= deltaTime;
        if(energy<5){
            return energy;
        }
        if(time<=0 && isShooting) {
            time = 0.5f;
            energy-=5;
            shoot();
        }
        return energy;
    }
}
