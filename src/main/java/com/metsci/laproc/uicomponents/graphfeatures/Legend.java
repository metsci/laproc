package com.metsci.laproc.uicomponents.graphfeatures;

import com.metsci.glimpse.painter.decoration.LegendPainter;
import com.metsci.glimpse.plot.Plot2D;
import com.metsci.glimpse.util.Pair;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.uicomponents.GraphVisualProperties;
import com.metsci.laproc.uicomponents.PainterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adds a legend to the plot
 * Created by robinsat on 4/6/2017.
 */
public class Legend implements GraphFeature {

    /** A list storing the legend entries as a String and the corresponding color */
    List<Pair<String, float[]>> lineEntries;
    List<Pair<String, float[]>> blockEntries;

    /** Constructor */
    public Legend() {
        this.lineEntries = new ArrayList<Pair<String, float[]>>();
        this.blockEntries = new ArrayList<Pair<String, float[]>>();
    }

    /**
     * Adds an entry to the legend to display as a line of the given color
     * @param label The string to display on the legend
     * @param color The color to display on the legend
     */
    public void addLineEntry(String label, float[] color) {
        this.lineEntries.add(new Pair<String, float[]>(label, color));
    }

    /**
     * Adds an entry to the legend to display as a color block
     * @param label The string to display on the legend
     * @param color The color to display on the legent
     */
    public void addBlockEntry(String label, float[] color) {
        this.blockEntries.add(new Pair<String, float[]>(label, color));
    }

    /**
     * Applies this feature to the graph
     * @param graph      The Graph object containing the data that will be drawn
     * @param plot       The graph on which to draw this feature
     * @param properties The properties to use when drawing the graph
     */
    public void applyToPlot(Graph graph, Plot2D plot, Legend legend, GraphVisualProperties properties) {
        PainterFactory factory = new PainterFactory(properties);
        LegendPainter.LineLegendPainter lineLegendPainter =
                factory.getLineLegendPainter(LegendPainter.LegendPlacement.SE);

        for(Pair<String, float[]> lines : this.lineEntries) {
            lineLegendPainter.addItem(lines.first(), lines.second());
        }
        LegendPainter.BlockLegendPainter blockPainter = new LegendPainter.BlockLegendPainter(LegendPainter.LegendPlacement.SE);
        for(Pair<String, float[]> lines : this.blockEntries) {
            blockPainter.addItem(lines.first(), lines.second());
        }
        plot.addPainter(blockPainter);
        plot.addPainter(lineLegendPainter);
    }
}
