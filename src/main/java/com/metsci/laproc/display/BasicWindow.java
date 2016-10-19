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
    private GraphPanel graphPanel = new GraphPanel(this);
    private DataSheetPanel dataPanel = new DataSheetPanel(this);
    private ClassifierSetPanel classPanel = new ClassifierSetPanel(this);

    /**
     * Puts together a docking group and docks in default views
     * Creaded by porterjc on 9/22/2016
     */
    public void display() {
        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);

        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

        JPanel keyPanel = new JPanel();

        keyPanel.setBackground(Color.GREEN);

        DockingFrame frame = group.addNewFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        View sView = new View("Data", dataPanel, "Data", true);
        View cView = new View("Sets", classPanel, "Sets", true);
        View gView = new View("Graph", graphPanel.getCanvas(), "Graph", true);
        View kView = new View("WIP", keyPanel, "WIP", true);

        Tile spreadTile = tileFactory.newTile();
        spreadTile.addView(sView, 0);
        spreadTile.addView(cView, 1);

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
     * sets up the GraphPanel that will be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showGraph(Graph graph) {
        this.graphPanel.addGraphToCanvas(graph);
    }

    /**
     * Sets up a spreadsheet panel to be added to the display
     * Creaded by porterjc on 9/22/2016
     */
    public void showSpreadsheet(ClassifierDataSet data) {
        this.dataPanel.setDataSheet(data);
    }

    public void showClass(ClassifierDataSet data){
        this.classPanel.clearTable();
        this.classPanel.addClassifierSetToTable("Initial Classifier Data Set",data);
    }

    public void addDataToClass(String name, ClassifierDataSet data){
        this.classPanel.addClassifierSetToTable(name, data);
    }
}
