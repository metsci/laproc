package com.metsci.laproc.plotting;

/**
 * Interface representing a single axis of a graph
 * Created by robinsat on 9/20/2016.
 */
public class BasicAxis implements Axis{

    /** The displayable name of this axis */
    private String name;
    /** The name of the units this axis uses */
    private String unit;
    /** The lower bound of this axis */
    private double min;
    /** The upper bound of this axis */
    private double max;

    /**
     * Constructor
     * @param min The lower bound of this axis
     * @param max The upper bound of this axis
     */
    public BasicAxis(double min, double max) {
        this(min, max, "", "");
    }

    /**
     * Constructor
     * @param min The lower bound of this axis
     * @param max The upper bound of this axis
     * @param name The displayable name of this axis
     */
    public BasicAxis(double min, double max, String name) {
        this(min, max, name, "");
    }

    /**
     * Constructor
     * @param min The lower bound of this axis
     * @param max The upper bound of this axis
     * @param name The displayable name of this axis
     * @param unit The unit to uicomponents for this axis
     */
    public BasicAxis(double min, double max, String name, String unit) {
        this.min = min;
        this.max = max;
        this.name = name;
        this.unit = unit;
    }

    /**
     * Getter for the displayable name of this axis
     * @return The displayable name of this axis
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the unit name for this axis
     * @return The unit name as it should be displayed on a graph
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * Getter for the lower bound of the axis
     * @return The lower bound of this axis
     */
    public double getMin() {
        return this.min;
    }

    /**
     * Getter for the upper bound of the axis
     * @return The upper bound of this axis
     */
    public double getMax() {
        return this.max;
    }

    /**
     * Setter for the displayable name of this axis
     * @param name The displayable name of this axis
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the unit name for this axis
     * @param unit The unit name as it should be displayed on a graph
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Sets the upper and lower bounds of the axis
     * @param min The lower bound, inclusive
     * @param max The upper bound, inclusive
     */
    public void setBounds(double min, double max) {
        if(min > max){
            throw new IllegalArgumentException("Minimum value cannot exceed the maximum");
        }
        this.min = min;
        this.max = max;
    }
}
