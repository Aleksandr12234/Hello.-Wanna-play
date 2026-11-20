package com.one.Project.Objects.Items;

import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

public class Item {
    public final String name;
    public final byte lvl;
    public final int durability;
    private final float damageOnThrow;
    private final float timeToEnemyStunOnThrow;
    private final float timeToAkariStunOnThrow;
    private final float speedOnThrow;
    private final Vector2 sizeOnThrow;
    private final int penetrationOnThrow;

    @Getter protected int currentDurability;

    public Item(String name, byte lvl, int durability, float damageOnThrow, float timeToEnemyStunOnThrow, float timeToAkariStunOnThrow,
                float speedOnThrow, Vector2 sizeOnThrow, int penetrationOnThrow) {
        this.name = name;
        this.lvl = lvl;
        this.durability = durability;
        this.damageOnThrow = damageOnThrow;
        this.timeToEnemyStunOnThrow = timeToEnemyStunOnThrow;
        this.timeToAkariStunOnThrow = timeToAkariStunOnThrow;
        this.speedOnThrow = speedOnThrow;
        this.sizeOnThrow = sizeOnThrow;
        this.penetrationOnThrow = penetrationOnThrow;
        resetDurability();
    }

    protected void resetDurability(){
        currentDurability=durability;
    }

    public void fling(){
        System.out.println("broshen: "+this);
    }

    @Override
    public String toString() {
        return name+"; lvl: "+lvl+"; durability: "+(currentDurability+"/"+durability)+"; damageOnThrow: "+damageOnThrow
            +"; timeToEnemyStunOnThrow: "+timeToEnemyStunOnThrow+"; timeToAkariStunOnThrow: "+timeToAkariStunOnThrow
            +"; speedOnThrow: "+speedOnThrow+"; sizeOnThrow: "+(sizeOnThrow.x+" "+sizeOnThrow.y)
            +"; penetrationOnThrow: "+penetrationOnThrow;
    }
}
