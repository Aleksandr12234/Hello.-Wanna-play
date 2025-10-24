package com.one.Project.Objects.UI.UI;

import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

public class UI extends BasicObject {
    Informer informer;

    public UI(Informer informer){
        this.informer=informer;
    }

    @Override
    public void draw() {
        Manager.serviceObjects.font.draw(
            Manager.serviceObjects.batch, ""+informer.getHp(),
            Manager.serviceObjects.camera.position.x-Manager.serviceObjects.camera.viewportWidth/2+20,
            Manager.serviceObjects.camera.position.y+Manager.serviceObjects.camera.viewportHeight/2-20
        );
        Manager.serviceObjects.font.draw(
            Manager.serviceObjects.batch, ""+informer.getEnergy(),
            Manager.serviceObjects.camera.position.x+Manager.serviceObjects.camera.viewportWidth/2-20,
            Manager.serviceObjects.camera.position.y+Manager.serviceObjects.camera.viewportHeight/2-20
        );
    }
}
