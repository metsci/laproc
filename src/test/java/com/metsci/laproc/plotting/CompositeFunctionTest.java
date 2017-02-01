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

    @Before
    public void setupMockData() {
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
        assertEquals(5, invokeAverageMethod(vals), GenericData.TOLERANCE);
    }

    @Test
    public void testAverage2() throws Exception {
        double[] vals = {55, 43, 172, 68, 263, 99.9};
        assertEquals(116.816666667, invokeAverageMethod(vals), GenericData.TOLERANCE);
    }

    @Test
    public void testInterpolation1() throws Exception {
        Pair<Double, Double> bounds = invokeInterpolationBounds(set1);
        assertEquals(1, bounds.first(), GenericData.TOLERANCE);
        assertEquals(53, bounds.second(), GenericData.TOLERANCE);
    }

    @Test
    public void testCompute1() {
        InterpolationTrackerUtility func = new InterpolationTrackerUtility();
        func.compute(set2);
        List<Double[]> results = func.getResults();
        assertEquals(5, results.size());
        assertEquals(1, results.get(0)[0], GenericData.TOLERANCE);
        assertEquals(3.75, results.get(1)[0], GenericData.TOLERANCE);
        assertEquals(6.5, results.get(2)[0], GenericData.TOLERANCE);
        assertEquals(9.25, results.get(3)[0], GenericData.TOLERANCE);
        assertEquals(12, results.get(4)[0], GenericData.TOLERANCE);
    }

    @Test
    public void testCompute2() {
        InterpolationTrackerUtility func = new InterpolationTrackerUtility();
        func.compute(set3);
        List<Double[]> results = func.getResults();

        /*  Expected Results:
            X Values: 2, 14.75, 27.5, 40.25, 53
            Y Values (set 1): None, 7.52941176471, 23.0434782609, 45.2173913043 None
         */
        assertEquals(5, results.size());
        assertEquals(1, results.get(0).length);
        assertEquals(7, results.get(0)[0], GenericData.TOLERANCE);
        assertEquals(2, results.get(1).length);
        assertEquals(7.52941176471, results.get(1)[0], GenericData.TOLERANCE);
        assertEquals(2, results.get(2).length);
        assertEquals(23.0434782609, results.get(2)[0], GenericData.TOLERANCE);
        assertEquals(2, results.get(3).length);
        assertEquals(45.2173913043, results.get(3)[0], GenericData.TOLERANCE);
        assertEquals(1, results.get(4).length);
        assertEquals(30, results.get(4)[0], GenericData.TOLERANCE);


    }

    /**
     * Helper method that uses reflection to invoke a private method in the CompositeFunction class
     * @param data The method input
     * @return The resulting Pair
     * @throws Exception So many exceptions are possible because of reflection
     */
    private Pair<Double, Double> invokeInterpolationBounds(List<GraphableData> data) throws Exception{
        CompositeFunction func = new VerticalAverageFunction(); //Arbitrarily selected concrete class
        Method method = CompositeFunction.class.getDeclaredMethod("getInterpolationBounds",List.class);
        method.setAccessible(true);
        return (Pair<Double, Double>) method.invoke(func, data);
    }

    /**
     * Helper method that uses reflection to invoke a private method in the ROCCurveFunction class
     * @param values The input
     * @return The result
     * @throws Exception So many exceptions are possible because of reflection
     */
    private double invokeAverageMethod(double[] values) throws Exception{
        CompositeFunction func = new VerticalAverageFunction(); //Arbitrarily selected concrete class
        Method method = CompositeFunction.class.getDeclaredMethod("calculateAverage",double[].class);
        method.setAccessible(true);
        return (Double) method.invoke(func, values);
    }

    private class InterpolationTrackerUtility extends CompositeFunction {

        private List<Double[]> results;

        public InterpolationTrackerUtility() {
            results = new ArrayList<Double[]>();
        }

        /**
         * Overrides the behavior of this method to keep a record for testing purposes
         * @param yValues The y values for several data sets at a given x
         * @return The function output
         */
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
         */
        public List<Double[]> getResults() {
            return this.results;
        }

        @Override
        protected int getNumComputationPoints() {
            return 5;
        }
    }
}
