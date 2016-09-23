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
 * Created by malinocr on 9/20/2016.
 */
public class GraphDisplayer implements GlimpseLayoutProvider
{
    Graph graph;

    public GraphDisplayer(Graph graph ){
        this.graph = graph;
    }

    public static int NUM_POINTS = 100;

    public SimplePlot2D getLayout()
    {
        // create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        Axis xAxis = graph.getXAxis();
        Axis yAxis = graph.getYAxis();

        // set axis labels and chart title
        plot.setTitle( "ROC Curve Example" );
        plot.setAxisLabelX( xAxis.getName() );
        plot.setAxisLabelY( yAxis.getName() );

        // set the x, y initial axis bounds
        plot.setMinX( xAxis.getMin() );
        plot.setMaxX( xAxis.getMax() );

        plot.setMinY( yAxis.getMin() );
        plot.setMaxY( yAxis.getMax() );

        // don't show the square selection box, only the x and y crosshairs
        plot.getCrosshairPainter( ).showSelectionBox( false );

        // draws a line for each set of graphable data
        for(GraphableData g : graph.getData()){
            XYLinePainter series = createXYLinePainter( g );
            plot.addPainter( series );
        }

        // add a painter to display the x and y position of the cursor
        CursorTextPainter cursorPainter = new CursorTextPainter( );
        plot.addPainter( cursorPainter );

        // don't offset the text by the size of the selection box, since we aren't showing it
        cursorPainter.setOffsetBySelectionSize( false );

        LineLegendPainter legend = new LineLegendPainter( LegendPlacement.SE );
        legend.setOffsetY( 10 );
        legend.setOffsetX( 100 );
        legend.addItem( "Series 1", GlimpseColor.fromColorRgba( 1.0f, 0.0f, 0.0f, 0.8f ) );
        legend.addItem( "Series 2", GlimpseColor.fromColorRgba( 0.0f, 0.0f, 1.0f, 0.8f ) );
        legend.setLegendItemWidth( 60 );

        // add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter( ).addPainter( legend );

        return plot;
    }

    public static XYLinePainter createXYLinePainter( GraphableData data )
    {

        XYLinePainter series = new XYLinePainter( );
        series.setData( data.getXValues(), data.getYValues() );
        series.setLineColor( GlimpseColor.fromColorRgba( 1.0f, 0.0f, 0.0f, 0.8f ) );
        series.setLineThickness( 1.5f );
        series.showPoints( false );

        return series;
    }

    public static XYLinePainter createXYLinePainter2( )
    {
        // add two data series to the plot
        float[] dataX = new float[NUM_POINTS];
        float[] dataY = new float[NUM_POINTS];

        XYLinePainter series2 = new XYLinePainter( );
        generateData2( dataX, dataY, NUM_POINTS );
        series2.setData( dataX, dataY );
        series2.setLineColor( GlimpseColor.fromColorRgba( 0.0f, 0.0f, 1.0f, 0.8f ) );
        series2.setLineThickness( 1.5f );
        series2.showPoints( false );

        return series2;
    }

    public static void generateData1( float[] dataX, float[] dataY, int size )
    {
        for ( int i = 0; i < size; i++ )
        {
            float x = (float)((1.0/size) * i);

            dataX[i] = x;
            dataY[i] = ( float ) (Math.sqrt( x ) * 0.9);
        }
    }

    public static void generateData2( float[] dataX, float[] dataY, int size )
    {
        for ( int i = 0; i < size; i++ )
        {
            float x = (float)((1.0/size) * i);;

            dataX[i] = x;
            dataY[i] = ( float ) Math.sqrt( x );
        }
    }
}