package com.metsci.laproc.plotting;

import com.metsci.glimpse.util.Pair;
import com.metsci.laproc.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An abstract for a "composite" function, or a function that computes based on the output of other functions.
 * Created by robinsat on 12/13/2016.
 */
public class CompositeFunction implements GraphableFunction<Iterable<GraphableData>> {

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
    public GraphableData compute(Iterable<GraphableData> input) {
        GraphableDataWithMetrics<CompositePoint> data = new GraphableDataWithMetrics<CompositePoint>(
               "", new CompositePointXMetric(), new AverageMetric());
        if(!input.iterator().hasNext()) // Avoid null pointers and index out of bounds exceptions
            return data;

        Pair<Double, Double> bounds = getInterpolationBounds(input); // Find the bounds of the data to analyze
        double min = bounds.first();
        double max = bounds.second();
        double[] xValues = populateArray(min, max, getNumComputationPoints());

        for(int i = 0; i < xValues.length; i++) {
            double currentValue = xValues[i];

            // Create a list to represent all calculated Y values that will be associated with the current x
            List<Double> calculatedYVals = new ArrayList<Double>();

            // Iterate over all data sets in the input
            Iterator<GraphableData> iterator = input.iterator();
            while(iterator.hasNext()) {
                GraphableData item = iterator.next();
                // Use linear interpolation
                GraphPoint lowPoint = item.getPointLessOrEqual(currentValue);
                GraphPoint highPoint = item.getPointGreaterOrEqual(currentValue);

                if(lowPoint != null) {
                    if(highPoint == null || lowPoint.getY() == highPoint.getY()) {
                        calculatedYVals.add(lowPoint.getY());
                    } else {
                        calculatedYVals.add(lowPoint.getY() + (highPoint.getY() - lowPoint.getY()) *
                                (currentValue - lowPoint.getX()) / (highPoint.getX() - lowPoint.getX()));
                    }
                } else if (highPoint != null) {
                    calculatedYVals.add(highPoint.getY());
                }
            }

            // Use the set of Y values to finish executing the function
            double[] yVals = Utils.toPrimitiveArray(calculatedYVals);
            data.addDataPoint(new CompositePoint(currentValue, yVals));
        }

        data.addAxisMetric(new CompositePointXMetric());
        data.addAxisMetric(new AverageMetric());
        data.addAxisMetric(new VarianceMetric());
        data.addAxisMetric(new StandardDeviationMetric());

        return data;
    }

    /**
     * A helper method to find the bounds to use for interpolation
     * This finds the interval of x values that is accounted for by the superset of data sets
     * This assumes that the input has size >= 1, which the compute method should check for
     * @return An interval across which all x values on all data sets can be found by interpolation
     */
    private Pair<Double, Double> getInterpolationBounds(Iterable<GraphableData> input) {
        Iterator<GraphableData> iterator = input.iterator();
        Axis first = iterator.next().getXBounds();
        double min = first.getMin();
        double max = first.getMax();
        while(iterator.hasNext()) {
            Axis currentBounds = iterator.next().getXBounds();
            if(currentBounds.getMin() < min) {
                min = currentBounds.getMin();
            }
            if(currentBounds.getMax() > max) {
                max = currentBounds.getMax();
            }
        }
        return new Pair<Double, Double>(min, max);
    }

    /**
     * Function that determines the number of points used to compute the function
     * This default behavior can be overridden by subclasses
     * @return The number of points used to compute the function
     */
    protected int getNumComputationPoints() {
        return 100;
    }

    /**
     * A helper method used to populate the array of points at which the function will be computed
     * @param lowerBound The lower bound of the points at which the function will be computed
     * @param upperBound The upper bound of hte points at which the function will be computed
     * @param numPoints The number of points at which to compute the function
     * @return An array populated with x values
     */
    private double[] populateArray(double lowerBound, double upperBound, int numPoints) {
        double[] resultArray = new double[numPoints];
        double interval = (upperBound - lowerBound) / (numPoints - 1);
        double value = lowerBound;

        for(int i = 0; i < numPoints; i++) {
            resultArray[i] = value;
            value += interval;
        }

        return resultArray;
    }
}
