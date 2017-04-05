package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.pointmetrics.*;

/**
 * The function to compute a ROC curve
 * Created by robinsat on 9/20/2016.
 */
public class ROCCurveFunction implements GraphableFunction<ClassifierDataSet> {

    /** The default number of points */
    private static final int NUMPOINTS = 100;

    /**
     * Computes the Receiver Operating Characteristic curve
     * @return The plottable data set representing this curve
     */
    public GraphableData compute(ClassifierDataSet input) {
        GraphableDataWithMetrics<ClassifierSetPoint> out = new GraphableDataWithMetrics<ClassifierSetPoint>("ROC Curve",
                new FalsePositiveRate(), new TruePositiveRate());

        double max = 1.0;
        double min = 0.0;

        // Metrics that must be calculated for each point in the data set
        double interval = (max - min) / NUMPOINTS;
        double cutpoint = min;

        //Iterate over all points in the set to compute the values for each point
        for(int i = 0; i < NUMPOINTS; i++) {
            cutpoint += interval;
            if (cutpoint > max)
                break;
            out.addDataPoint(createPointAtThreshold(input, cutpoint));
        }

        // Populate the list of metrics that can be used as axes
        out.addAxisMetric(new Precision());
        out.addAxisMetric(new TruePositives());
        out.addAxisMetric(new FalsePositives());
        out.addAxisMetric(new Threshold());

        // Add extra metrics for uicomponents, in the order that they will be displayed
        out.addStatisticMetric(new Threshold());
        out.addStatisticMetric(new TruePositives());
        out.addStatisticMetric(new FalsePositives());
        out.addStatisticMetric(new TrueNegatives());
        out.addStatisticMetric(new FalseNegatives());
        out.addStatisticMetric(new TruePositiveRate());
        out.addStatisticMetric(new FalsePositiveRate());
        out.addStatisticMetric(new TrueNegativeRate());
        out.addStatisticMetric(new FalseNegativeRate());
        out.addStatisticMetric(new Precision());
        out.addStatisticMetric(new Accuracy());
        out.addStatisticMetric(new F1Score());

        return out;
    }

    /**
     * Helper function to create a classifier set point at a given threshold using the given data
     * @param in The data to use for this calculation
     * @param threshold The threshold to use for this calculation
     * @return A classifier set point storing the threshold, TP, FP, TN, and FN
     */
    private ClassifierSetPoint createPointAtThreshold(ClassifierDataSet in, double threshold) {
        int truePositives = 0;
        int trueNegatives = 0;
        int falsePositives = 0;
        int falseNegatives = 0;

        // Iterate over all points in the input set
        for (DataPoint p : in) {
            double val = p.getValues()[0];
            if(p.getTruth()) { // p is actually true
                if(val >= threshold)
                    truePositives++;
                else
                    falseNegatives++;
            } else { // p is actually false
                if( val >= threshold)
                    falsePositives++;
                else
                    trueNegatives++;
            }
        }

        // Create a point with the resulting values
        return new ClassifierSetPoint(threshold, truePositives, trueNegatives, falsePositives, falseNegatives);
    }
}
