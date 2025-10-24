package com.one.Project.Objects.Wearpons.Bullets;

import com.badlogic.gdx.math.Vector2;
import com.one.Project.Objects.Creature.Creature;
import com.one.Structure.Objects.BasicObject;

public class CreatureBullet extends EasyBullet{
    BasicObject shooter;

    public CreatureBullet(float angle, float distance, Vector2 position, BasicObject shooter) {
        super(angle, distance, position);
        this.shooter=shooter;
    }

    public void addEnergy(){
        ((Creature)shooter).addEnergy(10);
    }
}
