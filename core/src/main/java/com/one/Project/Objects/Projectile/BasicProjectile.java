package com.one.Project.Objects.Projectile;

import com.badlogic.gdx.math.Vector2;
import com.one.Structure.Collision.BasicBody;
import com.one.Structure.Objects.BasicObject;

public class BasicProjectile extends BasicObject {
    protected BasicProjectileCollision collision=new BasicProjectileCollision(this);
    protected float damage;
    protected float timeToStan;

    public BasicProjectile(Vector2 position, Vector2 size, float damage, float timeToStan) {
        this.damage = damage;
        this.timeToStan = timeToStan;
        transform.position.set(position);
        transform.size.set(size);
    }

    public void contact(BasicBody<?> other){
        System.out.println("Nanositsya uron "+damage);
        System.out.println("Stan vraga "+timeToStan);
    }
}
