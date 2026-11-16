package com.one.Project.Objects.Akari;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.one.Project.Objects.Akari.Modules.AkariCollision;
import com.one.Project.Objects.Akari.Modules.AkariLegs;
import com.one.Project.Objects.Projectile.Projectile;
import com.one.Project.Objects.Projectile.damageArea;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Stock;

public class Akari extends BasicObject {
    public final AkariCollision akariCollision=new AkariCollision(this);
    public final AkariLegs akariLegs=new AkariLegs(this);

    public Akari() {
        transform.size.set(0.75f, 0.75f);
    }

    @Override
    public void doLogic() {
        super.doLogic();
        if(Stock.getTaskRunner().userInput.getMousePressed(Input.Buttons.LEFT))
            Stock.getTaskRunner().scenesManager.getActiveScene().addObject(
                new damageArea(transform.position, transform.size, 10, 10,
                    0.1f));

        if(Stock.getTaskRunner().userInput.getMousePressed(Input.Buttons.RIGHT))
            Stock.getTaskRunner().scenesManager.getActiveScene().addObject(
                new Projectile(transform.position, new Vector2(0.1f, 0.1f), 10, 10,
                    -1, 2, 45)
            );
    }
}
