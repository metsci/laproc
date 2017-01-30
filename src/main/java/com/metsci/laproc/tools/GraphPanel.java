package com.metsci.laproc.tools;

import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.glimpse.docking.View;
import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.uicomponents.GraphDisplayer;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.utils.IObserver;


/**
 * Created by porterjc on 9/26/2016.
 */
public class GraphPanel implements ITool, IObserver<GraphReference> {
    private NewtSwingGlimpseCanvas canvas;
    private GraphDisplayer graphDisplayer;

    /**
     * Simple constructor
     * Creaded by porterjc on 9/22/2016
     */

    public GraphPanel(GraphReference reference, IAction<GraphPoint[]>... clickActions){
        reference.addObserver(this);
        canvas = new NewtSwingGlimpseCanvas();
        new FPSAnimator(canvas.getGLDrawable(), 120).start();
        graphDisplayer = new GraphDisplayer(clickActions);
        this.addGraphToCanvas(graphDisplayer);
    }

	/**
     * Takes a graph and properly builds a canvas from the layout generated by the graph
     * Creaded by porterjc on 9/22/2016
     */
    public void addGraphToCanvas(GraphDisplayer displayer) {
        canvas.removeAllLayouts();
        canvas.addLayout(displayer.getLayout());
        this.graphDisplayer = displayer;
    }

    /**
     * Returns the canvas
     * Creaded by porterjc on 9/22/2016
     */
    public NewtSwingGlimpseCanvas getCanvas(){
        return canvas;
    }

    public View getView() {
        return new View("Graph", this.getCanvas(), "Graph", true);
    }

    public int getDefaultPosition() {
        return ITool.CENTERPOSITION;
    }

    public void update(GraphReference reference) {
        canvas.removeAllLayouts();
        graphDisplayer.setGraph(reference.getGraph());
        canvas.addLayout(graphDisplayer.getLayout());
    }
}
