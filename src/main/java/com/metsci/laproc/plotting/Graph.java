package com.metsci.laproc.plotting;

import com.metsci.laproc.pointmetrics.ParametricFunction;

import java.util.Collection;

/**
 * This interface represents a graph with customizable axes that can be rendered with Glimpse.
 * Created by robinsat on 9/19/2016.
 */
public interface Graph {

    /**
     * Getter for the graph's title
     * @return the title
     */
    String getTitle();

    /**
     * Getter for the graph's X axis
     * @return the X axis
     */
    Axis getXAxis();

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    Axis getYAxis();

    /**
     * Getter for the graph's Z axis
     * @return the Z axis
     */
    Axis getZAxis();

    /**
     * Setter for the graph's title
     * @param title the title to set
     */
    void setTitle(String title);

    /**
     * Setter for the graph's X axis
     * @param x the x axis to set
     */
    void setXAxis(Axis x);

    /**
     * Setter for the graph's Y axis
     * @param y the Y axis to set
     */
    void setYAxis(Axis y);

    /**
     * Setter for the graph's Z axis
     * @param z the Z axis to set
     */
    void setZAxis(Axis z);

    /**
     * Setter for selected graph data
     * @param data the data to set
     */
    void setSelectedData(GraphableData data);

    /**
     * Getter for selected graph data
     * @return selected graph data
     */
    GraphableData getSelectedData();

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Iterable<GraphableData> getData();

    /**
     * Returns a list of all possible axes to use for this graph
     * @return The list of axes that can be used for this graph
     */
    public Collection<ParametricFunction> getAxes();

    /**
     * Sets all GraphableData sets on this graph to use the same set of axes
     * @param xAxis The function to use for the X Axis
     * @param yAxis The function to use for the Y Axis
     */
    void useAxes(ParametricFunction xAxis, ParametricFunction yAxis);

}
