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

    @Before
    public void setupMockData() {
        set1 = new ArrayList<GraphableData>();
        set1.add(GenericData.getDataSet1());
        set1.add(GenericData.getDataSet2());
        set1.add(GenericData.getDataSet3());
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
        Pair<GraphableData, GraphableData> bounds = invokeInterpolationBounds(set1);
        assertEquals(1, bounds.first().getXBounds().getMin(), GenericData.TOLERANCE);
        assertEquals(53, bounds.second().getXBounds().getMax(), GenericData.TOLERANCE);
    }

    @Test
    public void testCompute() {
        InterpolationTrackerUtility func = new InterpolationTrackerUtility();
       // func.compute(set1);

    }

    /**
     * Helper method that uses reflection to invoke a private method in the CompositeFunction class
     * @param data The method input
     * @return The resulting Pair
     * @throws Exception So many exceptions are possible because of reflection
     */
    private Pair<GraphableData, GraphableData> invokeInterpolationBounds(List<GraphableData> data) throws Exception{
        CompositeFunction func = new VerticalAverageFunction(); //Arbitrarily selected concrete class
        Method method = CompositeFunction.class.getDeclaredMethod("getInterpolationBounds",List.class);
        method.setAccessible(true);
        return (Pair<GraphableData, GraphableData>) method.invoke(func, data);
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
    }
}
