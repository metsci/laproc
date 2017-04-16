package com.metsci.laproc.utils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests Utils
 * Created by malinocr on 2/7/2017.
 */
public class UtilsTest {
    private final double doubleFuzzValue = 0.00001;

    @Test
    public void TestToPrimitiveArrayNull(){
        try{
            Utils.toPrimitiveArray(null);
        } catch (IllegalArgumentException e){
            return;
        }
        fail();
    }

    @Test
    public void TestToPrimitiveArrayEmpty(){
        ArrayList<Double> list = new ArrayList<Double>();
        double[] array = Utils.toPrimitiveArray(list);
        assertEquals(0,array.length);
    }

    @Test
    public void TestToPrimitiveArraySingleElement(){
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(1d);
        double[] array = Utils.toPrimitiveArray(list);
        assertEquals(1,array.length);
        assertTrue(1 - array[0] < 0.0001);
    }

    @Test
    public void TestToPrimitiveArrayMultipleElement(){
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(1d);
        list.add(2d);
        double[] array = Utils.toPrimitiveArray(list);
        assertEquals(2,array.length);
        assertTrue(1 - array[0] < 0.0001);
        assertTrue(2 - array[1] < 0.0001);
    }
}
