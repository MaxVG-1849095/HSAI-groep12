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
        Demo demo = new Demo("Iphone 13", true, 7, 6, 2022, 12, 0);
        addDemo(demo);
        demo = new Demo("Samsung Galaxy S22", true, 12, 05, 2022, 15, 36);
        demo.cancel();
        addDemo(demo);
        demo = new Demo("Samsung QLED 50Q64A (2021)", false, 25, 5, 2022, 14, 30);
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
