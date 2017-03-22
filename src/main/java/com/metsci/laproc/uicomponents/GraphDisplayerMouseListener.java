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
import java.util.Map;

/**
 * Mouse listener for selecting a set of points
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
    private GlimpseMouseEvent firstClick;

    private static final int MOUSE_CLICK_NANOSECONDS = 500000000;

    /**
     * General constructor for GraphDisplayerMouseListener
     * @param polygonPainter polygon painter for selection area
     */
    protected GraphDisplayerMouseListener(Graph graph, PolygonPainter polygonPainter, IAction<Map<String, GraphPoint>>... actionsOnClick){
        this.graph = graph;
        this.polygonPainter = polygonPainter;
        this.actionsOnClick = actionsOnClick;

        configurePolygonPainter();
    }

    public void mouseEntered(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mouseExited(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mousePressed(GlimpseMouseEvent glimpseMouseEvent) {
        if(isDisplayingPolygon){
            polygonPainter.deletePolygon(0,0);
            isDisplayingPolygon = false;
        }
        if(doubleClicked){
            doubleClicked = false;
            displayDoubleClick = true;
        } else if(System.nanoTime() - lastClickTime < MOUSE_CLICK_NANOSECONDS){ //This is based on the normal double click time
            doubleClicked = true;
        } else {
            lastClickTime = System.nanoTime();
        }
    }

    public void mouseReleased(GlimpseMouseEvent glimpseMouseEvent) {
        if(displayDoubleClick){
            System.out.println("Double Click: ");
            float x1 = (float)displayClosestPoint(firstClick);
            float x2 = (float)displayClosestPoint(glimpseMouseEvent);
            displayDoubleClick = false;
            float[] xValues = {x1,x1,x2,x2};

            //Set to bigger than normal view so that edges are not seen
            float[] yValues = {-1,2,2,-1};
            this.polygonPainter.addPolygon(0,0,xValues,yValues,0);
            isDisplayingPolygon = true;
        } else if(doubleClicked){
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
}
