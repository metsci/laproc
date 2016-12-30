package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.lang.reflect.Array;
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

    /** All data sets that are displayed on the graph */
    private List<GraphableData> data;
    /** List determining if a set of data is displayed */
    private List<Boolean> isDisplayed;

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
        this.isDisplayed = new ArrayList<Boolean>();
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
        for(GraphableData d : this.getDisplayedData()) {
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
        for(GraphableData d : this.getDisplayedData()) {
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
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Iterable<GraphableData> getDisplayedData() {
        ArrayList<GraphableData> displayed = new ArrayList<GraphableData>();
        for(int i = 0; i < this.data.size(); i++){
            if(this.isDisplayed.get(i)){
                displayed.add(this.data.get(i));
            }
        }
        return displayed;
    }

    public Iterable<GraphableData> getHiddenData() {
        ArrayList<GraphableData> hidden = new ArrayList<GraphableData>();
        for(int i = 0; i < this.data.size(); i++){
            if(!this.isDisplayed.get(i)){
                hidden.add(this.data.get(i));
            }
        }
        return hidden;
    }

    public List<GraphableData> getData() {
        return this.data;
    }

    public List<Boolean> getDisplayed() {
        return this.isDisplayed;
    }

    /**
     * Gets the closest value on the plot to the value provided. This is probably a point that was clicked
     * @param x The x value to compare against
     * @param y The y value to compare against
     * @return The closest value on the plot to the value provided.
     */
    public GraphPoint[] getDisplayedClosestPoints(double x, double y) {
        List<GraphableData> displayedData = (ArrayList<GraphableData>)this.getDisplayedData();
        GraphPoint[] closestPoints = new GraphPoint[displayedData.size()];
        for(int i = 0; i < closestPoints.length; i++){
            closestPoints[i] = displayedData.get(i).getDataPoint(x,y);
        }
        return closestPoints;
    }

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    public Collection<ParametricFunction> getDisplayedAxisFunctions() {
        // This implementation uses a map to easily union all possible axes
        Map<String, ParametricFunction> functionUnion = new HashMap<String, ParametricFunction>();
        for(GraphableData d : this.getDisplayedData()) {
            List<ParametricFunction> axisFunctions = d.getAxes();
            for(ParametricFunction f : axisFunctions) {
                functionUnion.put(f.getDescriptor(), f);
            }
        }
        return functionUnion.values();
    }

    /**
     * Sets all GraphableData sets on this graph to use the same set of axes
     * @param xAxis The function to use for the X Axis
     * @param yAxis The function to use for the Y Axis
     */
    public void useAxisFunctions(ParametricFunction xAxis, ParametricFunction yAxis) {
        this.xAxisMetric = xAxis;
        this.yAxisMetric = yAxis;
        for(GraphableData d : this.getDisplayedData()) {
            d.useAxes(xAxis, yAxis);
        }
    }

    public void addData(GraphableData<?> dat, boolean display) {
        this.data.add(dat);
        this.isDisplayed.add(display);
    }

    public void setDataDisplay(GraphableData<?> dat, boolean display) {
        this.isDisplayed.set(this.data.indexOf(dat), display);
    }

	public void removeData(GraphableData<?> graphSet) {
		int index = this.data.indexOf(graphSet);
        this.data.remove(index);
        this.isDisplayed.remove(index);
	}
}
