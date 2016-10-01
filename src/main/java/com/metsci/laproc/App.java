package com.metsci.laproc;

import com.metsci.laproc.display.BasicWindow;
import com.metsci.laproc.display.Window;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
        GraphableFunction func = new ROCCurve(new ClassifierDataSet());
        graph.addData( func.compute());

        Window window = new BasicWindow();
        window.showGraph(graph);
        window.display();
    }
}
