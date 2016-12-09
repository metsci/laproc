package com.metsci.laproc.plotting;

/**
 * Interface representing a single axis of a graph
 * Created by robinsat on 9/20/2016.
 */
public interface Axis {

    /**
     * Getter for the displayable name of this axis
     * @return The displayable name of this axis
     */
    String getName();

    /**
     * Getter for the unit name for this axis
     * @return The unit name as it should be displayed on a graph
     */
    String getUnit();

    /**
     * Getter for the lower bound of the axis
     * @return The lower bound of this axis
     */
    double getMin();

    /**
     * Getter for the upper bound of the axis
     * @return The upper bound of this axis
     */
    double getMax();

    /**
     * Setter for the displayable name of this axis
     * @param name The displayable name of this axis
     */
    void setName(String name);

    /**
     * Setter for the unit name for this axis
     * @param unit The unit name as it should be displayed on a graph
     */
    void setUnit(String unit);

    /**
     * Sets the upper and lower bounds of the axis
     * @param min The lower bound, inclusive
     * @param max The upper bound, inclusive
     */
    void setBounds(double min, double max);
}
