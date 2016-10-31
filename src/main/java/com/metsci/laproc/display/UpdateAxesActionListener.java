package com.metsci.laproc.display;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by porterjc on 10/31/2016.
 */
public class UpdateAxesActionListener implements ActionListener {
    private GraphableData data;
    private GraphOptionsPanel options;
    private Graph graph;
    private Window window;

    public UpdateAxesActionListener(GraphableData dat, GraphOptionsPanel panel, Window window, Graph graph){
        this.window = window;
        this.data = dat;
        this.options = panel;
        this.graph = graph;
    }

    public void actionPerformed(ActionEvent e) {
        this.data.useAxes(this.options.getSelectedXAxis(), this.options.getSelectedYAxis());
        this.window.getGraphPanel().getCanvas().removeAllLayouts();
        this.window.getGraphPanel().getCanvas().addLayout(new GraphDisplayer(graph, window).getLayout());
    }
}
