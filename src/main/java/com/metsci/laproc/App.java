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
       /* NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas( );
        canvas.addLayout(new ROCCurvePlot(graph).getLayout());
        new FPSAnimator( canvas.getGLDrawable( ), 120 ).start( );
        tabbedPane.add("ROCCurvePlot",canvas);*/
        
        JPanel borderPanel = new JPanel();
        GridBagLayout ogbl = new GridBagLayout();
        borderPanel.setLayout(ogbl);
        JPanel dataPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.maximumLayoutSize(frame);
        
        dataPanel.setLayout(gbl);
        for(int i = 0; i < 5; i++){
        	JLabel tempLabel = new JLabel("Header " + Integer.toString(i));
        	tempLabel.setBorder(BorderFactory.createCompoundBorder(
        			BorderFactory.createLineBorder(Color.BLACK,1,false), 
        			BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        	GridBagConstraints c = new GridBagConstraints();
        	c.fill = GridBagConstraints.HORIZONTAL;
        	c.gridx = i;
        	c.gridy = 0;
        	dataPanel.add(tempLabel, c);
        }
        for(int i = 1; i < 7; i++){
            for(int j = 0; j < 5; j++){
            	JLabel tempLabel = new JLabel("Entry " + Integer.toString(i) + " value " + Integer.toString(j));
            	tempLabel.setBorder(BorderFactory.createCompoundBorder(
            			BorderFactory.createLineBorder(Color.BLACK,1,false), 
            			BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            	GridBagConstraints c = new GridBagConstraints();
            	c.fill = GridBagConstraints.HORIZONTAL;
            	c.gridx = j;
            	c.gridy = i;
            	dataPanel.add(tempLabel, c);
            }
        }
        GridBagConstraints c = new GridBagConstraints();
    	//c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 5;
    	c.gridy = 0;
    	c.gridwidth = 5;
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,false));
        borderPanel.add(dataPanel, c);
        GridBagConstraints c2 = new GridBagConstraints();
    	//c2.fill = GridBagConstraints.HORIZONTAL;
    	c2.gridx = 0;
    	c2.gridy = 0;
    	c2.gridwidth = 5;
    	JPanel leftPanel = new JPanel();
    	leftPanel.setLayout(new BorderLayout());
    	leftPanel.add(new JLabel("Data Sets"), BorderLayout.NORTH);
    	leftPanel.add(new JLabel("center test"), BorderLayout.CENTER);
    	borderPanel.add(leftPanel, c2);
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2,false));
        tabbedPane.addTab("Datasheet", borderPanel);
    }
}
