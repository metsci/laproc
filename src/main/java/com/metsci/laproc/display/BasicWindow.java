package com.metsci.laproc.display;

import com.metsci.glimpse.docking.*;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by porterjc on 9/22/2016.
 */
public class BasicWindow implements Window{
    private ConfusionPanel conmatrixPanel = new ConfusionPanel();
    private PointInfoPanel pointInfoPanel = new PointInfoPanel();
    private DockingFrame frame;
    private MultiSplitPane docker;
    private Tile analyticstiles;
    private GraphPanel graphPanel = new GraphPanel();
    private DataSheetPanel dataSheetPanel = new DataSheetPanel(this);
    private DataSetPanel dataSetPanel = new DataSetPanel(this);
    private GraphOptionsPanel optionsPanel;

    /**
     * Puts together a docking group and docks in default views
     * Creaded by porterjc on 9/22/2016
     */
    public void display() {
        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);

        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

        JPanel keyPanel = new JPanel();

        keyPanel.setBackground(Color.GREEN);

        this.frame = group.addNewFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        View sView = new View("Data", dataSheetPanel, "Data", true);
        View cView = new View("Sets", dataSetPanel, "Sets", true);
        View oView = new View("Options", optionsPanel, "Options", true);
        View gView = new View("Graph", graphPanel.getCanvas(), "Graph", true);
        View kView = new View("Confusion Matrix", conmatrixPanel, "Confusion Matrix", true);
        View pointView = new View("Point Analysis", pointInfoPanel, "Point Analysis", true);

        Tile spreadTile = tileFactory.newTile();
        spreadTile.addView(sView, 0);
        spreadTile.addView(cView, 1);
        spreadTile.addView(oView, 2);

        Tile graphTile = tileFactory.newTile();
        graphTile.addView(gView, 0);

        analyticstiles = tileFactory.newTile();
        analyticstiles.addView(kView, 0);
        analyticstiles.addView(pointView, 1);

        this.docker = frame.docker;

        docker.addInitialLeaf(graphTile);
        docker.addNeighborLeaf(analyticstiles, graphTile, Side.BOTTOM, 0.2);
        docker.addEdgeLeaf(spreadTile, Side.LEFT, 0.2);


        frame.setTitle("ROC Curve");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * sets up the GraphOptions that will be added to the display
     */
    public void showGraphOptions(Graph graph) {
        this.optionsPanel = new GraphOptionsPanel(this);
        this.optionsPanel.populateOptions(graph);
    }

    /**
     * sets up the GraphPanel that will be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph) {
        this.graphPanel.addGraphToCanvas(new GraphDisplayer(graph, this));
    }

    /**
     * Sets up a spreadsheet panel to be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet(ClassifierDataSet data) {
        this.dataSheetPanel.setDataSheet(data);
    }

    public GraphPanel getGraphPanel() {
        return this.graphPanel;
    }

    /**
     * adds classifier dataset table to the window
     * @param data
     */
    public void showClass(GraphableData data){
        this.dataSetPanel.clearTable();
        this.dataSetPanel.addDataSetToTable(data);
    }

    /**
     * adds the data to the classifier table
     * @param data
     */
    public void addDataSetToClass(GraphableData data){
        this.dataSetPanel.addDataSetToTable(data);
    }

    public void setSelectedDataSet (GraphableData data){
        this.graphPanel.setSelectedDataSet(data);
       // this.optionsPanel.populateOptions(data);
    }

    /**
     * Sets up a confusion matrix panel to be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showConfusionMatrix() {
        this.conmatrixPanel = new ConfusionPanel();
    }

    /**
     * returns confusion matrix panel
     * Creaded by porterjc on 9/22/2016
     */
    public ConfusionPanel getConfusionMatrixPanel() {
        return conmatrixPanel;
    }

    /**
     * returns returns point info panel
     * Creaded by porterjc on 9/22/2016
     */
    public PointInfoPanel getPointInfoPanel() {
        return pointInfoPanel;
    }

    /**
     * Repaints and revalidates the window.
      */
    public void repaint(){
        analyticstiles.revalidate();
        analyticstiles.repaint();
        frame.repaint();
    }

    public void repaintGraph(){
        this.graphPanel.updateGraph();
    }
}
