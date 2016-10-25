package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;

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
    private static final String truePositiveString = "True Positives";
    private static final String falsePositiveString = "False Positives";
    private static final String trueNegativeString = "True Negatives";
    private static final String falseNegativeString = "False Negatives";

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
       /* GraphableDataWithStats out = new GraphableDataWithStats();

        // Add the attributes to the list
      /*  out.addAttribute(tprString);
        out.addAttribute(fprString);
        out.addAttribute(tnrString);
        out.addAttribute(fnrString);
        out.addAttribute(cutpointString);
        out.addAttribute(accuracyString); */

        // Calculate the number of positive values and negative values in this data set
       /* int numPositives = 0;
        int numNegatives = 0;
        for(com.metsci.laproc.data.DataPoint p : input) {
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
            for (com.metsci.laproc.data.DataPoint p : input) {
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
            GraphPoint point = new GraphPoint(falsePositiveRate, truePositiveRate);
            point.put(cutpointString, cutpoint);
            point.put(truePositiveString, (double) truePositives);
            point.put(falsePositiveString, (double) falsePositives);
            point.put(trueNegativeString, (double) trueNegatives);
            point.put(falseNegativeString, (double) falseNegatives);
            point.put(tprString, truePositiveRate);
            point.put(fprString, falsePositiveRate);
            point.put(tnrString, trueNegativeRate);
            point.put(fnrString, falseNegativeRate);
            point.put(accuracyString, accuracy);
            out.addPoint(point);
        }
        return out;*/
        return null;
    }
}
