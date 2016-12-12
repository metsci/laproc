package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.*;

import java.lang.IllegalArgumentException;

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

    private ParametricFunction xAxisMetric;
    private ParametricFunction yAxisMetric;
    private ParametricFunction zAxisMetric;

    /** Selected data set */
    private GraphableData selectedData;
    /** All data sets that can be plotted on this graph */
    private Collection<GraphableData> data;

    /**
     * Default constructor
     */
    public BasicGraph() {
        this.title = "";
        this.data = new ArrayList<GraphableData>();
    }

    /**
     * Constructor
     * @param title The title to give to this graph
     */
    public BasicGraph(String title) {
        this();
        this.title = title;
    }

    /**
     * Constructor
     * @param xAxis The x axis
     * @param yAxis The y axis
     */
    public BasicGraph(Axis xAxis, Axis yAxis) {
        this("", xAxis, yAxis, null);
    }

    /**
     * Constructor
     * @param title The title
     * @param xAxis The x axis
     * @param yAxis The y axis
     * @param zAxis The z axis
     */
    public BasicGraph(String title, Axis xAxis, Axis yAxis, Axis zAxis) {
        this(title);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
    }

    /**
     * Constructor
     * @param title The title
     * @param xAxisMetric The x axis function
     * @param yAxisMetric The y axis function
     */
    public BasicGraph(String title, ParametricFunction xAxisMetric, ParametricFunction yAxisMetric) {
        this(title);
        this.xAxisMetric = xAxisMetric;
        this.yAxisMetric = yAxisMetric;
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
        if(xAxisMetric == null ) {
            if(this.xAxis == null) {
                this.xAxis = new BasicAxis(0, 1);
            }
            return this.xAxis;
        }

        double xMin = 0;
        double xMax = 1;
        for(GraphableData d : this.data) {
            Axis bounds = d.getXBounds();
            if(bounds.getMin() < xMin)
                xMin = bounds.getMin();
            if(bounds.getMax() > xMax)
                xMax = bounds.getMax();
        }
        return new BasicAxis(xMin, xMax, xAxisMetric.getDescriptor());
    }

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    public Axis getYAxis() {
        if(yAxisMetric == null) {
            if(this.yAxis == null) {
                this.yAxis = new BasicAxis(0, 1);
            }
            return this.yAxis;
        }
        double yMin = 0;
        double yMax = 1;
        for(GraphableData d : this.data) {
            Axis bounds = d.getYBounds();
            if(bounds.getMin() < yMin)
                yMin = bounds.getMin();
            if(bounds.getMax() > yMax)
                yMax = bounds.getMax();
        }
        return new BasicAxis(yMin, yMax, yAxisMetric.getDescriptor());
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
     * Selects a GraphableData object
     * @param data the data to select
     */
    public void setSelectedData (GraphableData data) {
        if(!this.data.contains(data)){
            throw new IllegalArgumentException();
        }
        this.selectedData = data;
    }

    /**
     * Gets the currently selected GraphableData
     * @return the currently selected GraphableData
     */
    public GraphableData getSelectedData(){
        return this.selectedData;
    }

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Iterable<GraphableData> getData() {
        return this.data;
    }

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    public Collection<ParametricFunction> getAxisFunctions() {
        // This implementation uses a map to easily union all possible axes
        Map<String, ParametricFunction> functionUnion = new HashMap<String, ParametricFunction>();
        for(GraphableData d : this.data) {
            List<ParametricFunction> axisFunctions = d.getAxes();
            for(ParametricFunction f : axisFunctions) {
                functionUnion.put(f.getDescriptor(), f);
            }
        }
        return functionUnion.values();
    }

    /**
     * Adds a graphable data item
     * @param dat The data to add
     */
    public void addData(GraphableData dat) {
        this.data.add(dat);
        if(selectedData == null){
            this.selectedData = dat;
        }
    }

    /**
     * Sets all GraphableData sets on this graph to use the same set of axes
     * @param xAxis The function to use for the X Axis
     * @param yAxis The function to use for the Y Axis
     */
    public void useAxisFunctions(ParametricFunction xAxis, ParametricFunction yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;
        for(GraphableData d : this.data) {
            d.useAxes(xAxis, yAxis);
        }
    }
}
