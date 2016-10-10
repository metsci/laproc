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
 *
 * Created by malinocr on 10/3/2016.
 */
public class GraphDisplayerMouseListener implements GlimpseMouseListener {
    Graph graph;
    PolygonPainterSimple polygonPainter;

    long lastClickTime = 0;

    boolean doubleClicked = false;
    boolean displayDoubleClick = false;

    GlimpseMouseEvent firstClick;

    public GraphDisplayerMouseListener(Graph graph, PolygonPainterSimple polygonPainter){
        this.graph = graph;
        this.polygonPainter = polygonPainter;
    }

    public void mouseEntered(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mouseExited(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mousePressed(GlimpseMouseEvent glimpseMouseEvent) {
        polygonPainter.clear();
        if(doubleClicked == true){
            doubleClicked = false;
            displayDoubleClick = true;
        } else if(System.nanoTime() - lastClickTime < 500000000){
            doubleClicked = true;
        } else {
            lastClickTime = System.nanoTime();
        }
    }

    public void mouseReleased(GlimpseMouseEvent glimpseMouseEvent) {
        polygonPainter.clear();
        if(displayDoubleClick){
            System.out.println("Double Click: ");
            float x1 = (float)displayClosestPoint(firstClick);
            float x2 = (float)displayClosestPoint(glimpseMouseEvent);
            displayDoubleClick = false;

            float[] testX = {x1,x1,x2,x2};
            float[] testY = {0,1,1,0};
            float[] testColor = GlimpseColor.fromColorRgb(0.5f,0.5f,0.5f);
            polygonPainter.addPolygon(0,testX,testY,testColor);
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
