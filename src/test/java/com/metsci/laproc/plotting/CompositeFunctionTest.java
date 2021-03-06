package com.metsci.laproc.plotting;

import com.metsci.glimpse.util.Pair;
import com.metsci.laproc.GenericData;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests The CompositeFunction class and its subclasses
 * Created by robinsat on 1/31/2017.
 */
public class CompositeFunctionTest {

    private List<GraphableData> set1;
    private List<GraphableData> set2;
    private List<GraphableData> set3;

    private AverageMetric average;

    @Before
    public void setupMockData() {
        average = new AverageMetric();

        set1 = new ArrayList<GraphableData>();
        set1.add(GenericData.getDataSet1());
        set1.add(GenericData.getDataSet2());
        set1.add(GenericData.getDataSet3());

        set2 = new ArrayList<GraphableData>();
        set2.add(GenericData.getDataSet2());

        set3 = new ArrayList<GraphableData>();
        set3.add(GenericData.getDataSet1());
        set3.add(GenericData.getDataSet3());
    }

    @Test
    public void testAverage1() throws Exception {
        double[] vals = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        CompositePoint point = new CompositePoint(0, vals);
        assertEquals(5, average.compute(point), GenericData.TOLERANCE);
    }

    @Test
    public void testAverage2() throws Exception {
        double[] vals = {55, 43, 172, 68, 263, 99.9};
        CompositePoint point = new CompositePoint(0, vals);
        assertEquals(116.816666667, average.compute(point), GenericData.TOLERANCE);
    }

    @Test
    public void testInterpolation1() throws Exception {
        Pair<Double, Double> bounds = invokeInterpolationBounds(set1);
        assertEquals(1, bounds.first(), GenericData.TOLERANCE);
        assertEquals(53, bounds.second(), GenericData.TOLERANCE);
    }

    /**
     * Helper method that uses reflection to invoke a private method in the CompositeFunction class
     * @param data The method input
     * @return The resulting Pair
     * @throws Exception So many exceptions are possible because of reflection
     */
    private Pair<Double, Double> invokeInterpolationBounds(List<GraphableData> data) throws Exception{
        CompositeFunction func = new CompositeFunction();
        Method method = CompositeFunction.class.getDeclaredMethod("getInterpolationBounds",Iterable.class);
        method.setAccessible(true);
        return (Pair<Double, Double>) method.invoke(func, data);
    }

    /*
     * Helper method that uses reflection to invoke a private method in the ROCCurveFunction class
     * @param values The input
     * @return The result
     * @throws Exception So many exceptions are possible because of reflection
     */
    /*private double invokeAverageMethod(double[] values) throws Exception{
        CompositeFunction func = new CompositeFunction();
        Method method = CompositeFunction.class.getDeclaredMethod("calculateAverage",double[].class);
        method.setAccessible(true);
        return (Double) method.invoke(func, values);
    }*/

    private double[] invokePopulateArrayMethod(double[] values) throws Exception{
        CompositeFunction func = new CompositeFunction();
        Method method = CompositeFunction.class.getDeclaredMethod("populateArray", double.class, double.class, int.class);
        method.setAccessible(true);
        return (double[]) method.invoke(func, values);
    }

    /*private class InterpolationTrackerUtility extends CompositeFunction {

        private List<Double[]> results;

        public InterpolationTrackerUtility() {
            results = new ArrayList<Double[]>();
        }

        /**
         * Overrides the behavior of this method to keep a record for testing purposes
         * @param yValues The y values for several data sets at a given x
         * @return The function output

        protected double computeFromYValues(double[] yValues) {
            Double[] vals = new Double[yValues.length];
            for(int i = 0; i < yValues.length; i++) {
                vals[i] = yValues[i];
            }
            results.add(vals);
            return 0;
        }

        /**
         * Getter for the results
         * @return The results

        public List<Double[]> getResults() {
            return this.results;
        }

        @Override
        protected int getNumComputationPoints() {
            return 5;
        }
    }*/
}
