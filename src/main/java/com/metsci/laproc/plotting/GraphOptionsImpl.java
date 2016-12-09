package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.Collection;
import java.util.List;

/**
 * Created by robinsat on 11/30/2016.
 */
public abstract class GraphOptionsImpl implements GraphOptions {

    private ParametricFunction xAxis;
    private ParametricFunction yAxis;
    private int numPoints;

    private Collection<ParametricFunction> functions;

    public ParametricFunction getXAxisFunction() {
        return xAxis;
    }

    public ParametricFunction getYAxisFunction() {
        return yAxis;
    }

    public Collection<ParametricFunction> getAllFunctions() {
        return functions;
    }

    public void useFunctions(ParametricFunction xAxis, ParametricFunction yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    protected void addFunction(ParametricFunction func) {
        this.functions.add(func);
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

}
