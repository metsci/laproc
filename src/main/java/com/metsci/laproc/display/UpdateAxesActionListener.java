package com.metsci.laproc.display;

import com.metsci.laproc.plotting.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by porterjc on 10/31/2016.
 */
public class UpdateAxesActionListener implements ActionListener {
    private Graph graph;
    private GraphOptionsPanel options;

    /**
     * Contructor for this listener
     *
     * @param graph
     * @param panel
     */
    public UpdateAxesActionListener(Graph graph, GraphOptionsPanel panel){
        this.graph = graph;
        this.options = panel;
    }

    /**
     * when an action happens update the axes and redraw the curve
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        this.graph.useAxisFunctions(this.options.getSelectedXAxis(), this.options.getSelectedYAxis());
    }
}
