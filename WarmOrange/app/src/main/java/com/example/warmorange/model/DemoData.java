package com.example.warmorange.model;

import com.example.warmorange.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class DemoData {
    private Vector<Demo> demos = new Vector<>();
    private int lastID = -1;
    public DemoData(){
        fillData();
    }
    private void fillData() {
        Demo demo = new Demo("naam 1", true, 18, 11, 2002, 2, 0);
        addDemo(demo);
        demo = new Demo("qsldkjf sldfk jdsmj mlfd 2", false, 12, 05, 2022, 15, 36);
        demo.cancel();
        addDemo(demo);
        demo = new Demo("nog een demo over een random product 3", false, 15, 12, 2023, 14, 00);
        addDemo(demo);
    }
    public void addDemo(Demo demo) {
        demo.setID(++lastID);
        demos.add(0, demo);
    }
    public Demo getDemo(int id) {
        return demos.get(id);
    }
    public List<Demo> getAllDemos() {
        return Collections.list(demos.elements());
    }
}
