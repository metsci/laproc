package com.metsci.laproc;
import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.display.BasicWindow;
import com.metsci.laproc.display.Window;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Curve;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Axis xAxis = new Axis();
        xAxis.setName("x axis");
        xAxis.setBounds(0,1);

        Axis yAxis = new Axis();
        yAxis.setName("y axis");
        yAxis.setBounds(0,1);

        BasicGraph graph = new BasicGraph();
        graph.setXAxis(xAxis);
        graph.setYAxis(yAxis);
        graph.addData( new Curve("Curve 1"));

        Window window = new BasicWindow();
        window.showGraph(graph);
        window.display();
    }
}
