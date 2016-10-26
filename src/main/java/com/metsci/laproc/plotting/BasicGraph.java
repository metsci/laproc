package com.metsci.laproc.plotting;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A basic implementation of a graph
 * Created by robinsat on 9/20/2016.
 */
public class BasicGraph implements Graph {

    /** The title of the graph */
    private String title;
    /** The X axis of the graph */
    private Axis xAxis;
    /** The Y axis of the graph */
    private Axis yAxis;
    /** The Z axis of the graph */
    private Axis zAxis;

    /** All data sets that can be plotted on this graph */
    private Collection<GraphableData> data;

    /**
     * Default constructor
     */
    public BasicGraph() {
        this("ROCCurve", new Axis(0, 1), new Axis(0, 1), new Axis(0, 1));
    }

    /**
     * Constructor
     * @param xAxis The x axis
     * @param yAxis The y axis
     */
    public BasicGraph(Axis xAxis, Axis yAxis) {
        this("ROCCurve", xAxis, yAxis, null);
    }

    /**
     * Constructor
     * @param title The title
     * @param xAxis The x axis
     * @param yAxis The y axis
     * @param zAxis The z axis
     */
    public BasicGraph(String title, Axis xAxis, Axis yAxis, Axis zAxis) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;

        this.data = new ArrayList<GraphableData>();
    }

    /**
     * Getter for the graph's title
     * @return the title
     */
    public String getTitle() {
        return this.title;
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
     * Setter for the graph's title
     * @param title the title axis to set
     */
    public void setTitle(String title) {
        this.title = title;
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
