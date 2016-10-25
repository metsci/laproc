package com.metsci.laproc.plotting;

import java.util.Map;

public class GraphPoint {

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
