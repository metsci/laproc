package com.metsci.laproc.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests DataPointImpl
 * Created by malinocr on 1/21/2017.
 */
public class DataPointImplTest {
    @Test
    public void TestDataPoint(){
        double epsilon = 0.001;

        boolean bool = true;
        boolean bool1 = false;
        double val = 0;
        double val1 = 1;
        DataPointImpl dataPoint = new DataPointImpl(bool);
        assertEquals(bool,dataPoint.getTruth());
        assertEquals(0,dataPoint.getValues().length);

        DataPointImpl dataPoint1 = new DataPointImpl(bool1,val);
        assertEquals(bool1,dataPoint1.getTruth());
        assertEquals(1,dataPoint1.getValues().length);
        assertEquals(val,dataPoint1.getValues()[0],epsilon);

        DataPointImpl dataPoint2 = new DataPointImpl(bool1,val,val1);
        assertEquals(bool1,dataPoint2.getTruth());
        assertEquals(2,dataPoint2.getValues().length);
        assertEquals(val,dataPoint2.getValues()[0],epsilon);
        assertEquals(val1,dataPoint2.getValues()[1],epsilon);
    }
}
