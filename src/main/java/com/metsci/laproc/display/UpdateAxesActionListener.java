package com.metsci.laproc.display;

import com.metsci.laproc.plotting.GraphableData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by porterjc on 10/31/2016.
 */
public class UpdateAxesActionListener implements ActionListener {
    private GraphableData data;
    private GraphOptionsPanel options;
    private Window window;

    /**
     * Contructor for this listener
     *
     * @param dat
     * @param panel
     * @param window
     */
    public UpdateAxesActionListener(GraphableData dat, GraphOptionsPanel panel, Window window){
        this.window = window;
        this.data = dat;
        this.options = panel;
    }

    /**
     * when an action happens update the axes and redraw the curve
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        this.data.useAxes(this.options.getSelectedXAxis(), this.options.getSelectedYAxis());
        this.window.repaintGraph();
    }
}
