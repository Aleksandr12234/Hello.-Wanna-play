package com.one.Project.Objects.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.one.Project.Objects.Room1.Dealer;
import com.one.Structure.Stock;

public class ItemsLoader {
    public static void loadItems(Dealer dealer){
        String[] allFiles = Gdx.files.internal("assets.txt").readString().split("\n");

        for (String path : allFiles) {
            if (path.startsWith("Project/Weapon/Specifications/") && path.endsWith(".json")) {
                JsonValue value= Stock.getTaskRunner().assetsLoader.json.loadJson(path);
                float[] size= value.get("sizeOnThrow").asFloatArray();
                dealer.addItem(new Item(value.getString("name"),
                    value.getByte("lvl"),
                    value.getInt("durability"),
                    value.getFloat("damageOnTrow"),
                    value.getFloat("timeToEnemyStunOnThrow"),
                    value.getFloat("timeToAkariStunOnThrow"),
                    value.getFloat("speedOnThrow"), new Vector2(size[0], size[1]),
                    value.getInt("penetrationOnThrow") ));
            }
        }

        dealer.shuffle();
    }
}
