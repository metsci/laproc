package com.metsci.laproc;
import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
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
        final JFrame frame = new JFrame( "Glimpse Example" );
        frame.setSize( 800, 800 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
        GraphableFunction func = new ROCCurve(importData());
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
