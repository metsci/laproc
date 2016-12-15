package com.metsci.laproc.display;

import com.metsci.glimpse.docking.*;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.GraphableData;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by porterjc on 9/22/2016.
 */
public abstract class Window {
    private DockingFrame frame;
    private MultiSplitPane docker;
    private List<Tile> tiles;
    private Tile analyticstiles;
    private Tile spreadTile;
    private Tile graphTile;

    private DataSetPanel dataSetPanel = new DataSetPanel(this);

    public Window(){
        tiles = new ArrayList<Tile>();

        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);
        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

        this.frame = group.addNewFrame();

        spreadTile = tileFactory.newTile();
        //spreadTile.addView(sView, 0);
        //spreadTile.addView(cView, 1);
        //spreadTile.addView(oView, 2);

        tiles.add(spreadTile);

        graphTile = tileFactory.newTile();
        //graphTile.addView(gView, 0);

        tiles.add(graphTile);

        analyticstiles = tileFactory.newTile();
        //analyticstiles.addView(kView, 0);
        //analyticstiles.addView(pointView, 1);

        tiles.add(analyticstiles);
    }

    /**
     * Puts together a docking group and docks in default views
     * Creaded by porterjc on 9/22/2016
     */
    public void display() {
        JPanel keyPanel = new JPanel();

        keyPanel.setBackground(Color.GREEN);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //View oView = optionsPanel.getView();
        //View sView = new View("Data", dataSheetPanel, "Data", true);
        //View cView = new View("Sets", dataSetPanel, "Sets", true);
        //View gView = new View("Graph", graphPanel.getCanvas(), "Graph", true);
        //View kView = new View("Confusion Matrix", conmatrixPanel, "Confusion Matrix", true);
        //View pointView = new View("Point Analysis", pointInfoPanel, "Point Analysis", true);

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

    public void addViewToTile(View v, int tileValue) {
        int tilesTotalViews = this.tiles.get(tileValue).numViews();
        if(tilesTotalViews == 0)
            this.tiles.get(tileValue).addView(v, 0);
        else if(tilesTotalViews == 1)
            this.tiles.get(tileValue).addView(v, 1);
        else
            this.tiles.get(tileValue).addView(v, tilesTotalViews - 1);
    }

    /**
     * adds the data to the classifier table
     * @param data
     */
    public void addDataSetToClass(GraphableData data){
        this.dataSetPanel.addDataSetToTable(data);
    }
}
