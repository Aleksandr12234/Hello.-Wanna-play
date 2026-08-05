package com.one.Structure.TaskPerformers;

import com.one.Structure.ServiceObjects;
import com.one.Structure.UserInput.UserInput;
import com.one.Structure.Collision.UpdateCollisions;

public class TaskRunner {
    public final ServiceObjects serviceObjects;
    public final UserInput userInput=new UserInput();
    public final AssetsLoader assetsLoader =new AssetsLoader();
    public final UpdateCollisions updateCollisions=new UpdateCollisions();
    public final ScenesManager scenesManager=new ScenesManager();
    public final Drawer drawer=new Drawer();
    public final Logic logic=new Logic();

    public TaskRunner(ServiceObjects serviceObjects){
        this.serviceObjects=serviceObjects;
    }

    public void update(){
        logic.update();
        updateCollisions.update();
        drawer.draw();
        userInput.update();
        scenesManager.update();
    }
}
