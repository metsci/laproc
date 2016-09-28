package com.metsci.laproc;

import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Curve;

import java.awt.*;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
    {
        final JFrame frame = new JFrame( "Glimpse Example" );
        frame.setSize( 800, 800 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

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

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);
        NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas( );
        canvas.addLayout(new ROCCurvePlot(graph).getLayout());
        new FPSAnimator( canvas.getGLDrawable( ), 120 ).start( );
        tabbedPane.add("ROCCurvePlot",canvas);
       
        JPanel dataPanel = DataSheetGUI.GetDataSheet(); 
        tabbedPane.addTab("Datasheet", dataPanel);
    }
}
