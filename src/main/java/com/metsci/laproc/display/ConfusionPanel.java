package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.utils.IActionReceiver;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying a confusion matrix
 *
 * Created by porterjc on 10/14/2016.
 */
public class ConfusionPanel implements ITool, IActionReceiver<GraphPoint[]> {
    private JPanel panel;
    private JScrollPane pane;

    /**
     * Basic constructor  for the confusion matrix panel
     *
     */
    public ConfusionPanel(){
        GridLayout matri = new GridLayout(3, 3);
        panel = new JPanel();
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
        //this.add(pane);
    }

    /**
     * Updates the columns and rows of the confusion matrix
     */
    public void updateConfusionMatrix(GraphPoint[] points) {
        //TODO Rework to support new function of having matrix per displayed data set.
        /*JLabel temp = (JLabel) panel.getComponent(4);
        temp.setText(positives[0]+ "");
        temp.repaint();
        temp = (JLabel) panel.getComponent(5);
        temp.setText(negatives[0] + "");
        temp.repaint();
        temp = (JLabel) panel.getComponent(7);
        temp.setText(positives[1]+ "");
        temp.repaint();
        temp = (JLabel) panel.getComponent(8);
        temp.setText(negatives[1]+ "");
        temp.repaint();
        pane.revalidate();
        pane.repaint();
        pane.getParent().repaint();*/
    }

    public View getView() {
        return new View("Confusion Matrices", this.pane, "Confusion Matrices", true);
    }

    public int getDefaultPosition() {
        return ITool.BOTTOMPOSITION;
    }

    public void respondToAction(GraphPoint[] points) {
        updateConfusionMatrix(points);
    }
}
