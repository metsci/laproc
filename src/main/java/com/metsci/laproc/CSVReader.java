package com.metsci.laproc;

import java.io.*;

/**
 * A reader for CSV files
 * Created by malinocr on 9/25/2016.
 */
public class CSVReader {
    private BufferedReader reader;

    /**
     * Construtor for the CSV Reader
     * @param filePath file of the CSV
     * @throws IOException
     */
    public CSVReader(String filePath) throws IOException{
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    /**
     * Gets a row of the CSV
     * @return A string array where the collumn index of a cell matches the index in the array
     * @throws IOException
     */
    public String[] getLine() throws IOException{
        String line = reader.readLine();
        if(line != null)
            return line.split(",");
        return null;
    }
}
