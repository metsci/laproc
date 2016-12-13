package com.metsci.laproc.plotting;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.pointmetrics.ClassifierSetPoint;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Tests the output of the ROC Curve function
 * Created by robinsat on 10/28/2016.
 */
public class ROCCurveTest {

    ClassifierDataSet data1;
    ClassifierDataSet data2;

    @Before
    public void setupMockData() {
        data1 = new ClassifierDataSet();
        data2 = new ClassifierDataSet();

        data1.add(new DataPointImpl(false, 0.1));
        data1.add(new DataPointImpl(false, 0.2));
        data1.add(new DataPointImpl(false, 0.3));
        data1.add(new DataPointImpl(false, 0.4));
        data1.add(new DataPointImpl(false, 0.5));
        data1.add(new DataPointImpl(true, 0.6));
        data1.add(new DataPointImpl(true, 0.7));
        data1.add(new DataPointImpl(true, 0.8));
        data1.add(new DataPointImpl(true, 0.9));

        data2.add(new DataPointImpl(false, 0.05));
        data2.add(new DataPointImpl(false, 0.14));
        data2.add(new DataPointImpl(false, 0.20));
        data2.add(new DataPointImpl(false, 0.34));
        data2.add(new DataPointImpl(false, 0.17));
        data2.add(new DataPointImpl(false, 0.68));
        data2.add(new DataPointImpl(false, 0.73));
        data2.add(new DataPointImpl(false, 0.53));
        data2.add(new DataPointImpl(false, 0.42));
        data2.add(new DataPointImpl(false, 0.31));
        data2.add(new DataPointImpl(true, 0.64));
        data2.add(new DataPointImpl(true, 0.44));
        data2.add(new DataPointImpl(true, 0.89));
        data2.add(new DataPointImpl(true, 0.93));
        data2.add(new DataPointImpl(true, 0.77));
        data2.add(new DataPointImpl(true, 0.32));
        data2.add(new DataPointImpl(true, 0.81));
        data2.add(new DataPointImpl(true, 0.62));
        data2.add(new DataPointImpl(true, 0.50));
        data2.add(new DataPointImpl(true, 0.61));
    }

    @Test
    public void testROCatSingleThreshold1() throws Exception {
        ClassifierSetPoint result = invokeCreatePointMethod(data1, 0.5);
        assertEquals(4, result.getNumPositives());
        assertEquals(5, result.getNumNegatives());
        assertEquals(4, result.getTruePositives());
        assertEquals(1, result.getFalsePositives());
        assertEquals(4, result.getTrueNegatives());
        assertEquals(0, result.getFalseNegatives());

        result = invokeCreatePointMethod(data1, 0.7);
        assertEquals(4, result.getNumPositives());
        assertEquals(5, result.getNumNegatives());
        assertEquals(3, result.getTruePositives());
        assertEquals(0, result.getFalsePositives());
        assertEquals(5, result.getTrueNegatives());
        assertEquals(1, result.getFalseNegatives());
    }

    @Test
    public void testROCatSingleThreshold2() throws Exception {
        ClassifierSetPoint result = invokeCreatePointMethod(data2, 0.5);
        assertEquals(10, result.getNumPositives());
        assertEquals(10, result.getNumNegatives());
        assertEquals(8, result.getTruePositives());
        assertEquals(3, result.getFalsePositives());
        assertEquals(7, result.getTrueNegatives());
        assertEquals(2, result.getFalseNegatives());
    }

    /**
     * Helper method that uses reflection to invoke a private method in the ROCCurveFunction class
     * @param in The ClassifierDataSet to use as input
     * @param threshold The threshold to use for the calculations
     * @return The resulting ClassifierSetPoint
     * @throws Exception So many exceptions are possible because of reflection
     */
    private ClassifierSetPoint invokeCreatePointMethod(ClassifierDataSet in, double threshold) throws Exception{
        ROCCurveFunction func = new ROCCurveFunction(in);
        Method method = ROCCurveFunction.class.getDeclaredMethod("createPointAtThreshold",ClassifierDataSet.class,Double.TYPE);
        method.setAccessible(true);
        return (ClassifierSetPoint) method.invoke(func, in, threshold);
    }

}
