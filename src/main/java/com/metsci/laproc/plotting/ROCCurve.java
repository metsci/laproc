package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

/**
 * The function to compute a ROC curve
 * Created by robinsat on 9/20/2016.
 */
public class ROCCurve implements GraphableFunction {

    /** A classified data set to be displayed as a ROCCurve */
    private ClassifierDataSet input;


    private static final int NUMPOINTS = 100;

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
        GraphableData d = new Curve("ROC", NUMPOINTS);

        // Calculate the number of positive values and negative values in this data set
        int numPositives = 0;
        int numNegatives = 0;
        for(DataPoint p : input) {
            if(p.getTruth())
                numPositives++;
            else
                numNegatives++;
        }

        double interval = 1.0 / NUMPOINTS;
        double cutpoint = 0;
        int truePositives;
        int trueNegatives;
        int falsePositives;
        int falseNegatives;

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

            double tpr = 1.0 * truePositives;
            tpr /= numPositives;
            double fpr = 1.0 * falsePositives;
            fpr /= numNegatives;

            d.addPoint(fpr, tpr);
        }
        System.out.println("SIZE: " + d.getSize());
        return d;
    }
}
