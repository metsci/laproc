package com.metsci.laproc.utils;

import java.util.List;

/**
 * A generic Utils class for static utility functions
 * Created by robinsat on 1/31/2017.
 */
public class Utils {

    /**
     * Copies the contents of a list to an array of primitive doubles
     * @param list The input List
     * @return The output array with identical contents to the input list
     */
    public static double[] toPrimitiveArray(List<Double> list) {
        if(list == null){
            throw new IllegalArgumentException("Null list cannot be converted to an array");
        }
        double[] array = new double[list.size()];
        int index = 0;
        for(Double d : list) {
            array[index] = d;
            index++;
        }
        return array;
    }
}
