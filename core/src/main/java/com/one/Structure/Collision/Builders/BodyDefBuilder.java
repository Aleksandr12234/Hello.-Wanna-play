package com.one.Structure.Collision.Builders;

import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyDefBuilder {
    private final BodyDef bodyDef=new BodyDef();

    public BodyDefBuilder(){
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //        BodyDef.BodyType.DynamicBody - динамическое тело
        //        BodyDef.BodyType.KinematicBody - Как статическое, но может двигаться (движущаяся платформа)
        //        BodyDef.BodyType.StaticBody - Статическое тело
        bodyDef.fixedRotation = false;//если True - тело не будет крутиться
        bodyDef.bullet = false;//Непрерывный просчет коллизий, для быстрых объектов
        bodyDef.linearDamping = 0.5f; // Гашение линейной скорости
        bodyDef.angularDamping = 0.5f; // Гашение вращения
    }

    public BodyDefBuilder setType(BodyDef.BodyType type){
        bodyDef.type=type;
        return this;
    }

    public BodyDefBuilder setFixedRotation(boolean fixedRotation){
        bodyDef.fixedRotation=fixedRotation;
        return this;
    }

    public BodyDefBuilder setFastCheckMod(boolean fastCheck){
        bodyDef.bullet=fastCheck;
        return this;
    }

    public BodyDefBuilder setLinearDamping(float value){
        bodyDef.linearDamping=value;
        return this;
    }

    public BodyDefBuilder setAngularDamping(float value){
        bodyDef.angularDamping=value;
        return this;
    }

    public BodyDef build(){
        return bodyDef;
    }
}
