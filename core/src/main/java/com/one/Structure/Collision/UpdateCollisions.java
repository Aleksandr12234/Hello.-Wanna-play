package com.one.Structure.Collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.one.Structure.Objects.BasicObjects.Camera.BasicCamera;
import lombok.Getter;

public class UpdateCollisions {
    @Getter private World world=new World(new Vector2(), true);
    @Getter private final BodyCategories categories =new BodyCategories();
    private final Box2DDebugRenderer debugRenderer=new Box2DDebugRenderer();
    private float accumulator = 0;

    public UpdateCollisions() {
        setup();
    }

    public void setup(){
        world=new World(new Vector2(), true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                BasicBody<?> bodyA= (BasicBody<?>) contact.getFixtureA().getBody().getUserData();
                BasicBody<?> bodyB= (BasicBody<?>) contact.getFixtureB().getBody().getUserData();

                if(contact.getFixtureB().isSensor())bodyA.beginTrigger(bodyB);
                else bodyA.beginCollision(bodyB);
                if(contact.getFixtureA().isSensor()) bodyB.beginTrigger(bodyA);
                else bodyB.beginCollision(bodyA);
            }

            @Override
            public void endContact(Contact contact) {
                BasicBody<?> bodyA= (BasicBody<?>) contact.getFixtureA().getBody().getUserData();
                BasicBody<?> bodyB= (BasicBody<?>) contact.getFixtureB().getBody().getUserData();

                if(contact.getFixtureB().isSensor())bodyA.endTrigger(bodyB);
                else bodyA.endCollision(bodyB);
                if(contact.getFixtureA().isSensor()) bodyB.endTrigger(bodyA);
                else bodyB.endCollision(bodyA);
            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {}
            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {}
        });
    }

    public void update(float v){
        float frameTime = Math.min(v, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }
    }

    public void debug(){
        debugRenderer.render(world, BasicCamera.camera.combined);
    }

    public void dispose(){
        categories.dispose();
        world.dispose();
    }
}
