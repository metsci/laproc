package com.metsci.laproc.plotting;

import com.metsci.glimpse.axis.Axis1D;

import java.util.Collection;

/**
 * This interface represents a graph with customizable axes that can be rendered with Glimpse.
 * Created by robinsat on 9/19/2016.
 */
public interface Graph {

    /**
     * Getter for the graph's X axis
     * @return the X axis
     */
    public Axis1D getXAxis();

    /**
     * Getter for the graph's Y axis
     * @return the Y axis
     */
    public Axis1D getYAxis();

    /**
     * Getter for the graph's Z axis
     * @return the Z axis
     */
    public Axis1D getZAxis();

    /**
     * Setter for the graph's X axis
     * @param x the x axis to set
     */
    public void setXAxis(Axis1D x);

    /**
     * Setter for the graph's Y axis
     * @param y the Y axis to set
     */
    public void setYAxis(Axis1D y);

    /**
     * Setter for the graph's Z axis
     * @param z the Z axis to set
     */
    public void setZAxis(Axis1D z);

    /**
     * Getter for all of the graphable data associated with this graph
     * @return The graphable data associated with this graph
     */
    public Collection<GraphableData> getData();

}
