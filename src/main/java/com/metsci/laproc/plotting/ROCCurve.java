package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.pointmetrics.*;

import java.util.Collection;

/**
 * The function to compute a ROC curve
 * Created by robinsat on 9/20/2016.
 */
public class ROCCurve implements GraphableFunction {

    public static final String tpString = "True Positives";
    public static final String fpString = "False Positives";
    public static final String tnString = "True Negatives";
    public static final String fnString = "False Negatives";

    /** The default number of points */
    private static final int NUMPOINTS = 100;
    private GraphOptions options;

    /** A classified data set to be displayed as a ROCCurve */
    private ClassifierDataSet input;

    /**
     * Constructor
     * @param in A data set which has been classified
     */
    public ROCCurve(ClassifierDataSet in) {
        this.input = in;
    }

    /**
     * Compures the Receiver Operating Characteristic curve
     * @return The plottable data set representing this curve
     */
    public GraphableData compute() {
        GraphableDataWithMetrics<ClassifierSetPoint> out = new GraphableDataWithMetrics<ClassifierSetPoint>("ROC Curve",
                new FalsePositiveRate(), new TruePositiveRate());

        // Metrics that must be calculated for each point in the data set
        double interval = 1.0 / NUMPOINTS;
        double cutpoint = 0;

        //Iterate over all points in the set to compute the values for each point
        for(int i = 0; i < NUMPOINTS; i++) {
            cutpoint += interval;
            out.addDataPoint(createPointAtThreshold(input, cutpoint));
        }

        // Populate the list of metrics that can be used as axes
        out.addAxisMetric(new Precision());
        out.addAxisMetric(new TruePositives());
        out.addAxisMetric(new FalsePositives());
        out.addAxisMetric(new Threshold());

        // Add extra metrics for display, in the order that they will be displayed
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

    public Collection<ParametricFunction> getAllFunctions() {
        return null;
    }
    public void useFunctions(ParametricFunction xAxis, ParametricFunction yAxis) {

    }

}
