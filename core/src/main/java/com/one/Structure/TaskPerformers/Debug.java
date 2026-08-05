package com.one.Structure.TaskPerformers;

import com.badlogic.gdx.graphics.Color;
import com.one.Structure.Stock;
import com.one.Structure.TaskPerformers.DebugInstruments.DebugTaskLine;

import java.util.ArrayList;
import java.util.List;

public class Debug {
    static List<DebugTaskLine> taskLines=new ArrayList<>();

    public static void drawLine(float x1, float y1, float x2, float y2){
        taskLines.add(new DebugTaskLine(x1, y1, x2, y2));
    }

    public static void drawTask(){
        for (DebugTaskLine i : taskLines) {
            Stock.getTaskRunner().serviceObjects.shapeDrawer.line(i.x1, i.y1, i.x2, i.y2);
            Stock.getTaskRunner().serviceObjects.shapeDrawer.filledCircle(i.x2, i.y2, 0.025f, Color.CYAN);
        }
        taskLines.clear();
    }
}
