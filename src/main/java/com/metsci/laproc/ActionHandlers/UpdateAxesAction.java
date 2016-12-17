package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * Created by porterjc on 12/16/2016.
 */
public class UpdateAxesAction implements Action<ParametricFunction[]>{
    private Graph graph;



    public UpdateAxesAction(Graph graph){
        this.graph = graph;
    }

    public void doAction(ParametricFunction[] argument) {
        this.graph.useAxisFunctions(argument[0], argument[1]);
    }
}
