package com.one.Project;

import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.TestClasses.TestScene;

public class BasicSettings {
    //имя проекта
    public static String getProjectName(){
        return "Stock";
    }

    //Полный экран
    public static boolean isFullScreen(){
        return false;
    }

    //Размер окна
    public static int[] getWindowSize(){
        return new int[]{800, 500};
    }

    //начальная сцена
    public static EmptyScene getStartScene(){
        return new TestScene();
    }
}
