package com.metsci.laproc.plotting;

import com.metsci.laproc.GenericData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robinsat on 1/31/2017.
 */
public class GraphableDataTest {

    private BasicGraphableData data1;
    private BasicGraphableData data2;
    private BasicGraphableData data3;

    @Before
    public void setupMockData() {
        data1 = GenericData.getDataSet1();
        data2 = GenericData.getDataSet2();
        data3 = GenericData.getDataSet3();
    }

    @Test
    public void testGetLessOrEqual() {
        GraphPoint point1 = data1.getPointLessOrEqual(3);
        GraphPoint point2 = data1.getPointLessOrEqual(2.9);
        GraphPoint point3 = data1.getPointLessOrEqual(4);

        assertSame(point1, point3);
        assertEquals(3, point1.getX(), GenericData.TOLERANCE);
        assertEquals(2, point1.getY(), GenericData.TOLERANCE);
        assertNull(point2);

        GraphPoint point4 = data3.getPointLessOrEqual(10);
        assertEquals(9, point4.getX(), GenericData.TOLERANCE);
    }

    @Test
    public void testGetGreaterOrEqual() {
        GraphPoint point1 = data1.getPointGreaterOrEqual(40);
        GraphPoint point2 = data1.getPointGreaterOrEqual(43);
        GraphPoint point3 = data1.getPointGreaterOrEqual(45);

        assertNotNull(point1);
        assertNotNull(point2);
        assertSame(point1, point2);
        assertEquals(43, point1.getX(), GenericData.TOLERANCE);
        assertEquals(50, point1.getY(), GenericData.TOLERANCE);
        assertNull(point3);

        GraphPoint point4 = data3.getPointGreaterOrEqual(10);
        assertEquals(47, point4.getX(), GenericData.TOLERANCE);
    }
}
