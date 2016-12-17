package com.metsci.laproc.application;

import com.metsci.laproc.tools.*;

/**
 * The default implementation of the Abstract ToolBox class. This provides all the basic tools
 * that will be used by the application
 * Created by robinsat on 12/16/2016.
 */
public class DefaultToolBox extends ToolBox {

    public DefaultToolBox(DataReference reference) {
        super(reference);
    }

    protected void initializeTools() {

        ConfusionPanel confusionPanel = new ConfusionPanel();
        PointInfoPanel pointInfoPanel = new PointInfoPanel();

        this.addTool(new DataSheetPanel(getDataReference()));
        this.addTool(new DataSetPanel(getDataReference()));
        this.addTool(new GraphOptionsPanel(getDataReference()));
        this.addTool(new GraphPanel(getDataReference(), confusionPanel, pointInfoPanel));
        this.addTool(confusionPanel);
        this.addTool(pointInfoPanel);
    }
}
