package com.metsci.laproc.display;

import com.metsci.glimpse.docking.*;
import com.metsci.laproc.plotting.Graph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by porterjc on 9/22/2016.
 */
public class BasicWindow implements Window{
    private GraphPanel graphPanel = new GraphPanel();

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public void display() {
        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);

        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

        JPanel spreadpanel = new JPanel();
        JPanel keyPanel = new JPanel();

        spreadpanel.setBackground(Color.blue);
        keyPanel.setBackground(Color.GREEN);

        DockingFrame frame = group.addNewFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        View sView = new View("Data", spreadpanel, "Data", true);
        View gView = new View("Graph", graphPanel.getCanvas(), "Graph", true);
        View kView = new View("WIP", keyPanel, "WIP", true);

        Tile spreadTile = tileFactory.newTile();
        spreadTile.addView(sView, 0);

        Tile graphTile = tileFactory.newTile();
        graphTile.addView(gView, 0);

        Tile keyTile = tileFactory.newTile();
        keyTile.addView(kView, 0);

        MultiSplitPane docker = frame.docker;

        docker.addInitialLeaf(graphTile);
        docker.addNeighborLeaf(keyTile, graphTile, Side.BOTTOM, 0.2);
        docker.addEdgeLeaf(spreadTile, Side.LEFT, 0.2);

        frame.setTitle("Basic GUI");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph) {
        this.graphPanel.addGraphToCanvas(graph);
        this.graphPanel.animateGraph();
    }

    /**
     *
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet() {

    }
}
