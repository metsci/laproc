package com.metsci.laproc.display;

import com.metsci.glimpse.event.mouse.GlimpseMouseEvent;
import com.metsci.glimpse.event.mouse.GlimpseMouseListener;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.painter.shape.PolygonPainterSimple;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import javafx.scene.shape.Circle;

import java.awt.geom.Point2D;

/**
 * Mouse listener for selecting a set of points
 * Created by malinocr on 10/3/2016.
 */
public class GraphDisplayerMouseListener implements GlimpseMouseListener {
    Graph graph;
    PolygonPainter polygonPainter;
    long lastClickTime = 0;
    boolean doubleClicked = false;
    boolean displayDoubleClick = false;
    boolean isDisplayingPolygon = false;
    GlimpseMouseEvent firstClick;

    public static final int MOUSE_CLICK_NANOSECONDS = 500000000;

    public GraphDisplayerMouseListener(Graph graph, PolygonPainter polygonPainter){
        this.graph = graph;
        this.polygonPainter = polygonPainter;

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

    public void mouseEntered(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mouseExited(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mousePressed(GlimpseMouseEvent glimpseMouseEvent) {
        if(isDisplayingPolygon){
            polygonPainter.deletePolygon(0,0);
            isDisplayingPolygon = false;
        }
        if(doubleClicked == true){
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
        for(GraphableData data : graph.getData()){
            int index = findClosestIndex(data.getXValues(),
                    data.getYValues(),
                    glimpseMouseEvent.getAxisCoordinatesX(),
                    glimpseMouseEvent.getAxisCoordinatesY());
            System.out.println("X: " + data.getXValues()[index] + " Y: " + data.getYValues()[index]);
            ret = data.getXValues()[index];
        }
        return ret;
    }

    /**
     * Find the index of the closest point given an x and y value
     * @return index of the closest point
     */
    private static int findClosestIndex(double[] xValues, double[] yValues, double x, double y){
        int closestIndex = 0;
        double closestDistance = Point2D.distance(xValues[0],yValues[0],x,y);
        for(int i = 1; i < xValues.length; i++){
            double currentDistance = Point2D.distance(xValues[i],yValues[i],x,y);
            if(currentDistance < closestDistance){
                closestDistance = currentDistance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }
}
