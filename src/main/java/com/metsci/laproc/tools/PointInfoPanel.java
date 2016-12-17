package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.utils.IActionReceiver;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Panel with general data stored in a data point.
 *
 * Created by porterjc on 10/21/2016.
 */
public class PointInfoPanel implements ITool, IActionReceiver<GraphPoint[]>{
    private JScrollPane pane;
    private JPanel panel;

    /**
     * Basic constructor for the PointInfoPanel
     */
    public PointInfoPanel() {
        panel = new JPanel();
        panel.setName("Point Analytics");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pane = new JScrollPane();
        panel.add(pane);
    }

    /**
     * updates the rows of the point info panel
     */
    public void update(GraphPoint[] points){
        Map<String, Double> data = points[0].getAnalytics();

        JPanel panel = new JPanel();
        GridLayout matri = new GridLayout(data.size(), 2);
        panel.setLayout(matri);
        for(String key : data.keySet()) {
            panel.add(new JLabel(key));
            panel.add(new JLabel(Math.floor(data.get(key) * 10000) / 10000 + ""));
        }

        this.panel.remove(pane);
        pane = new JScrollPane(panel);

        this.panel.add(pane);
        this.panel.revalidate();
        this.panel.repaint();
        this.panel.getParent().repaint();
    }

    public View getView() {
        return new View("PointInfo", this.panel, "PointInfo", true);
    }

    public int getDefaultPosition() {
        return ITool.BOTTOMPOSITION;
    }

    public void respondToAction(GraphPoint[] points) {
        update(points);
    }
}
