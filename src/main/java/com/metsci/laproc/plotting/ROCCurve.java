package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.pointmetrics.*;

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
        GraphableDataWithMetrics out = new GraphableDataWithMetrics("ROC Curve");

        // Calculate the number of positive values and negative values in this data set
        int numPositives = 0;
        int numNegatives = 0;
        for(DataPoint p : input) {
            if(p.getTruth())
                numPositives++;
            else
                numNegatives++;
        }

        // Metrics that must be calculated for each point in the data set
        double interval = 1.0 / NUMPOINTS;
        double cutpoint = 0;
        int truePositives;
        int trueNegatives;
        int falsePositives;
        int falseNegatives;

        //Iterate over all points in the set to compute the values for each point
        for(int i = 0; i < NUMPOINTS; i++) {
            truePositives = 0;
            trueNegatives = 0;
            falsePositives = 0;
            falseNegatives = 0;

            cutpoint += interval;
            for (DataPoint p : input) {
                double val = p.getValues()[0];
                if(p.getTruth()) { // p is actually true
                    if(val >= cutpoint)
                        truePositives++;
                    else
                        falseNegatives++;
                } else { // p is actually false
                    if( val >= cutpoint)
                        falsePositives++;
                    else
                        trueNegatives++;
                }
            }
            out.addClassifierSetPoint(new ClassifierSetPoint(cutpoint, truePositives, trueNegatives, falsePositives, falseNegatives));

        }

        // Add extra metrics for display, in the order that they will be displayed
        out.addMetric(new TrueNegativeRate());
        out.addMetric(new FalseNegativeRate());
        out.addMetric(new Precision());
        out.addMetric(new Accuracy());
        out.addMetric(new F1Score());
        out.addMetric(new TruePositives());
        out.addMetric(new FalsePositives());
        out.addMetric(new TrueNegatives());
        out.addMetric(new FalseNegatives());
        out.addMetric(new Threshold());

        return out;
    }
}
