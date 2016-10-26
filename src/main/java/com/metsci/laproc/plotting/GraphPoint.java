package com.metsci.laproc.plotting;

import java.util.HashMap;

public class GraphPoint extends HashMap<String, Double> {

    private double xVal;
    private double yVal;

    public GraphPoint(double x, double y) {
        this.xVal = x;
        this.yVal = y;
    }

    public double getX() {
        return this.xVal;
    }

    public double getY() {
        return this.yVal;
    }
}
