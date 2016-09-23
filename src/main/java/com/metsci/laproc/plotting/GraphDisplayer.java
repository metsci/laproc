package com.metsci.laproc.plotting;

import com.metsci.glimpse.layout.GlimpseLayoutProvider;
import com.metsci.glimpse.painter.decoration.LegendPainter.*;
import com.metsci.glimpse.painter.info.CursorTextPainter;
import com.metsci.glimpse.painter.plot.XYLinePainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.color.GlimpseColor;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

/**
 * Creates a Glimpse plot for a Graph
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    Graph graph;

    public GraphDisplayer(Graph graph)
    {
        this.graph = graph;
    }

    public SimplePlot2D getLayout()
    {
        // Create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        Axis xAxis = graph.getXAxis();
        Axis yAxis = graph.getYAxis();

        // Set axis labels and chart title
        plot.setTitle( graph.getTitle() );
        plot.setAxisLabelX( xAxis.getName() );
        plot.setAxisLabelY( yAxis.getName() );

        // Set the x, y initial axis bounds
        plot.setMinX( xAxis.getMin() );
        plot.setMaxX( xAxis.getMax() );

        plot.setMinY( yAxis.getMin() );
        plot.setMaxY( yAxis.getMax() );

        // Only show the x and y crosshairs
        plot.getCrosshairPainter( ).showSelectionBox( false );

        //Set up Legend
        LineLegendPainter legend = new LineLegendPainter( LegendPlacement.SE );
        legend.setOffsetY( 10 );
        legend.setOffsetX( 100 );
        legend.setLegendItemWidth( 60 );

        // Draws a line for each set of graphable data
        for(GraphableData lineData : graph.getData()){
            XYLinePainter linePainter = createXYLinePainter( lineData );
            plot.addPainter( linePainter );
            legend.addItem( lineData.getName() , GlimpseColor.fromColorRgba( 1.0f, 0.0f, 0.0f, 0.8f ) );
        }

        // Add a painter to display the x and y position of the cursor
        CursorTextPainter cursorPainter = new CursorTextPainter( );
        plot.addPainter( cursorPainter );

        // Don't offset the text by the size of the selection box
        cursorPainter.setOffsetBySelectionSize( false );

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter( ).addPainter( legend );

        return plot;
    }

    public static XYLinePainter createXYLinePainter( GraphableData data )
    {
        XYLinePainter linePainter = new XYLinePainter( );
        linePainter.setData( data.getXValues(), data.getYValues() );
        linePainter.setLineColor( GlimpseColor.fromColorRgba( 1.0f, 0.0f, 0.0f, 0.8f ) );
        linePainter.setLineThickness( 1.5f );
        linePainter.showPoints( false );

        return linePainter;
    }
}