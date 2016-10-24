package com.metsci.laproc.display;

import com.metsci.glimpse.docking.*;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.Graph;
import javax.swing.*;
import java.awt.*;

/**
 * Created by porterjc on 9/22/2016.
 */
public class BasicWindow implements Window{
    private GraphPanel graphPanel = new GraphPanel();
    private JScrollPane dataPanel = new JScrollPane();
    private ConfusionPanel conmatrixPanel = new ConfusionPanel();
    private PointInfoPanel pointInfoPanel = new PointInfoPanel();
    private GraphDisplayer displayer;
    private DockingFrame frame;
    private MultiSplitPane docker;
    private Tile analyticstiles;

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

        View sView = new View("Data", dataPanel, "Data", true);
        View gView = new View("Graph", graphPanel.getCanvas(), "Graph", true);
        View kView = new View("Confusion Matrix", conmatrixPanel, "Confusion Matrix", true);
        View pointView = new View("Point Analysis", pointInfoPanel, "Point Analysis", true);

        Tile spreadTile = tileFactory.newTile();
        spreadTile.addView(sView, 0);

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
     * sets up the GraphPanel that will be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph) {
        this.displayer = new GraphDisplayer(graph, this);
        this.graphPanel.addGraphToCanvas(this.displayer);
        this.graphPanel.animateGraph();
    }

    /**
     * Sets up a spreadsheet panel to be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet(ClassifierDataSet data) {
        this.dataPanel = DataSheetPanel.GetDataSheet(data);
        
    }

    public void showConfusionMatrix() {
        this.conmatrixPanel = new ConfusionPanel();
    }

    public ConfusionPanel getConfusionMatrixPanel() {
        return conmatrixPanel;
    }

    public PointInfoPanel getPointInfoPanel() {
        return pointInfoPanel;
    }

    public void repaint(){

        analyticstiles.revalidate();
        analyticstiles.repaint();
        frame.repaint();
    }
}
