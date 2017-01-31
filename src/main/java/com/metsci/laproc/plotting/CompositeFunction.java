package com.metsci.laproc.plotting;

import javafx.util.Pair;

import java.util.List;

/**
 * An abstract for a "composite" function, or a function that computes based on the output of other functions.
 * Created by robinsat on 12/13/2016.
 */
public abstract class CompositeFunction implements GraphableFunction<List<GraphableData>> {

    /** The name of this function, to assign to the GraphableData result */
    private String name;

    /**
     * Constructor
     */
    public CompositeFunction() {
        this.name = "";
    }

    /**
     * Sets a name to use for the output of this function
     * @param name The name to use for the output of this function
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Executes the function and returns the resulting data set
     * @return GraphableData
     */
    public GraphableData compute(List<GraphableData> input) {
        BasicGraphableData data = new BasicGraphableData(this.name);
        if(input.isEmpty()) // Avoid null pointers and index out of bounds exceptions
            return data;

        Pair<Double, Double> bounds = getInterpolationBounds(input); // Find the bounds of the data to analyze
        double min = bounds.getKey();
        double max = bounds.getValue();
        double[] xValues = input.get(0).getXValues(); // Arbitrarily base the x values off of the first data set
        double[] yValues = input.get(0).getYValues();

        for(int i = 0; i < xValues.length; i++) {
            double currentValue = xValues[i];
            if(currentValue < min || currentValue > max ) // Don't bother if it's outside the bounds
                continue;

            // Create an array to represent all calculated Y values that will be associated with the current x
            double[] calculatedYVals = new double[input.size()];
            calculatedYVals[0] = yValues[i];
            // Iterate over all data sets in the input
            for(int j = 1; j < input.size(); j++) {
                // Use linear interpolation
                GraphPoint lowPoint = input.get(j).getPointLessOrEqual(currentValue);
                GraphPoint highPoint = input.get(j).getPointGreaterOrEqual(currentValue);

                calculatedYVals[j] = lowPoint.getY() + (highPoint.getY() - lowPoint.getY()) *
                        (currentValue - lowPoint.getX()) / (highPoint.getX() - lowPoint.getX());
            }

            // Use the set of Y values to finish executing the function
            double result = computeFromYValues(calculatedYVals);
            data.addPoint(currentValue, result);
        }


        return data;
    }

    /**
     * Uses the y values for several data sets at a given x to compute output
     * @param yValues The y values for several data sets at a given x
     * @return The function output
     */
    protected abstract double computeFromYValues(double[] yValues);

    /**
     * A helper method to find the bounds to use for interpolation
     * This finds the interval of x values that is accounted for by the superset of data sets
     * This assumes that the input has size >= 1, which the compute method should check for
     * @return An interval across which all x values on all data sets can be found by interpolation
     */
    private Pair<Double, Double> getInterpolationBounds(List<GraphableData> input) {
        Axis first = input.get(0).getXBounds();
        double min = first.getMin();
        double max = first.getMax();
        for(int i = 1; i < input.size(); i++) {
            Axis currentBounds = input.get(i).getXBounds();
            if(currentBounds.getMin() < min)
                min = currentBounds.getMin();
            if(currentBounds.getMax() > max)
                max = currentBounds.getMax();
        }
        return new Pair<Double, Double>(min, max);
    }

    /**
     * Calculates the average. This is useful for multiple functions
     * @param values The values to use for the average
     * @return The average of the values
     */
    protected double calculateAverage(double[] values) {
        double sum = 0;
        for(int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    }
}
