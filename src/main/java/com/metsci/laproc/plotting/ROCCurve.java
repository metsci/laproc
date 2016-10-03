package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

/**
 * The function to compute a ROC curve
 * Created by robinsat on 9/20/2016.
 */
public class ROCCurve implements GraphableFunction {

    /** The default number of points */
    private static final int NUMPOINTS = 100;

    // String constants
    private static final String scoreString = "Classifier Score";
    private static final String tprString = "True Positive Rate";
    private static final String fprString = "False Positive Rate";
    private static final String tnrString = "True Negative Rate";
    private static final String fnrString = "False Negative Rate";
    private static final String cutpointString = "Cutpoint";
    private static final String accuracyString = "Accuracy";


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
    public GraphableFunctionOutput compute() {
        GraphableFunctionOutput out = new GraphableFunctionOutputImpl();

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

            // Calculate the rates at which the classifer is accurate
            double truePositiveRate = ((double) truePositives) / numPositives;
            double falsePositiveRate = ((double) falsePositives) / numNegatives;
            double trueNegativeRate = ((double) trueNegatives) / numNegatives;
            double falseNegativeRate = ((double) falseNegatives) / numPositives;
            double accuracy = ((double) (truePositives + trueNegatives)) / ((double) (numPositives + numNegatives));

            // Construct a point with all of the data and add it to the output set
            GraphPoint point = new GraphPoint();
            point.put(cutpointString, cutpoint);
            point.put(tprString, truePositiveRate);
            point.put(fprString, falsePositiveRate);
            point.put(tnrString, trueNegativeRate);
            point.put(fnrString, falseNegativeRate);
            point.put(accuracyString, accuracy);
            out.add(point);
        }
        return out;
    }
}
