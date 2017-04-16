package com.metsci.laproc.tools;

import com.metsci.glimpse.canvas.FBOGlimpseCanvas;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.glimpse.gl.util.GLUtils;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.font.FontUtils;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.uicomponents.GraphRenderer;

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
        GraphRenderer renderer = new GraphRenderer();
        SimplePlot2D plot = renderer.getLayout(graph);
        // Only show the x and y crosshairs
        plot.getCrosshairPainter().setVisible(false);

        //Create a new canvas with specific settings for an exported graph
        NewtSwingGlimpseCanvas canvas = new NewtSwingGlimpseCanvas();
        plot.setTitleFont( FontUtils.getDefaultBold( 18 ) );

        canvas.addLayout(plot);
        GLOffscreenAutoDrawable glDrawable = GLUtils.newOffscreenDrawable( canvas.getGLProfile() );
        FBOGlimpseCanvas offscreenCanvas = new FBOGlimpseCanvas(glDrawable.getContext(), 1000, 1000 );
        offscreenCanvas.addLayout(plot);
        BufferedImage image = offscreenCanvas.toBufferedImage();
        ImageIO.write(image, "PNG", new File(filePath));
    }
}
