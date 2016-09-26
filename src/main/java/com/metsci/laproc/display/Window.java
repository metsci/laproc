package com.metsci.laproc.display;

import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.laproc.plotting.Graph;

/**
 * Created by porterjc on 9/21/2016.
 */
public interface Window {

    public void display();

    public void showGraph(Graph graph);

    public void showSpreadsheet();
}
