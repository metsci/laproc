package com.metsci.laproc.display;

import com.metsci.glimpse.event.mouse.GlimpseMouseEvent;
import com.metsci.glimpse.event.mouse.GlimpseMouseListener;
import com.metsci.glimpse.painter.shape.PolygonPainter;
import com.metsci.glimpse.painter.shape.PolygonPainterSimple;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphPoint;
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
    private Window window;

    long lastClickTime = 0;

    boolean doubleClicked = false;
    boolean displayDoubleClick = false;

    GlimpseMouseEvent firstClick;

    public GraphDisplayerMouseListener(Graph graph, PolygonPainterSimple polygonPainter){
        this.graph = graph;
        this.polygonPainter = polygonPainter;
    }

    public GraphDisplayerMouseListener(Graph graph, Window window, PolygonPainterSimple polygonPainter){
        this.graph = graph;
        this.polygonPainter = polygonPainter;
        this.window = window;
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
            GraphPoint point = data.getDataPoint(glimpseMouseEvent.getAxisCoordinatesX(), glimpseMouseEvent.getAxisCoordinatesY());
            window.getConfusionMatrixPanel().updateConfusionMatrix(new double[]{point.get("True Positives"), point.get("False Positives")},
                    new double[]{point.get("True Negatives"), point.get("False Negatives")});

            window.getPointInfoPanel().update(point);
            window.repaint();
            System.out.println("X: " + point.getX() + " Y: " + point.getY());
            ret = point.getX();
        }
        return ret;
    }

}
