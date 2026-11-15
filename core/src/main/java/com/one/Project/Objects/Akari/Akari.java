package com.one.Project.Objects.Akari;

import com.one.Project.Objects.Akari.Modules.AkariCollision;
import com.one.Project.Objects.Akari.Modules.AkariLegs;
import com.one.Structure.Objects.BasicObject;

public class Akari extends BasicObject {
    public final AkariCollision akariCollision=new AkariCollision(this);
    public final AkariLegs akariLegs=new AkariLegs(this);

    public Akari() {
        transform.size.set(0.75f, 0.75f);
    }
}
