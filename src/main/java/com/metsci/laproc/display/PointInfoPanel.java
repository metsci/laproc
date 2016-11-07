package com.metsci.laproc.display;

import com.metsci.laproc.plotting.GraphPoint;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Panel with general data stored in a data point.
 *
 * Created by porterjc on 10/21/2016.
 */
public class PointInfoPanel extends JPanel{
    private JScrollPane pane;
    /**
     * Basic constructor for the PointInfoPanel
     */
    public PointInfoPanel() {
        setName("Point Analytics");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        pane = new JScrollPane();
        this.add(pane);
    }

    /**
     * updates the rows of the point info panel
     *
     * @param point
     */
    public void update(GraphPoint point){
        Map<String, Double> data = point.getAnalytics();

        JPanel panel = new JPanel();
        GridLayout matri = new GridLayout(data.size(), 2);
        panel.setLayout(matri);
        for(String key : data.keySet()) {
            panel.add(new JLabel(key));
            panel.add(new JLabel(Math.floor(data.get(key) * 10000) / 10000 + ""));
        }

        this.remove(pane);
        pane = new JScrollPane(panel);

        this.add(pane);
        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
