package com.one.Structure;

import com.badlogic.gdx.Screen;
import com.one.Project.BasicSettings;
import lombok.Getter;

public class Stock implements Screen {
    @Getter private static TaskRunner taskRunner;

    public Stock(){
        taskRunner = new TaskRunner();
        taskRunner.scenesManager.changeActiveScene(BasicSettings.getStartScene());
    }

    @Override
    public void render(float v) {
        taskRunner.update(v);
    }

    @Override
    public void resize(int i, int i1) {
        taskRunner.drawer.viewport.update(i, i1);
    }

    @Override
    public void dispose() {
        taskRunner.assetsLoader.dispose();
        taskRunner.drawer.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        taskRunner.drawer.postProcessor.rebind();
    }

    @Override
    public void hide() {

    }
}
