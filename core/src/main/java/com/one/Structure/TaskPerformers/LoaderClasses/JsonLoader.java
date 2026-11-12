package com.one.Structure.TaskPerformers.LoaderClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

public class JsonLoader {
    private final HashMap<String, JsonValue> jsonMap = new HashMap<>();
    private final JsonReader reader = new JsonReader();

    public JsonValue loadJson(String path) {
        if (jsonMap.containsKey(path)) return jsonMap.get(path);

        JsonValue jsonValue = reader.parse(Gdx.files.internal(path));
        jsonMap.put(path, jsonValue);
        return jsonValue;
    }

    public void dispose() {
        jsonMap.clear();
    }
}
