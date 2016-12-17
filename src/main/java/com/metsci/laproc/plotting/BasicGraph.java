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

    /** The metric to use for the x axis, if applicable */
    private ParametricFunction xAxisMetric;
    /** The metric to use for the y axis, if applicable */
    private ParametricFunction yAxisMetric;

    /** Allows the user to provide a custom X Axis descriptor */
    private String xAxisDescriptor;
    /** Allows the user to provide a custom Y Axis descriptor */
    private String yAxisDescriptor;

    /** Selected data set */
    private GraphableData selectedData;
    /** All data sets that can be plotted on this graph */
    private List<GraphableData> data;

    /**
     * Default constructor
     */
    public BasicGraph() {
        this("", null, null);
    }

    /**
     * Constructor
     * @param title The title to give to this graph
     */
    public BasicGraph(String title) {
        this(title, null, null);
    }

    /**
     * Constructor
     * @param xAxisMetric The x axis function
     * @param yAxisMetric The y axis function
     */
    public BasicGraph(ParametricFunction xAxisMetric, ParametricFunction yAxisMetric) {
        this("", xAxisMetric, yAxisMetric);
    }

    /**
     * Constructor
     * @param title The title
     * @param xAxisMetric The x axis function
     * @param yAxisMetric The y axis function
     */
    public BasicGraph(String title, ParametricFunction xAxisMetric, ParametricFunction yAxisMetric) {
        this.title = title;
        this.xAxisMetric = xAxisMetric;
        this.yAxisMetric = yAxisMetric;
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
        double xMin = 0; // The default lower bound
        double xMax = 1; // The default upper bound
        // Get the maximum and minimum bounds necessary to contain all of the data
        for(GraphableData d : this.data) {
            Axis bounds = d.getXBounds();
            if(bounds.getMin() < xMin)
                xMin = bounds.getMin();
            if(bounds.getMax() > xMax)
                xMax = bounds.getMax();
        }
        // Give a descriptor to this axis
        String descriptor = "X Axis";
        if(this.xAxisDescriptor != null)
            descriptor = this.xAxisDescriptor;
        else if(this.xAxisMetric != null)
            descriptor = this.xAxisMetric.getDescriptor();

        return new BasicAxis(xMin, xMax, descriptor);
    }

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    public Axis getYAxis() {
        double yMin = 0; // The default lower bound
        double yMax = 1; // The default upper bound
        // Get the maximum and minimum bounds necessary to contain all of the data
        for(GraphableData d : this.data) {
            Axis bounds = d.getYBounds();
            if(bounds.getMin() < yMin)
                yMin = bounds.getMin();
            if(bounds.getMax() > yMax)
                yMax = bounds.getMax();
        }

        // Give a descriptor to this axis
        String descriptor = "Y Axis";
        if(this.yAxisDescriptor != null)
            descriptor = this.yAxisDescriptor;
        else if(this.yAxisMetric != null)
            descriptor = this.yAxisMetric.getDescriptor();

        return new BasicAxis(yMin, yMax, descriptor);
    }

    /**
     * Sets the X axis descriptor to the given string
     * @param descriptor The string to set as the descriptor
     */
    public void setXAxisDescriptor(String descriptor) {
        this.xAxisDescriptor = descriptor;
    }

    /**
     * Sets the Y axis descriptor to the given string
     * @param descriptor The string to set as the descriptor
     */
    public void setYAxisDescriptor(String descriptor) {
        this.yAxisDescriptor = descriptor;
    }

    /**
     * Setter for the graph's title
     * @param title the title axis to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    public GraphPoint getClosestPoint(double x, double y) {
        if(this.data.isEmpty())
            return null;
        
        GraphPoint closestPoint = this.data.get(0).getDataPoint(x, y);
        double closestDistance = findDistance(closestPoint.getX(), x, closestPoint.getY(), y);
        for(int i = 1; i < data.size(); i++) {
            GraphPoint currentPoint = data.get(i).getDataPoint(x, y);
            double currentDistance = findDistance(currentPoint.getX(), x, currentPoint.getY(), y);
            if(currentDistance < closestDistance) {
                closestDistance = currentDistance;
                closestPoint = currentPoint;
            }
        }
        return closestPoint;
    }

    private double findDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
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
