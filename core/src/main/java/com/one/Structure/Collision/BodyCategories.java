package com.one.Structure.Collision;

import java.util.HashMap;

public class BodyCategories {
    private final HashMap<String, Short> categories =new HashMap<>();

    public void addCategory(String name){
        if(categories.containsKey(name))return;
        if(categories.size()>15) throw new IllegalStateException("BodyCategories: to many categories");
        categories.put(name, (short) (1 << categories.size()));
    }

    public Short getCategory(String name){
        return categories.get(name);
    }

    public void dispose(){
        categories.clear();
    }
}
