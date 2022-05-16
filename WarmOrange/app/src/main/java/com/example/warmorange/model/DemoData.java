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
        Demo demo = new Demo("naam", true, 18, 11, 2002, 2, 0);
        addDemo(demo);
    }
    public void addDemo(Demo demo) {
        demo.setID(++lastID);
        demos.add(demo);
    }
    public Demo getDemo(int id) {
        return demos.get(id);
    }
    public List<Demo> getAllDemos() {
        return Collections.list(demos.elements());
    }
}
