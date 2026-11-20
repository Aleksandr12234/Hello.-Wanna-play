package com.one.Project.Scenes;

import com.one.Project.Objects.Items.ItemsLoader;
import com.one.Project.Objects.Room1.Dealer;
import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;

public class DefaultDevelopScene extends EmptyScene {
    @Override
    public void setup() {
        Stock.getTaskRunner().drawer.setDrawCollision(true);
        Stock.getTaskRunner().drawer.setBackgroundColor(0.5f, 0.5f, 0.5f);

        Dealer dealer=new Dealer();
        ItemsLoader.loadItems(dealer);

        System.out.println(dealer.takeItem(1));
        System.out.println(dealer.takeItem(1));
        System.out.println(dealer.takeItem(1));
    }
}
