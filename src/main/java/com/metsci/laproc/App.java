package com.metsci.laproc;


import com.metsci.laproc.display.BasicWindow;
import com.metsci.laproc.display.Window;
import com.metsci.laproc.plotting.Axis;
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
        BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
        ClassifierDataSet data = importData();
        GraphableFunction func = new ROCCurve(data);
        GraphableFunctionOutput output = func.compute();
        graph.addData( output.getGraphableData("False Positive Rate", "True Positive Rate"));
        
        Window window = new BasicWindow();
        window.showGraph(graph);
        window.showSpreadsheet(data);
        window.showClass(data);
        window.display();
    }

    private static ClassifierDataSet importData() {
        ClassifierDataSet data = new ClassifierDataSet();
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset1.csv");
            reader.getLine();
            String[] line;
            while(true) {
                line = reader.getLine();
                if(line == null)
                    break;
                if(line[3].equals("1"))
                    data.add(new DataPointImpl(true, Double.parseDouble(line[4])));
                else
                    data.add(new DataPointImpl(false, Double.parseDouble(line[4])));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }
}
