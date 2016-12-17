package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.display.ConfusionPanel;
import com.metsci.laproc.display.PointInfoPanel;
import com.metsci.laproc.plotting.GraphPoint;

/**
 * Created by porterjc on 12/16/2016.
 */
public class DisplayPointAnalyticsAction implements Action<GraphPoint[]>{
    private PointInfoPanel pointInfoPanel;
    private ConfusionPanel confusionPanel;

    public DisplayPointAnalyticsAction(PointInfoPanel pointPanel, ConfusionPanel confusePanel){
        this.pointInfoPanel = pointPanel;
        this.confusionPanel = confusePanel;
    }

    public void doAction(GraphPoint[] argument) {
        this.pointInfoPanel.update(argument);
    }
}
