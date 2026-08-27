package com.one.Structure;

import com.badlogic.gdx.Screen;
import com.one.Project.BasicSettings;
import com.one.Structure.TaskPerformers.*;

public class Stock implements Screen {
    private static TaskRunner taskRunner;

    public Stock(ServiceObjects serviceObjects){
        taskRunner = new TaskRunner(serviceObjects);
        taskRunner.scenesManager.changeActiveScene(BasicSettings.getStartScene());
    }

    public static TaskRunner getTaskRunner(){
        return taskRunner;
    }

    @Override
    public void render(float v) {
        taskRunner.update();
    }

    @Override
    public void resize(int i, int i1) {
        taskRunner.serviceObjects.viewport.update(i, i1);
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
