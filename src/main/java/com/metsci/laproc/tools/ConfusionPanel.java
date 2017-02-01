package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.pointmetrics.FalseNegatives;
import com.metsci.laproc.utils.IActionReceiver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Panel for displaying a confusion matrix
 *
 * Created by porterjc on 10/14/2016.
 */
public class ConfusionPanel implements ITool, IActionReceiver<GraphPoint[]> {
    private JPanel masterPanel;
    private JScrollPane pane;

    /**
     * Basic constructor  for the confusion matrix panel
     *
     */
    public ConfusionPanel(){
        masterPanel = new JPanel();
        masterPanel.setName("Confusion Matrix");
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        GridLayout matri = new GridLayout(3, 3);
        JPanel panel = new JPanel();
        panel.setName("Confusion Matrix");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel = new JPanel();
        panel.setLayout(matri);

        panel.add(new JLabel(""));
        panel.add(new JLabel("Predicted Positives"));
        panel.add(new JLabel("Predicted Negatives"));
        panel.add(new JLabel("True Positives"));
        panel.add(new JLabel(0 + ""));
        panel.add(new JLabel(0 + ""));
        panel.add(new JLabel("True Negatives"));
        panel.add(new JLabel(0 + ""));
        panel.add(new JLabel(0 + ""));
        pane = new JScrollPane(panel);
        this.masterPanel.add(pane);
    }

    /**
     * Updates the columns and rows of the confusion matrix for each displayed graph
     */
    public void updateConfusionMatrix(GraphPoint[] points) {
        masterPanel.remove(pane);
        pane = new JScrollPane();
        JPanel supPanel = new JPanel();

        for(GraphPoint point : points) {
            Map<String, Double> data = point.getAnalytics();

            JPanel panel = new JPanel();
            GridLayout matri = new GridLayout(3, 3);
            panel.setLayout(matri);

            panel.add(new JLabel(""));
            panel.add(new JLabel("Predicted Positives"));
            panel.add(new JLabel("Predicted Negatives"));
            panel.add(new JLabel("True Positives"));
            panel.add(new JLabel(data.get("True Positives") + ""));
            panel.add(new JLabel(data.get("True Negatives") + ""));
            panel.add(new JLabel("True Negatives"));
            panel.add(new JLabel(data.get("False Positives") + ""));
            panel.add(new JLabel(data.get("False Negatives") + ""));

            supPanel.add(panel);
        }

        pane.add(supPanel);
        pane.setViewportView(supPanel);
        masterPanel.add(pane);
        masterPanel.revalidate();
        masterPanel.repaint();
    }

    /**
     * Returns a View that can be displayed in a Tile
     * @return
     */
    public View getView() {
        return new View("Confusion Matrices", this.masterPanel, "Confusion Matrices", true);
    }

    /**
     * Returns default position of a tile implementation
     * @return
     */
    public int getDefaultPosition() {
        return ITool.BOTTOMPOSITION;
    }

    /**
     * Responds to action that provides this object with an array of points by updating the panel
     * @param points
     */
    public void respondToAction(GraphPoint[] points) {
        updateConfusionMatrix(points);
    }
}
