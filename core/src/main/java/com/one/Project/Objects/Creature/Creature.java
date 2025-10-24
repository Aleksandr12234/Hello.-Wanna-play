package com.one.Project.Objects.Creature;

import com.badlogic.gdx.math.Vector2;
import com.one.Project.Objects.ColorSettings.PhysicColorCollision;
import com.one.Project.Objects.Skills.EasySkill;
import com.one.Project.Objects.UI.Camera.Observed;
import com.one.Project.Objects.UI.UI.Informer;
import com.one.Project.Objects.Wearpons.Rifle;
import com.one.Structure.Manager;
import com.one.Structure.Objects.BasicObject;

public class Creature extends BasicObject implements Observed, Informer {
    //Поля
    CreatureAnimation creatureAnimation = new CreatureAnimation(this);

    private final PhysicColorCollision collision= new PhysicColorCollision(this);

    public int energy=100;

    private EasySkill skill;

    private final Rifle weapon=new Rifle(this);

    Vector2 undoDirection=new Vector2();
    float speed, maxSpeed=5;

    //Настройка
    public Creature(float x, float y, float width, float height){
        position.x=x;
        position.y=y;
        size.x=width;
        size.y=height;
    }

    public Creature setColor(boolean black, boolean white){
        collision.setColor(black, white);
        return this;
    }

    public void reverseColor(){
        collision.setColor(!collision.getBlack(), !collision.getWhite());
        creatureAnimation.switchColor();
    }

    public void setSkill(EasySkill skill){
        this.skill=skill;
    }

    public void addEnergy(int add){
        energy+=add;
        int maxEnergy = 100;
        if(energy> maxEnergy)energy= maxEnergy;
    }

    //Логика
    @Override
    public void doLogic() {
        collision.updateStatus();

        move();
        shoot();
        useSkill();
    }

    private void move(){
        Vector2 direction=new Vector2();
        if(Manager.user.getKeysClamped(0)){
            direction.y+=1;
        }
        if (Manager.user.getKeysClamped(1)) {
            direction.y-=1;
        }
        if (Manager.user.getKeysClamped(2)) {
            direction.x-=1;
            creatureAnimation.direction=-1;
        }
        if (Manager.user.getKeysClamped(3)) {
            direction.x+=1;
            creatureAnimation.direction=1;
        }

        if(direction.len()==0){
            if(speed>0.0)speed-=0.5f;
            else speed=0;
            undoDirection.nor().scl(speed);
            creatureAnimation.isMove=false;
        } else if (speed<maxSpeed) {
            creatureAnimation.isMove=true;
            if(creatureAnimation.activeSprite>8)creatureAnimation.activeSprite=0;
            speed+=(maxSpeed-speed)/(maxSpeed*2f);
        }

        direction = direction.nor().scl(speed);
        if(direction.len()!=0)undoDirection.set(direction);

        position.x += undoDirection.x;
        position.y += undoDirection.y;
    }

    private void shoot(){
        Vector2 vector2=new Vector2(Manager.user.getMousePosition().x-position.x,
            Manager.user.getMousePosition().y-position.y);
        weapon.updateAngle(vector2.angleRad());

        energy=weapon.shoot(energy, Manager.user.getKeysClamped(5));
    }

    private void useSkill(){
        if(Manager.user.getKeysPressed(4)){
            reverseColor();
            if(skill!=null)energy=skill.use(energy);
        }
        if(skill!=null && skill.dispose)skill=null;
    }

    //Отрисовка
    @Override
    public void draw() {
        creatureAnimation.draw();
    }

    //UI
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public int getHp() {
        return 100;
    }

    @Override
    public int getEnergy() {
        return energy;
    }
}
