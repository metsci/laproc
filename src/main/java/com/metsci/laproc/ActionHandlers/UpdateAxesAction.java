package com.metsci.laproc.ActionHandlers;


import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.pointmetrics.ParametricFunction;

/**
 * Created by porterjc on 12/16/2016.
 */
public class UpdateAxesAction implements Action{
    private Graph graph;
    private ParametricFunction xAxis;
    private ParametricFunction yAxis;



    public UpdateAxesAction(Graph graph, ParametricFunction x, ParametricFunction y){
        this.graph = graph;
        this.xAxis = x;
        this.yAxis = y;
    }

    public void doAction() {
        this.graph.useAxisFunctions(this.xAxis, this.yAxis);
    }
}
