package com.metsci.laproc;

import com.metsci.laproc.application.Application;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPointImpl;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
    {
        ClassifierDataSet importedData = importData();

        Application application = new Application(importedData);
        application.run();

    }

    private static ClassifierDataSet importData() {
        ClassifierDataSet data = new ClassifierDataSet();
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset1.csv");
            String[] line = reader.getLine();
            TagHeader tag1 = new TagHeader(line[0]);
            TagHeader tag2 = new TagHeader(line[1]);
            TagHeader tag3 = new TagHeader(line[2]);

            while(true) {
                line = reader.getLine();
                if(line == null)
                    break;
                DataPoint point;
                if(line[3].equals("1"))
                    point = new DataPointImpl(true, Double.parseDouble(line[4]));
                else
                    point = new DataPointImpl(false, Double.parseDouble(line[4]));
                point.addTag(line[0]);
                point.addTag(line[1]);
                point.addTag(line[2]);

                tag1.addTag(line[0]);
                tag2.addTag(line[1]);
                tag3.addTag(line[2]);
                data.add(point);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }
}
