package com.metsci.laproc.plotting;

import java.util.Map;

/**
 * Interface representing a point in a set of GraphableData
 * Created by robinsat on 10/25/2016.
 */
public interface GraphPoint {

    /**
     * Getter for the x value
     * @return The x value
     */
    double getX();

    /**
     * Getter for the y value
     * @return The y value
     */
    double getY();

    /**
     * Gets a map representing any additional data associated with this point
     * @return A map representing any additional data associated with this point
     */
    Map<String, Double> getAnalytics();
}
