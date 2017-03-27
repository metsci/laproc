package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.canvas.FBOGlimpseCanvas;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.glimpse.gl.util.GLUtils;
import com.metsci.glimpse.painter.decoration.LegendPainter;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.font.FontUtils;
import com.metsci.laproc.plotting.*;

import javax.imageio.ImageIO;
import javax.media.opengl.GLOffscreenAutoDrawable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class that handles exporting graphs to an image
 * Created by malinocr on 1/21/2017.
 */
public class GraphExporter{
    /**
     * Exports an image of the given graph to the given file path
     * @param filePath filepath to export the graph
     * @param graph graph to export image
     * @throws IOException
     */
    public static void exportGraph(String filePath, Graph graph) throws IOException {
        //Create a new canvas with specific settings for an exported graph
        NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas();
        SimplePlot2D plot = GraphExporter.getLayout(graph);
        plot.setTitleFont( FontUtils.getDefaultBold( 18 ) );
        canvas.addLayout(GraphExporter.getLayout(graph));

        //Create a drawable that can create an image of its layout
        GLOffscreenAutoDrawable glDrawable = GLUtils.newOffscreenDrawable( canvas.getGLProfile() );
        FBOGlimpseCanvas offscreenCanvas = new FBOGlimpseCanvas(glDrawable.getContext(), 1000, 1000 );
        offscreenCanvas.addLayout(GraphExporter.getLayout(graph));
        BufferedImage image = offscreenCanvas.toBufferedImage();
        ImageIO.write(image, "PNG", new File(filePath));
    }

    /**
     * Gets the glimpse layout that will be displayed for the graph
     * @param graph graph to display the layout
     * @return glimpse layout of the graph
     */
    private static SimplePlot2D getLayout(Graph graph)
    {
        // Create a plot frame
        SimplePlot2D plot = new SimplePlot2D( );

        // Set axis labels and chart title
        plot.setTitle(graph.getTitle());
        GraphDisplayer.setPlotAxis(graph.getXAxis(), graph.getYAxis(), plot);

        // Only show the x and y crosshairs
        plot.getCrosshairPainter().setVisible(false);

        //Set up Legend
        LegendPainter.LineLegendPainter legend = GraphDisplayer.createLineLegendPainter();

        //Draws each graphable data
        GraphDisplayer.drawGraphableData(graph.getData(), plot, legend);

        // Add the legend painter to the top of the center GlimpseLayout
        plot.getLayoutCenter().addPainter(legend);

        return plot;
    }
}
