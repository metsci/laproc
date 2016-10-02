package com.metsci.laproc;

import com.metsci.laproc.display.BasicWindow;
import com.metsci.laproc.display.Window;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.plotting.*;

import javax.swing.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("beginning");
        final JFrame frame = new JFrame( "Glimpse Example" );
        frame.setSize( 800, 800 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
        GraphableFunction func = new ROCCurve(importData());
        System.out.println("a");
        GraphableFunctionOutput output = func.compute();
        System.out.println("b");
        graph.addData( output.getGraphableData("True Positive Rate", "False Positive Rate"));
        System.out.println("c");

        Window window = new BasicWindow();
        window.showGraph(graph);
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
