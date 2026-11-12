package com.one.Structure;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Box2D;

public class GameEntryPoint extends Game {
    @Override
    public void create() {
        Box2D.init();
        setScreen(new Stock());
    }

    @Override
    public void render() {
        super.render(); // important!
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
