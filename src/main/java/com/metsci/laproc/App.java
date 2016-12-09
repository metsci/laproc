package com.metsci.laproc;


import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.display.BasicWindow;
import com.metsci.laproc.display.Window;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.plotting.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
    {
        BasicGraph graph = new BasicGraph(new BasicAxis(0, 1, "X Axis"), new BasicAxis(0, 1, "Y Axis"));
        ClassifierDataSet importData = importData();
        GraphableFunction func = new ROCCurve(importData);
        GraphableData graphableData = func.compute();
        graphableData.setName("Initial Classifier Data Set");
        graph.addData( graphableData);

        
        Window window = new BasicWindow();
        window.showGraphOptions(graphableData);
        window.showGraph(graph);
        window.showSpreadsheet(importData);
        window.showClass(graphableData);
        window.display();
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
