package com.metsci.laproc;
import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;

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

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);
        NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas( );
        canvas.addLayout(new ScatterPlot().getLayout());
        new FPSAnimator( canvas.getGLDrawable( ), 120 ).start( );
        tabbedPane.add("ScatterPlot",canvas);
    }
}
