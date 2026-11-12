package com.one.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.one.Project.BasicSettings;
import com.one.Structure.GameEntryPoint;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static void createApplication() {
        new Lwjgl3Application(new GameEntryPoint(), getDefaultConfiguration());
    }

    public static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

        //тут менять имя
        configuration.setTitle(BasicSettings.PROJECT_NAME);

        //fps
        configuration.setForegroundFPS(BasicSettings.FPS);

        if(BasicSettings.IS_FULLSCREEN) {
            //Полный экран
            configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        }else {
            //Окно
            configuration.setWindowedMode(BasicSettings.WINDOW_SIZE[0], BasicSettings.WINDOW_SIZE[1]);
        }

        // Смена иконок в lwjgl3/src/main/resources/ .
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        return configuration;
    }

}
