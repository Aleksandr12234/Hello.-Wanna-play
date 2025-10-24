package com.one.Project.Scenes;

import com.one.Project.Objects.Creature.Creature;
import com.one.Project.Objects.Enemyes.EasyEnemy;
import com.one.Project.Objects.Skills.EasySkill;
import com.one.Project.Objects.UI.Camera.CameraController;
import com.one.Project.Objects.UI.UI.UI;
import com.one.Project.Objects.Wall.Wall;
import com.one.Project.Objects.Wearpons.Bullets.EasyBullet;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.taskPerformers.Drawer;
import com.one.Structure.testClasses.Square;

import java.util.ArrayList;
import java.util.HashMap;

public class GameplayTestScene extends EmptyScene {
    ArrayList<Creature> creatures=new ArrayList<>();
    ArrayList<Square> squares=new ArrayList<>();
    ArrayList<EasyEnemy> enemies=new ArrayList<>();
    ArrayList<EasyBullet> bullets=new ArrayList<>();
    ArrayList<Wall> walls=new ArrayList<>();
    ArrayList<EasySkill> easySkills=new ArrayList<>();
    CameraController cameraController;
    UI ui;

    HashMap<Class<?>, ArrayList<?>> allObjects=new HashMap<>();

    @Override
    public void setup() {
        Drawer.setBackgroundColor(0, 0.5f, 0);

        addObject(new Creature(0, 0, 50, 100).setColor(false, true));
        addObject(new Square(-500, 0, 0));
        addObject(new EasyEnemy(-200, 0, 50, 100));
        addObject(new Wall(0, -200, 300,10, 0).setColor(true, true));
        addObject(new EasySkill(100, 0));
        cameraController=new CameraController(creatures.get(0));
        ui=new UI(creatures.get(0));

        InitializeObjectsMap();
    }

    private void InitializeObjectsMap(){
        allObjects.put(Creature.class, creatures);
        allObjects.put(Square.class, squares);
        allObjects.put(EasyEnemy.class, enemies);
        allObjects.put(EasyBullet.class, bullets);
        allObjects.put(Wall.class, walls);
        allObjects.put(EasySkill.class, easySkills);
    }

    @Override
    public void addObject(BasicObject added) {


        if(added instanceof Creature)
            creatures.add((Creature) added);
        else if(added instanceof Square)
            squares.add((Square) added);
        else if (added instanceof EasyBullet)
            bullets.add((EasyBullet) added);
        else if (added instanceof EasyEnemy)
            enemies.add((EasyEnemy) added);
        else if (added instanceof Wall)
            walls.add((Wall) added);
        else if(added instanceof EasySkill)
            easySkills.add((EasySkill) added);
    }

    @Override
    public void removeObject(BasicObject removed) {
        if(removed instanceof Creature)
            creatures.remove((Creature) removed);
        else if(removed instanceof Square)
            squares.remove((Square) removed);
        else if (removed instanceof EasyBullet)
            bullets.remove((EasyBullet) removed);
        else if (removed instanceof EasyEnemy)
            enemies.remove((EasyEnemy) removed);
        else if (removed instanceof Wall)
            walls.remove((Wall) removed);
        else if(removed instanceof EasySkill)
            easySkills.remove((EasySkill) removed);
    }

    @Override
    public ArrayList<BasicObject> getObjects() {
        ArrayList<BasicObject> out =new ArrayList<>();
        out.addAll(creatures);
        out.addAll(squares);
        out.addAll(enemies);
        out.addAll(bullets);
        out.addAll(walls);
        out.addAll(easySkills);
        out.add(ui);
        out.add(cameraController);
        return out;
    }


}
