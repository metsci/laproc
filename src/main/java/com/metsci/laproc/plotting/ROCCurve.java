package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;

/**
 * The function to compute a ROC curve
 * Created by robinsat on 9/20/2016.
 */
public class ROCCurve implements GraphableFunction {

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
        GraphableData d = new Curve("ROC");
        d.addPoint(0.1, 0.4);
        d.addPoint(0.3, 0.4);
        d.addPoint(0.5, 0.5);
        d.addPoint(0.7, 0.8);
        return d;
    }
}
