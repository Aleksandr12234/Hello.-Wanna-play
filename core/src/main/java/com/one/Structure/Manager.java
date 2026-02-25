package com.one.Structure;

import com.badlogic.gdx.Screen;
import com.one.Project.Scenes.GameplayTestScene;
import com.one.Project.Scenes.MainMenu;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Scenes.Scene;
import com.one.Structure.UserInput.KeyBoardInput;
import com.one.Structure.collision.UpdateCollisions;
import com.one.Structure.taskPerformers.Drawer;
import com.one.Structure.taskPerformers.Logic;
import com.one.Structure.taskPerformers.TexturesLoader;

import java.util.ArrayList;

public class Manager implements Screen {
    //настройки
    public static ServiceObjects serviceObjects;
    public static KeyBoardInput user=new KeyBoardInput();
    public TexturesLoader texturesLoader=new TexturesLoader();
    public UpdateCollisions updateCollisions=new UpdateCollisions();
    private final Logic logic=new Logic(this);

    //Переменные для сцен
    public Scene activeScene;
    private Scene newScene;
    private ArrayList<BasicObject> activeObjects = new ArrayList<>();

    //"рычаги" управления
    public boolean isActiveObjectsChange =false;

    //Настройки
    public Manager(ServiceObjects mainObjects1){
        serviceObjects =mainObjects1;

        EmptyScene.manager=this;

        BasicObject.manager=this;
        BasicObject.shapeDrawer=serviceObjects.shapeDrawer;

        Drawer.manager=this;
        Drawer.batch=serviceObjects.batch;
        Drawer.camera=serviceObjects.camera;

        //сюда вписываем первую сцену
        changeScene(new MainMenu());
    }

    //работа с объектами
    public void changeScene(Scene scene){
        newScene=scene;
    }

    private void changeScene(){
        texturesLoader.dispose();
        texturesLoader=new TexturesLoader();

        updateCollisions=new UpdateCollisions();

        activeScene=newScene;
        newScene.setup();
        activeObjects=newScene.getObjects();

        isActiveObjectsChange=false;
        newScene=null;
    }

    //рендер и тп
    @Override
    public void render(float v) {
        logic.update(activeObjects);
        updateCollisions.update();
        Drawer.draw(activeObjects);

        user.update();
        if(newScene!=null){
            changeScene();
            return;
        }
        updateActiveObjects();
    }

    private void updateActiveObjects(){
        if(isActiveObjectsChange)activeObjects=activeScene.getObjects();
        isActiveObjectsChange=false;
    }

    @Override
    public void resize(int i, int i1) {
        serviceObjects.viewport.update(i, i1);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
        texturesLoader.dispose();
    }
}
