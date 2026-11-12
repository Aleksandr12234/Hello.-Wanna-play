package com.one.Structure;

import com.one.Structure.TaskPerformers.AssetsLoader;
import com.one.Structure.TaskPerformers.Drawer;
import com.one.Structure.TaskPerformers.Logic;
import com.one.Structure.TaskPerformers.ScenesManager;
import com.one.Structure.TaskPerformers.UserInput;
import com.one.Structure.Collision.UpdateCollisions;

public class TaskRunner {
    public final Logic logic=new Logic();
    public final UserInput userInput=new UserInput();
    public final AssetsLoader assetsLoader =new AssetsLoader();
    public final UpdateCollisions updateCollisions=new UpdateCollisions();
    public final ScenesManager scenesManager=new ScenesManager();
    public final Drawer drawer=new Drawer();

    public void update(float v){
        logic.update();
        updateCollisions.update(v);
        drawer.draw();
        userInput.update();
        scenesManager.update();
    }
}
