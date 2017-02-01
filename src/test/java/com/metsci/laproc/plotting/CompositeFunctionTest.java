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
}
