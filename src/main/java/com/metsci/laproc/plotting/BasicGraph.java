package com.metsci.laproc.plotting;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A basic implementation of a graph
 * Created by robinsat on 9/20/2016.
 */
public class BasicGraph implements Graph {

    /** The X axis of the graph */
    private Axis xAxis;
    /** The Y axis of the graph */
    private Axis yAxis;
    /** The Z axis of the graph */
    private Axis zAxis;

    /** All data sets that can be plotted on this graph */
    private Collection<GraphableData> data;

    public BasicGraph (){
        data = new ArrayList<GraphableData>();
    }

    /**
     * Getter for the graph's X axis
     * @return the X axis
     */
    public Axis getXAxis() {
        return this.xAxis;
    }

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    public Axis getYAxis() {
        return this.yAxis;
    }

    /**
     * Getter for the graph's Z axis
     * @return the Z axis
     */
    public Axis getZAxis() {
        return this.zAxis;
    }

    /**
     * Setter for the graph's X axis
     * @param x the x axis to set
     */
    public void setXAxis(Axis x) {
        this.xAxis = x;
    }

    /**
     * Setter for the graph's Y axis
     * @param y the Y axis to set
     */
    public void setYAxis(Axis y) {
        this.yAxis = y;
    }

    /**
     * Setter for the graph's Z axis
     * @param z the Z axis to set
     */
    public void setZAxis(Axis z) {
        this.zAxis = z;
    }

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Iterable<GraphableData> getData() {
        return this.data;
    }

    /**
     * Adds a graphable data item
     * @param dat The data to add
     */
    public void addData(GraphableData dat) {
        this.data.add(dat);
    }
}
