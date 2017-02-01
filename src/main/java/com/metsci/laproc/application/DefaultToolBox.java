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

    public DefaultToolBox(InputDataReference inputDataReference, OutputDataReference outputDataReference) {
        super(inputDataReference, outputDataReference);
    }

    protected void initializeTools() {

        ConfusionPanel confusionPanel = new ConfusionPanel();
        PointInfoPanel pointInfoPanel = new PointInfoPanel();
        GraphReference graphReference = new GraphReferenceImpl(getOutputReference());

        this.addTool(new EvaluationSetPanel(getInputReference(), getOutputReference()));
        this.addTool(new DataSetPanel(getOutputReference()));
        this.addTool(new GraphOptionsPanel(getOutputReference()));
        this.addTool(new GraphPanel(graphReference,
                new UpdateGenericDisplayAction<GraphPoint[]>(confusionPanel),
                new UpdateGenericDisplayAction<GraphPoint[]>(pointInfoPanel)));
        this.addTool(confusionPanel);
        this.addTool(pointInfoPanel);
    }
}
