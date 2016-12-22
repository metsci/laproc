package com.metsci.laproc.uicomponents;

import com.metsci.glimpse.docking.*;
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
    private Tile analyticsTile;
    private Tile spreadTile;
    private Tile graphTile;

    public Window(){
        tiles = new ArrayList<Tile>();

        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);
        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

        this.frame = group.addNewFrame();

        graphTile = tileFactory.newTile();
        tiles.add(graphTile);

        spreadTile = tileFactory.newTile();
        tiles.add(spreadTile);

        analyticsTile = tileFactory.newTile();
        tiles.add(analyticsTile);
    }

    /**
     * Puts together a docking group and docks in default views
     * Creaded by porterjc on 9/22/2016
     */
    public void display() {
        JPanel keyPanel = new JPanel();

        keyPanel.setBackground(Color.GREEN);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.docker = frame.docker;

        docker.addInitialLeaf(graphTile);
        docker.addNeighborLeaf(analyticsTile, graphTile, Side.BOTTOM, 0.2);
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
}
