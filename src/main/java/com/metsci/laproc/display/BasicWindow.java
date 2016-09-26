package com.metsci.laproc.display;

import com.jogamp.opengl.util.FPSAnimator;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.glimpse.docking.*;
import com.metsci.laproc.ROCCurvePlot;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Curve;
import com.metsci.laproc.plotting.Graph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by porterjc on 9/22/2016.
 */
public class BasicWindow implements Window{
    private NewtSwingGlimpseCanvas canvas;

    public void display(Graph graph) {
       // MultiSplitPane pane = new MultiSplitPane(50);

        DockingGroup group = new DockingGroup(DockingThemes.defaultDockingTheme, DockingGroup.DockingFrameCloseOperation.DISPOSE_CLOSED_FRAME);

        TileFactories.TileFactory tileFactory = new TileFactories.TileFactoryStandard(group);

//        JFrame frame = new DockingFrame();
//        frame.setSize(800, 800);
//        frame.setVisible( true );
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JTabbedPane tabbedPane = new JTabbedPane();
        //frame.add(tabbedPane);
        //pane.addInitialLeaf(tabbedPane);

        JPanel spreadpanel = new JPanel();
        JPanel graphpanel = new JPanel();
        JPanel keyPanel = new JPanel();

        spreadpanel.setBackground(Color.blue);
        graphpanel.setBackground(Color.gray);
        keyPanel.setBackground(Color.GREEN);

        DockingFrame frame = group.addNewFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvas = showGraph(graph);

        View sView = new View("Data", spreadpanel, "Data");
        View gView = new View("Graph", canvas, "Graph");
        View kView = new View("WIP", keyPanel, "WIP");

        Tile spreadTile = tileFactory.newTile();
        spreadTile.addView(sView, 0);

        Tile graphTile = tileFactory.newTile();
        graphTile.addView(gView, 0);

        Tile keyTile = tileFactory.newTile();
        keyTile.addView(kView, 0);

        MultiSplitPane docker = frame.docker;

        docker.addInitialLeaf(graphTile);
        docker.addNeighborLeaf(spreadTile, graphTile, Side.LEFT, 0.3);
        docker.addEdgeLeaf(keyTile, Side.BOTTOM, 0.3);

        //frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

//
        new FPSAnimator(canvas.getGLDrawable(), 120).start();
//        tabbedPane.add("ROCCurvePlot",canvas);
    }

    public NewtSwingGlimpseCanvas showGraph(Graph graph) {
        NewtSwingGlimpseCanvas canv = new NewtSwingGlimpseCanvas();

        canv.addLayout(new ROCCurvePlot(graph).getLayout());
        return canv;
    }

    public void showSpreadsheet() {

    }
}
