package com.metsci.laproc.display;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying a confusion matrix
 *
 * Created by porterjc on 10/14/2016.
 */
public class ConfusionPanel extends JPanel {
    private JPanel panel;

    /**
     * Basic constructor  for the confusion matrix panel
     *
     */
    public ConfusionPanel(){
        GridLayout matri = new GridLayout(3, 3);
        this.setName("Confusion Matrix");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        JScrollPane pane = new JScrollPane(panel);
        this.add(pane);
    }

    /**
     * Updates the columns and rows of the confusion matrix
     *
     * @param positives
     * @param negatives
     */
    public void updateConfusionMatrix(double[] positives, double[] negatives) {
        JLabel temp = (JLabel) panel.getComponent(4);
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
        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
