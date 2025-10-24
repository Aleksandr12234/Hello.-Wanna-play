package com.one.Project.Objects.Enemyes;

import com.one.Project.Objects.ColorSettings.ColorCollision;
import com.one.Project.Objects.Wearpons.Bullets.CreatureBullet;
import com.one.Project.Objects.Wearpons.Bullets.EasyBullet;
import com.one.Structure.Objects.BasicObject;
import com.one.Structure.collision.Figures.LineCollision;
import com.one.Structure.collision.Figures.RectCollision;
import com.one.Structure.collision.Objects.PhysicalObject;

public class EasyEnemyCollision extends PhysicalObject implements ColorCollision {
    boolean black=true, white=false;

    public EasyEnemyCollision(BasicObject master) {
        super(master);
    }

    @Override
    public void action(LineCollision lineCollision) {
        if(lineCollision.master.master instanceof EasyBullet){
            if(colorCheck(
                ((ColorCollision) lineCollision.master).getBlack(),
                ((ColorCollision) lineCollision.master).getWhite(),
                black, white))
                ((EasyEnemy) master).hp-=50;
        }
        if(((EasyEnemy) master).hp<=0){
            if (lineCollision.master.master instanceof CreatureBullet)
                ((CreatureBullet) lineCollision.master.master).addEnergy();
            dispose=true;
            master.dispose=true;
        }
    }

    private boolean colorCheck(boolean black1, boolean white1, boolean black2, boolean white2){
        return (black1 && black2) || (white1 && white2);
    }

    @Override
    public void action(RectCollision rectCollision) {
        super.action(rectCollision);
    }

    @Override
    public boolean getBlack() {
        return black;
    }

    @Override
    public boolean getWhite() {
        return white;
    }

    @Override
    public void setColor(boolean black, boolean white) {
        this.black=black;
        this.white=white;
    }
}
