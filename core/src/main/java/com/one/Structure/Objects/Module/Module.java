package com.one.Structure.Objects.Module;

import com.one.Structure.Objects.BasicObject;

public class Module<T extends BasicObject> extends BasicObject {
    private final T master;

    public Module(T master){
        this.master=master;
        transform=master.getTransform();
    }

    public T getMaster(){
        return master;
    }
}
