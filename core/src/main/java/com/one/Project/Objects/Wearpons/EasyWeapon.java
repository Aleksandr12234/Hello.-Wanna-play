package com.one.Project.Objects.Wearpons;

import com.one.Project.Objects.Wearpons.Bullets.EasyBullet;
import com.one.Structure.Objects.BasicObject;

public class EasyWeapon extends BasicObject {
    BasicObject shooter;

    public EasyWeapon(BasicObject shooter){
        this.shooter=shooter;
        position=shooter.position;
    }

    public void updateAngle(float angle){
        this.rotate=angle;
    }

    public void shoot(){
        manager.addObject(new EasyBullet(rotate, 200, position));
    }
}
