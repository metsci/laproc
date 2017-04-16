package com.metsci.laproc.plotting;

import com.metsci.laproc.GenericData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests BasicAxis
 * Created by malinocr on 2/11/2017.
 */
public class BasicAxisTest {
    @Test
    public void TestMinMax(){
        double min = 0;
        double max = 5;
        BasicAxis axis = new BasicAxis(min,max,"","");

        assertTrue(min - axis.getMin() < GenericData.TOLERANCE);
        assertTrue(max - axis.getMax() < GenericData.TOLERANCE);

        min = 1;
        max = 3;
        axis.setBounds(min,max);

        assertTrue(min - axis.getMin() < GenericData.TOLERANCE);
        assertTrue(max - axis.getMax() < GenericData.TOLERANCE);

        min = 2;
        max = 2;
        axis.setBounds(min,max);

        assertTrue(min - axis.getMin() < GenericData.TOLERANCE);
        assertTrue(max - axis.getMax() < GenericData.TOLERANCE);

        min = 3;
        max = 1;

        try {
            axis.setBounds(min, max);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    public void TestName(){
        String name = "Name1";
        BasicAxis axis = new BasicAxis(0,0,name,"");

        assertEquals(name,axis.getName());

        name = "Name2";
        axis.setName(name);

        assertEquals(name,axis.getName());
    }

    @Test
    public void TestUnit(){
        String unit = "Unit1";
        BasicAxis axis = new BasicAxis(0,0,"",unit);

        assertEquals(unit,axis.getUnit());

        unit = "Unit2";
        axis.setUnit(unit);

        assertEquals(unit,axis.getUnit());
    }
}
