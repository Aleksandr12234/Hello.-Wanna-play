package com.one.Project.Objects.Room1;

import com.one.Project.Objects.Items.FateCart;
import com.one.Project.Objects.Items.Item;
import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
    private ArrayList<Item> itemsLvl1=new ArrayList<>();
    private ArrayList<Item> itemsLvl2=new ArrayList<>();
    private ArrayList<Item> itemsLvl3=new ArrayList<>();
    private ArrayList<FateCart> fateCarts=new ArrayList<>();

    private ArrayList<Item> shuffleItemsLvl1;
    private ArrayList<Item> shuffleItemsLvl2;
    private ArrayList<Item> shuffleItemsLvl3;
    private ArrayList<FateCart> shuffleFateCarts;

    public void addItem(Item item){
        switch (item.lvl){
            case 1: itemsLvl1.add(item); break;
            case 2: itemsLvl2.add(item); break;
            case 3: itemsLvl3.add(item); break;
        }
    }

    public void addItem(FateCart fateCart){
        fateCarts.add(fateCart);
    }

    public void shuffle(){
        for (int i = 0; i < 4; i++) {
            shuffle(i);
        }
    }

    public void shuffle(int number){
        switch (number){
            case 0:
                shuffleFateCarts=new ArrayList<>(fateCarts);
                fateCarts=new ArrayList<>();
                Collections.shuffle(shuffleFateCarts);
                break;
            case 1:
                shuffleItemsLvl1=new ArrayList<>(itemsLvl1);
                itemsLvl1=new ArrayList<>();
                Collections.shuffle(shuffleItemsLvl1);
                break;
            case 2:
                shuffleItemsLvl2=new ArrayList<>(itemsLvl2);
                itemsLvl2=new ArrayList<>();
                Collections.shuffle(shuffleItemsLvl2);
                break;
            case 3:
                shuffleItemsLvl3=new ArrayList<>(itemsLvl3);
                itemsLvl3=new ArrayList<>();
                Collections.shuffle(shuffleItemsLvl3);
                break;
        }
    }

    public Item takeItem(int number){
        Item cart=null;
        switch (number){
            case 1:
                if(shuffleItemsLvl1.isEmpty())shuffle(1);
                if(shuffleItemsLvl1.isEmpty())return null;
                cart=shuffleItemsLvl1.get(shuffleItemsLvl1.size()-1);
                shuffleItemsLvl1.remove(shuffleItemsLvl1.size()-1);
                break;
            case 2:
                if(shuffleItemsLvl2.isEmpty())shuffle(2);
                if(shuffleItemsLvl2.isEmpty())return null;
                cart=shuffleItemsLvl2.get(shuffleItemsLvl2.size()-1);
                shuffleItemsLvl2.remove(shuffleItemsLvl2.size()-1);
                break;
            case 3:
                if(shuffleItemsLvl3.isEmpty())shuffle(3);
                if(shuffleItemsLvl3.isEmpty())return null;
                cart=shuffleItemsLvl3.get(shuffleItemsLvl3.size()-1);
                shuffleItemsLvl3.remove(shuffleItemsLvl3.size()-1);
                break;

        }
        return cart;
    }

    public FateCart takeFateCart(){
        if(shuffleFateCarts.isEmpty())shuffle(0);
        if(shuffleFateCarts.isEmpty()) return null;
        FateCart cart=shuffleFateCarts.get(shuffleFateCarts.size()-1);
        shuffleFateCarts.remove(shuffleFateCarts.size()-1);
        return cart;
    }
}
