package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.event.mouse.GlimpseMouseEvent;
import com.metsci.glimpse.event.mouse.GlimpseMouseListener;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Mouse listener for selecting a set of points on a graph
 * Created by malinocr on 10/3/2016.
 */
public class GraphDisplayerMouseListener implements GlimpseMouseListener {

    private Graph graph;
    private PolygonPainter polygonPainter;
    private long lastClickTime = 0;
    private boolean doubleClicked = false;
    private boolean displayDoubleClick = false;
    private boolean isDisplayingPolygon = false;
    private IAction<Map<String, GraphPoint>>[] actionsOnClick;
    private IAction<Map<String, Map<Double, Double>>> actionOnDoubleClick;
    private GlimpseMouseEvent firstClick;

    //Defines maximum amount of time between two clicks to consider them together as a double click
    private static final int MOUSE_CLICK_NANOSECONDS = 500000000;

    /**
     * General constructor for GraphDisplayerMouseListener
     * @param graph graph to observe
     * @param polygonPainter polygon painter for selection area
     * @param actionsOnClick actions to perform on click
     */
    protected GraphDisplayerMouseListener(Graph graph, PolygonPainter polygonPainter, IAction<Map<String, Map<Double, Double>>> actionOnDoubleClick, IAction<Map<String, GraphPoint>>... actionsOnClick){
        this.graph = graph;
        this.polygonPainter = polygonPainter;
        this.actionsOnClick = actionsOnClick;
        this.actionOnDoubleClick = actionOnDoubleClick;

        configurePolygonPainter();
    }

    public void mouseEntered(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mouseExited(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mousePressed(GlimpseMouseEvent glimpseMouseEvent) {
        if(isDisplayingPolygon){ //Remove the previous polygon if the graph is clicked
            polygonPainter.deletePolygon(0,0);
            isDisplayingPolygon = false;
        }
        if(doubleClicked){ //If the graph was previously double clicked, display the selected region
            doubleClicked = false;
            displayDoubleClick = true;
        } else if(System.nanoTime() - lastClickTime < MOUSE_CLICK_NANOSECONDS){ //Set the graph a
            doubleClicked = true;
        } else {//Log clicked time to see if the next click is a double click
            lastClickTime = System.nanoTime();
        }
    }

    public void mouseReleased(GlimpseMouseEvent glimpseMouseEvent) {
        if(displayDoubleClick){ //If a region is selected to be displayed, display it on the graph
            System.out.println("Double Click: ");
            float x1 = (float)displayClosestPoint(firstClick);
            float x2 = (float)displayClosestPoint(glimpseMouseEvent);
            displayDoubleClick = false;
            float[] xValues = {x1,x1,x2,x2};

            datasetAreaPairs(glimpseMouseEvent);

            //Set to bigger than normal view so that edges are not seen
            float[] yValues = {-1,2,2,-1};
            this.polygonPainter.addPolygon(0,0,xValues,yValues,0);
            this.polygonPainter.setVisible(true);
            isDisplayingPolygon = true;
        } else if(doubleClicked){ // Log the second click for a double click for selecting the region
            firstClick = glimpseMouseEvent;
        } else {
            System.out.println("Single Click: ");
            displayClosestPoint(glimpseMouseEvent);
        }
    }

    /**
     * Displays the closest point to a given mouse click
     * @param glimpseMouseEvent mouse event for mouse click
     * @return the closest x value of the first line
     */
    private double displayClosestPoint(GlimpseMouseEvent glimpseMouseEvent){
        double ret = 0;
        GraphPoint[] points = graph.getClosestPoints(glimpseMouseEvent.getAxisCoordinatesX(), glimpseMouseEvent.getAxisCoordinatesY());
        Map<String, GraphPoint> datapoints = new HashMap<String, GraphPoint>();
        Iterator<GraphableData> dataList = graph.getData().iterator();

        for(int i = 0; i < points.length; i++) {
            datapoints.put(dataList.next().getName(), points[i]);
        }

        for(IAction<Map<String, GraphPoint>> action : this.actionsOnClick) {
            action.doAction(datapoints);
        }

        ret = glimpseMouseEvent.getAxisCoordinatesX();

        return ret;
    }

    /**
     * Configures the polygon painter to have the correct selection settings
     */
    private void configurePolygonPainter(){
        //Set up coloring for selected area
        this.polygonPainter.setFill(0,true);
        float[] fillColor = GlimpseColor.fromColorRgb(0.6f,0.6f,0.6f);
        //Make polygon transparent
        fillColor[3] = 0.5f;
        this.polygonPainter.setFillColor(0,fillColor);
        this.polygonPainter.setShowLines(0,true);
        float[] lineColor = GlimpseColor.fromColorRgb(0f,0f,0f);
        this.polygonPainter.setLineColor(0,lineColor);
        this.polygonPainter.setLineWidth(0,2);
    }

    /**
     * Creates a map of key value pairs representing data sets mapped to maps of values that correspond to the selected areas values.
     *
     * @param glimpseMouseEvent
     */
    private void datasetAreaPairs(GlimpseMouseEvent glimpseMouseEvent) {
        Map<String, Map<Double, Double>> graphValueRanges = new HashMap<String, Map<Double, Double>>();

        float x1 = (float)displayClosestPoint(firstClick);
        float x2 = (float)displayClosestPoint(glimpseMouseEvent);

        List<GraphableData> data = graph.getData();

        GraphPoint[] firstpoints = graph.getClosestPoints(firstClick.getAxisCoordinatesX(), firstClick.getAxisCoordinatesY());
        GraphPoint[] lastpoints = graph.getClosestPoints(glimpseMouseEvent.getAxisCoordinatesX(), glimpseMouseEvent.getAxisCoordinatesY());

        for (int j = 0; j < data.size(); j++) {
            Map<Double, Double> values = new HashMap<Double, Double>();
            GraphPoint first = firstpoints[j];
            values.put(first.getX(), first.getY());
            for (float i = x1; i < x2; i += 0.01f) {
                GraphPoint point = data.get(j).getPointGreaterOrEqual(i);
                values.put(point.getX(), point.getY());
            }
            GraphPoint last = lastpoints[j];
            values.put(last.getX(), last.getY());
            graphValueRanges.put(data.get(j).getName(), values);
        }

        this.actionOnDoubleClick.doAction(graphValueRanges);
    }
}
