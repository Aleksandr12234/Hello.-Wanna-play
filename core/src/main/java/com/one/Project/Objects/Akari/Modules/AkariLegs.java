package com.one.Project.Objects.Akari.Modules;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.one.Project.Objects.Akari.Akari;
import com.one.Structure.Objects.Module;
import com.one.Structure.Stock;

public class AkariLegs extends Module<Akari> {
    public final float speed=2;

    public AkariLegs(Akari master) {
        super(master);
    }

    @Override
    public void doLogic() {
        super.doLogic();

        Vector2 direction=new Vector2();
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.W))direction.y+=1;
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.S))direction.y-=1;
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.A))direction.x-=1;
        if(Stock.getTaskRunner().userInput.getKeysClamped(Input.Keys.D))direction.x+=1;
        direction.nor().scl(speed);
        master.akariCollision.getBody().body.setLinearVelocity(direction);
    }
}
