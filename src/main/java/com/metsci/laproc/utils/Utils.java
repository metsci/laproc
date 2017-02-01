package com.metsci.laproc.utils;

import java.util.List;

/**
 * A generic Utils class for static utility functions
 * Created by robinsat on 1/31/2017.
 */
public class Utils {

    public static double[] toPrimitiveArray(List<Double> list) {
        double[] array = new double[list.size()];
        int index = 0;
        for(Double d : list) {
            array[index] = d;
            index++;
        }
        return array;
    }
}
