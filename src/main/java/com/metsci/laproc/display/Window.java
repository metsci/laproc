package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

/**
 * Created by porterjc on 9/21/2016.
 */
public interface Window {

    /**
     * Displays and constructs a GUI
     * Creaded by porterjc on 9/22/2016
     */
    public void display();

    /**
     * Adds a graphoptions panel to the window
     * @param data
     */
    public void showGraphOptions(GraphableData data);

    /**
     * adds a graphcomponent to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph);

    /**
     * adds a spreadsheet component to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet(ClassifierDataSet data);

    /**
     * Returns the graph Panel
     */
    public GraphPanel getGraphPanel();

    /**
     * Returns the confusion matrix panel
     * @return
     */
    public ConfusionPanel getConfusionMatrixPanel();

    /**
     * Returns the point info panel
     * @return
     */
    public PointInfoPanel getPointInfoPanel();

    /**
     * Forces a repaint of the frame and docker utility
     */
    public void repaint();

    /**
     * Forces graph panel to repaint based on current displayer
     */
    public void repaintGraph();

    /**
     * adds a data set component to the display
     * Created by malinocr on 10/17/2016
     */
    public void showClass(GraphableData data);

    /**
     * adds data set to classifier panel
     * Created by malinocr on 10/17/2016
     */
    public void addDataSetToClass(GraphableData data);

    /**
     * sets the selected data in the graph
     * Created by malinocr on 10/17/2016
     */
    public void setSelectedDataSet(GraphableData data);

    /**
     * Updates the graph's axis
     * @param x new x axis
     * @param y new y axis
     */
    public void updateGraphAxis(Axis x, Axis y);
}
