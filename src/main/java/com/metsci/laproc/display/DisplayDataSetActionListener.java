package com.metsci.laproc.display;

import com.metsci.laproc.plotting.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the data set panel that plots the selected sets on the graph panel
 * Created by malinocr on 10/17/2016.
 */
public class DisplayDataSetActionListener implements ActionListener {
    private Window window;
    private DataSetTable table;

    /**
     * Constructor for DisplayDataSetActionListener
     * @param window the window that holds the graph and data set panel
     * @param table the data set table in the data set panel
     */
    public DisplayDataSetActionListener(Window window, DataSetTable table) {
        this.window = window;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        BasicGraph graph = new BasicGraph();
        for(GraphableData data : table.getSelectedValues()){
            graph.addData( data);
        }
//        this.window.showGraph(graph);
    }
}
