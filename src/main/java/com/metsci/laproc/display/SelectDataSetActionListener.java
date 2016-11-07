package com.metsci.laproc.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by malinocr on 10/30/2016.
 */
public class SelectDataSetActionListener implements ActionListener {
    private Window window;
    private DataSetTable table;

    /**
     * Constructor for SelectDataSetActionListener
     * @param window the window that holds the graph and data set panel
     * @param table the data set table in the data set panel
     */
    public SelectDataSetActionListener(Window window, DataSetTable table) {
        this.window = window;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        this.window.setSelectedDataSet(table.getFirstSelectedValue());
        this.window.repaintGraph();
    }
}
