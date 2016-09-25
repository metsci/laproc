package com.metsci.laproc;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Reads a CSV file into a format usable by the laproc library
 * Created by malinocr on 9/25/2016.
 */
public class CSVReader {
    InputStream in;
    String filePath;
    ArrayList<String> instanceString;
    ArrayList<Instance> instances;

    public Instance buildInstance(InputStream is, String filePath){
        return null;
    }

}
