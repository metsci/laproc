package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.utils.IActionReceiver;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Example panel with general data stored in a data point.
 *
 * Created by porterjc on 10/21/2016.
 */
public class PointInfoPanel implements ITool, IActionReceiver<Map<String, GraphPoint>>{
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
     * Updates the rows of the point info panel for each displayed graph
     * @param dataMap data map from the graphable data set containing a point to the point
     */
    public void update(Map<String, GraphPoint> dataMap){
        this.panel.remove(pane);
        pane = new JScrollPane();
        JPanel supPanel = new JPanel();

        //For each name in the map, display the name and the analytics
        for(String name : dataMap.keySet()) {
            Map<String, Double> data = dataMap.get(name).getAnalytics();

            JPanel panel = new JPanel();
            GridLayout matri = new GridLayout(data.size() + 1, 2);
            panel.setLayout(matri);

            panel.add(new JLabel(name));
            panel.add(new JLabel(""));
            for (String key : data.keySet()) {
                panel.add(new JLabel(key));
                panel.add(new JLabel(Math.floor(data.get(key) * 10000) / 10000 + ""));
            }

            supPanel.add(panel);
        }

        pane.add(supPanel);
        pane.setViewportView(supPanel);
        this.panel.add(pane);
        this.panel.revalidate();
        this.panel.repaint();
    }

    public View getView() {
        return new View("Point Info", this.panel, "Point Info", false);
    }

    public int getDefaultPosition() {
        return ITool.BOTTOMPOSITION;
    }

    public void respondToAction(Map<String, GraphPoint> points) {
        update(points);
    }
}
