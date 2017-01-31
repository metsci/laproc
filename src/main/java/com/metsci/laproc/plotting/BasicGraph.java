package com.metsci.laproc.plotting;

import java.util.*;

/**
 * A basic implementation of a graph
 * Created by robinsat on 9/20/2016.
 */
public class BasicGraph implements Graph {

    /** The title of the graph */
    private String title;

    /** Allows the user to provide a custom X Axis descriptor */
    private String xAxisDescriptor;
    /** Allows the user to provide a custom Y Axis descriptor */
    private String yAxisDescriptor;

    /** All data sets that are in the graph paired with whether they are being displayed */
    private List<GraphableData> data;

    /**
     * Default constructor
     */
    public BasicGraph() {
        this("");
    }


    /**
     * Constructor
     * @param title The title
     */
    public BasicGraph(String title) {
        this.title = title;
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
        for(GraphableData d : this.getData()){
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
        for(GraphableData d : this.getData()) {
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

    public List<GraphableData> getData() {
        return this.data;
    }

    /**
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    public GraphPoint[] getClosestPoints(double x, double y) {
        List<GraphableData> displayedData = this.getData();
        GraphPoint[] closestPoints = new GraphPoint[displayedData.size()];
        for(int i = 0; i < closestPoints.length; i++){
            closestPoints[i] = displayedData.get(i).getDataPoint(x,y);
        }
        return closestPoints;
    }

    public void addData(GraphableData<?> data) {
        this.data.add(data);
    }

	public void removeData(GraphableData<?> graphSet) {
        this.data.remove(graphSet);
	}

}
