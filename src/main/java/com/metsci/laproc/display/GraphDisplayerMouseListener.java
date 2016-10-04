package com.metsci.laproc.display;

import com.metsci.glimpse.event.mouse.GlimpseMouseEvent;
import com.metsci.glimpse.event.mouse.GlimpseMouseListener;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import java.awt.geom.Point2D;

/**
 *
 * Created by malinocr on 10/3/2016.
 */
public class GraphDisplayerMouseListener implements GlimpseMouseListener {
    Graph graph;

    long lastClickTime = 0;

    boolean doubleClicked = false;
    boolean displayDoubleClick = false;

    GlimpseMouseEvent firstClick;

    public GraphDisplayerMouseListener(Graph graph){
        this.graph = graph;
    }

    public void mouseEntered(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mouseExited(GlimpseMouseEvent glimpseMouseEvent) {

    }

    public void mousePressed(GlimpseMouseEvent glimpseMouseEvent) {
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
        if(displayDoubleClick){
            System.out.println("Double Click: ");
            displayClosestPoint(firstClick);
            displayClosestPoint(glimpseMouseEvent);
            displayDoubleClick = false;
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
     */
    private void displayClosestPoint(GlimpseMouseEvent glimpseMouseEvent){
        for(GraphableData data : graph.getData()){
            int index = findClosestIndex(data.getXValues(),
                    data.getYValues(),
                    glimpseMouseEvent.getAxisCoordinatesX(),
                    glimpseMouseEvent.getAxisCoordinatesY());
            System.out.println("X: " + data.getXValues()[index] + " Y: " + data.getYValues()[index]);
        }
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
