package com.one.Project.Objects.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Weapon {
    public static void loadWeapons(){
        String[] allFiles = Gdx.files.internal("assets.txt").readString().split("\n");

        for (String path : allFiles) {
            if (path.startsWith("Project/Weapon/Specifications/") && path.endsWith(".json")) {
                FileHandle file = Gdx.files.internal(path);
                System.out.println(file.name());
            }
        }
    }
}
