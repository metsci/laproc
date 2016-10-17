package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Action Listener for the classifier set panel that plots the selected sets on the graph panel
 * Created by malinocr on 10/17/2016.
 */
public class DisplayEvalSetActionListener implements ActionListener {
    private Window window;
    private ClassifierTable table;

    /**
     * Constructor for DisplayEvalSetActionListener
     * @param window the window that holds the graph and classifer set panel
     * @param table the classifer table in the classifer set panel
     */
    public DisplayEvalSetActionListener(Window window, ClassifierTable table) {
        this.window = window;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        Iterator<ClassifierDataSet> iterator = table.getSelectedValues().iterator();
        if(iterator.hasNext()){
            GraphableFunction func = new ROCCurve(iterator.next());
            GraphableFunctionOutput output = func.compute();
            BasicGraph graph = new BasicGraph(new Axis(0, 1, "X Axis"), new Axis(0, 1, "Y Axis"));
            graph.addData( output.getGraphableData("False Positive Rate", "True Positive Rate"));
            this.window.showGraph(graph);
        }
    }
}
