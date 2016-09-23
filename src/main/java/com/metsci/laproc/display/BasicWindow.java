package com.metsci.laproc.display;

import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.ROCCurvePlot;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Curve;
import com.metsci.laproc.plotting.Graph;

import javax.swing.*;

/**
 * Created by porterjc on 9/22/2016.
 */
public class BasicWindow implements Window{
    private NewtSwingGlimpseCanvas canvas;

    public void display(Graph graph) {
        final JFrame frame = new JFrame("Basic Widnow");
        frame.setSize( 800, 800 );
        frame.setVisible( true );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);

        canvas = showGraph(graph);

        new FPSAnimator(canvas.getGLDrawable(), 120).start();
        tabbedPane.add("ROCCurvePlot",canvas);
    }

    public NewtSwingGlimpseCanvas showGraph(Graph graph) {
        NewtSwingGlimpseCanvas canv = new NewtSwingGlimpseCanvas();

        canv.addLayout(new ROCCurvePlot(graph).getLayout());
        return canv;
    }

    public void showSpreadsheet() {

    }
}
