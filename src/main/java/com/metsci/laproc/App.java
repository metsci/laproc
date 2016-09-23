package com.metsci.laproc;
import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.*;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final JFrame frame = new JFrame( "Glimpse Example" );
        frame.setSize( 800, 800 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
        GraphableFunction func = new ROCCurve(new ClassifierDataSet());
        graph.addData( func.compute());

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);
        NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas( );
        canvas.addLayout(new GraphDisplayer(graph).getLayout());
        new FPSAnimator( canvas.getGLDrawable( ), 120 ).start( );
        tabbedPane.add("GraphDisplayer",canvas);

        NewtSwingGlimpseCanvas canvas2 = new NewtSwingGlimpseCanvas( );
        canvas2.addLayout(new GraphDisplayer(graph).getLayout());
        new FPSAnimator( canvas2.getGLDrawable( ), 120 ).start( );
        tabbedPane.add("GraphDisplayer2",canvas2);
    }
}
