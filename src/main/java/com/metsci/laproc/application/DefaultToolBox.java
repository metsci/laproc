package com.metsci.laproc.application;

import com.metsci.laproc.display.*;

/**
 * The default implementation of the Abstract ToolBox class. This provides all the basic tools
 * that will be used by the application
 * Created by robinsat on 12/16/2016.
 */
public class DefaultToolBox extends ToolBox {

    protected void initializeTools() {

        this.addTool(new DataSheetPanel());
        this.addTool(new DataSetPanel(getDataReference()));
        this.addTool(new GraphOptionsPanel(getDataReference()));
        this.addTool(new GraphPanel(getDataReference()));
        this.addTool(new ConfusionPanel(getDataReference()));
        this.addTool(new PointInfoPanel(getDataReference()));
    }
}
