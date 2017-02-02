package com.metsci.laproc.application;

import com.metsci.laproc.action.UpdateGenericDisplayAction;
import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.datareference.GraphReferenceImpl;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.tools.*;

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

        GraphReference graphReference = new GraphReferenceImpl(getOutputReference());
        ConfusionPanel confusionPanel = new ConfusionPanel();
        PointInfoPanel pointInfoPanel = new PointInfoPanel();
        GraphPanel graphPanel = new GraphPanel(graphReference,
                new UpdateGenericDisplayAction<GraphPoint[]>(confusionPanel),
                new UpdateGenericDisplayAction<GraphPoint[]>(pointInfoPanel));

        this.addTool(new EvaluationSetPanel(getInputReference(), getOutputReference()));
        this.addTool(new DataSetPanel(getOutputReference()));
        this.addTool(new GraphOptionsPanel(getOutputReference(),
                new GraphDisplayManagerImpl(graphReference, graphPanel)));
        this.addTool(graphPanel);
        this.addTool(confusionPanel);
        this.addTool(pointInfoPanel);
    }
}
