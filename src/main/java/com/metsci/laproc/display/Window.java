package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.Graph;

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
     * adds a graphcomponent to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph);

    /**
     * adds a spreadsheet component to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet(ClassifierDataSet data);

    public void showConfusionMatrix();
}
