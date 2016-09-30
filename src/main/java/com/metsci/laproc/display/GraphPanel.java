package com.metsci.laproc.display;

import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.ROCCurvePlot;
import com.metsci.laproc.plotting.Graph;

import javax.swing.*;

/**
 * Created by porterjc on 9/26/2016.
 */
public class GraphPanel extends JPanel{
    private NewtSwingGlimpseCanvas canvas;

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public GraphPanel(){
        canvas = new NewtSwingGlimpseCanvas();
    }

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public void addGraphToCanvas(Graph graph) {
        canvas.addLayout(new ROCCurvePlot(graph).getLayout());
        //this.add(canvas);
    }

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public void animateGraph() {
        new FPSAnimator(canvas.getGLDrawable(), 120).start();
    }

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public NewtSwingGlimpseCanvas getCanvas(){
        return canvas;
    }
}
