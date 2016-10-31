package com.metsci.laproc.plotting;

/**
 * This interface represents a graph with customizable axes that can be rendered with Glimpse.
 * Created by robinsat on 9/19/2016.
 */
public interface Graph {

    /**
     * Getter for the graph's title
     * @return the title
     */
    public String getTitle();

    /**
     * Getter for the graph's X axis
     * @return the X axis
     */
    public Axis getXAxis();

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    public Axis getYAxis();

    /**
     * Getter for the graph's Z axis
     * @return the Z axis
     */
    public Axis getZAxis();

    /**
     * Setter for the graph's title
     * @param title the title to set
     */
    public void setTitle(String title);

    /**
     * Setter for the graph's X axis
     * @param x the x axis to set
     */
    public void setXAxis(Axis x);

    /**
     * Setter for the graph's Y axis
     * @param y the Y axis to set
     */
    public void setYAxis(Axis y);

    /**
     * Setter for the graph's Z axis
     * @param z the Z axis to set
     */
    public void setZAxis(Axis z);

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Iterable<GraphableData> getData();

}
