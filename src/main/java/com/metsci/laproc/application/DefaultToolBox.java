package com.metsci.laproc.application;

import com.metsci.laproc.action.UpdateGenericDisplayAction;
import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.datareference.GraphReferenceImpl;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.tools.*;
import com.metsci.laproc.uicomponents.GraphDisplayer;
import com.metsci.laproc.uicomponents.GraphRenderer;

import java.util.Map;

/**
 * The default implementation of the Abstract ToolBox class. This provides all the basic tools
 * that will be used by the application
 * Created by robinsat on 12/16/2016.
 */
public class DefaultToolBox extends ToolBox {

    /**
     * Default constructor for a toolbox
     * @param inputDataReference input reference for the toolbox
     * @param outputDataReference output reference for the toolbox
     */
    public DefaultToolBox(InputDataReference inputDataReference, OutputDataReference outputDataReference) {
        super(inputDataReference, outputDataReference);
    }

    protected void initializeTools() {
        //Create various tools and a graph reference
        GraphReference graphReference = new GraphReferenceImpl(getOutputReference());
        ConfusionPanel confusionPanel = new ConfusionPanel();
        PointInfoPanel pointInfoPanel = new PointInfoPanel();
        GraphDisplayer graphDisplayer = new GraphDisplayer(new UpdateGenericDisplayAction<Map<String, GraphPoint>>(confusionPanel),
                new UpdateGenericDisplayAction<Map<String, GraphPoint>>(pointInfoPanel));
        GraphPanel graphPanel = new GraphPanel(graphReference, graphDisplayer);

        //Add created tools and reference to the toolbox
        this.addTool(new EvaluationSetPanel(getInputReference(), getOutputReference()));
        this.addTool(new GraphableDataPanel(getOutputReference()));
        this.addTool(new GraphOptionsPanel(getOutputReference(),
                graphDisplayer));
        this.addTool(graphPanel);
        this.addTool(confusionPanel);
        this.addTool(pointInfoPanel);
    }
}
