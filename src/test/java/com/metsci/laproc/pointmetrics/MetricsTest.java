package com.metsci.laproc.pointmetrics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the true positive rate, false positive rate,
 * true negative rate, and false negative rate calculators
 * Created by robinsat on 10/29/2016.
 */
public class MetricsTest {

    private static final double PRECISION = 0.00001;
    private ClassifierSetPoint point1;

    @Before
    public void setup() {
        point1 = new ClassifierSetPoint(0.5, 20, 1820, 180, 10); // Data taken from sensitivity/specificity wikipedia
    }

    @Test
    public void testTruePositiveRate() {
        TruePositiveRate r = new TruePositiveRate();
        assertEquals(0.666666666667, r.compute(point1), PRECISION);
    }

    @Test
    public void testFalsePositiveRate() {
        FalsePositiveRate r = new FalsePositiveRate();
        assertEquals(0.09, r.compute(point1), PRECISION);
    }

    @Test
    public void testTrueNegativeRate() {
        TrueNegativeRate r = new TrueNegativeRate();
        assertEquals(0.91, r.compute(point1), PRECISION);
    }

    @Test
    public void testFalseNegativeRate() {
        FalseNegativeRate r = new FalseNegativeRate();
        assertEquals(0.333333333333, r.compute(point1), PRECISION);
    }

    @Test
    public void testPrecision() {
        Precision p = new Precision();
        assertEquals(0.1, p.compute(point1), PRECISION);
    }

    @Test
    public void testAccuracy() {
        Accuracy a = new Accuracy();
        assertEquals(0.9064, a.compute(point1), PRECISION);
    }

    @Test
    public void testF1Score() {
        F1Score f = new F1Score();
        assertEquals(0.173913, f.compute(point1), PRECISION);
    }

}
