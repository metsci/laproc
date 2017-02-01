package com.metsci.laproc;

import com.metsci.laproc.plotting.BasicGraphableData;

/**
 * Some data sets for use by test classes
 * Created by robinsat on 1/31/2017.
 */
public class GenericData {

    public static final double TOLERANCE = 0.0000001;

    public static BasicGraphableData getDataSet1() {
        BasicGraphableData data1 = new BasicGraphableData("");
        data1.addPoint(20, 10);
        data1.addPoint(43, 50);
        data1.addPoint(3, 2);
        return data1;
    }

    public static BasicGraphableData getDataSet2() {
        BasicGraphableData data2 = new BasicGraphableData("");
        data2.addPoint(2, 2);
        data2.addPoint(3, 3);
        data2.addPoint(4, 4);
        data2.addPoint(5, 5);
        data2.addPoint(6, 6);
        data2.addPoint(7, 7);
        data2.addPoint(8, 8);
        data2.addPoint(9, 9);
        data2.addPoint(10, 10);
        data2.addPoint(11, 11);
        data2.addPoint(12, 12);
        data2.addPoint(1, 1);
        return data2;
    }

    public static BasicGraphableData getDataSet3() {
        BasicGraphableData data3 = new BasicGraphableData("");
        data3.addPoint(5, 10);
        data3.addPoint(9, 10);
        data3.addPoint(9, 5);
        data3.addPoint(2, 7);
        data3.addPoint(53, 30);
        data3.addPoint(47, 10);
        return data3;
    }
}
