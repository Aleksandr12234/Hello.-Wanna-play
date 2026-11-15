package com.one.Project.Objects.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Collision.BasicBody;

public class Projectile extends BasicProjectile{
    private int penetratingPower;
    private final float angle;
    private final float speed;

    public Projectile(Vector2 position, Vector2 size, float damage, float timeToStan, int penetratingPower, float speed,
                      float angle) {
        super(position, size, damage, timeToStan);
        this.penetratingPower=penetratingPower;
        this.angle=angle;
        this.speed=speed;
    }

    @Override
    public void doLogic() {
        super.doLogic();
        Vector2 direction=new Vector2(1, 0);
        direction.scl(speed).setAngleDeg(angle);
        collision.getBody().body.setLinearVelocity(direction);
    }

    @Override
    public void contact(BasicBody<?> other) {
        super.contact(other);
        penetratingPower-=1;
        if(penetratingPower==0)status.dispose=true;
    }
}
