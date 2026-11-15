package com.one.Project.Objects.Weapon;

import com.badlogic.gdx.math.Vector2;

public class damageArea extends BasicProjectile{
    private float timeToLeave;

    public damageArea(Vector2 position, Vector2 size, float damage, float timeToStan, float timeToLeave) {
        super(position, size, damage, timeToStan);
        this.timeToLeave=timeToLeave;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        timeToLeave-=deltaTime;
        if(timeToLeave<=0)status.dispose=true;
    }
}
