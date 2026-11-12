package com.one.Project;

import com.one.Project.Scenes.DefaultDevelopScene;
import com.one.Structure.Scenes.EmptyScene;

public class BasicSettings {
    public static final String PROJECT_NAME="Structure";
    public static final boolean IS_FULLSCREEN=false;
    public static final int[] WINDOW_SIZE=new int[]{800, 500};
    public static final int[] VIEWPORT=new int[]{8, 5};
    public static final int FPS=60;

    //начальная сцена
    public static EmptyScene getStartScene(){
        return new DefaultDevelopScene();
    }
}
