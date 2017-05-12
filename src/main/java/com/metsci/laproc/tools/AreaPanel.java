package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.utils.IActionReceiver;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

/**
 * Tool used for analysis of the area under graphable datas
 *
 * Created by porterjc on 5/2/2017.
 */
public class AreaPanel implements ITool, IActionReceiver<Map<String, List<GraphPoint>>> {
    private JScrollPane pane;
    private JPanel panel;

    public AreaPanel() {
        panel = new JPanel();
        panel.setName("Graph Areas");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pane = new JScrollPane();
        panel.add(pane);
    }

    /**
     * Updates the rows of the Area Panel for each displayed graph
     * @param dataMap data map from the graphable data set containing a String mapped to a map of doubles
     */
    public void update(Map<String, List<GraphPoint>> dataMap){
        this.panel.remove(pane);
        pane = new JScrollPane();
        JPanel supPanel = new JPanel();

        double area = 0;
        double lastX = -1;
        double lastY = -1;

        GridLayout matri = new GridLayout(dataMap.size(), 2);

        //For each name in the map, display the name and the area
        for(String name : dataMap.keySet()) {
            List<GraphPoint> xyPairs = dataMap.get(name);

            for (GraphPoint p : xyPairs) {
                if (lastX == -1 && lastY == -1) {
                    lastX = p.getX();
                    lastY = p.getY();
                } else {
                    area += Math.abs(p.getX() - lastX) * ((p.getY() + lastY) / 2);
                    lastX = p.getX();
                    lastY = p.getY();
                }
            }
            JPanel panel = new JPanel();

            panel.setLayout(matri);
            panel.add(new JLabel(name + ": "));
            String areaString = String.format("%1$f", area);
            panel.add(new JLabel(areaString + ""));

            supPanel.add(panel);
            lastX = -1;
            lastY = -1;
            area = 0;
        }

        pane.add(supPanel);
        pane.setViewportView(supPanel);
        this.panel.add(pane);
        this.panel.revalidate();
        this.panel.repaint();
    }

    public void respondToAction(Map<String, List<GraphPoint>> argument) {
        update(argument);
    }

    public View getView() {
        return new View("Area", this.panel, "Area", false);
    }

    public int getDefaultPosition() {
        return ITool.BOTTOMPOSITION;
    }
}
