package com.one.Structure.TestClasses;

import com.one.Structure.Scenes.EmptyScene;
import com.one.Structure.Stock;
import com.one.Structure.TaskPerformers.UI.Button;
import com.one.Structure.TaskPerformers.UI.Text;

public class TestScene extends EmptyScene {

    public void setup(){
        Stock.getTaskRunner().drawer.setDrawCollision(false);

        Text text=new Text();
        text.getTransform().setPosition(0.5f, 0);
        text.setColor(0, 1, 1,1);
        text.scale=0.01f;
        text.text="one";
        addObject("UI2", text);

        String[] massive=new String[]{"one", "two", "three"};
        final int[] i = {0};
        Button button=new Button(0.5f, 1, 0.5f, 0.25f);
        button.released=()-> {
            i[0]++;
            if(i[0]== massive.length)i[0]=0;
            text.text=massive[i[0]];
        };
        addObject("UI2", button);


        Text text1=new Text();
        text1.getTransform().setPosition(-0.5f, 0);
        text1.setColor(1, 1, 0,1);
        text1.scale=0.01f;
        text1.text="one";
        addObject("UI1", text1);

        final int[] i1 = {0};
        button=new Button(-0.5f, 1, 0.5f, 0.25f);
        button.pressed=()-> {
            i1[0]++;
            if(i1[0]== massive.length)i1[0]=0;
            text1.text=massive[i1[0]];
        };
        addObject("UI1", button);

        layers.get("UI2").number=1;
        layers.get("UI1").number=-1;

        TestPhysicRect plate=new TestPhysicRect(0, -1, 5, 0.5f, true);
        addObject(plate);

        addObject(new TestControlRect(-4, 0, 1f, 1));

    }
}
