package com.metsci.laproc.display;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * Created by porterjc on 10/14/2016.
 */
public class ConfusionPanel extends JPanel {

    public ConfusionPanel(){
        GridLayout matri = new GridLayout(3, 3);
        this.setName("Confusion Matrix");
        this.setLayout(matri);
        this.add(new JLabel(""));
        this.add(new JLabel("Predicted Positives"));
        this.add(new JLabel("Predicted Negatives"));
        this.add(new JLabel("True Positives"));
        this.add(new JLabel(0 + ""));
        this.add(new JLabel(0 + ""));
        this.add(new JLabel("True Negatives"));
        this.add(new JLabel(0 + ""));
        this.add(new JLabel(0 + ""));
    }

    public void updateConfusionMatrix(double[] positives, double[] negatives) {
        JLabel temp = (JLabel) getComponent(4);
        temp.setText(positives[0]+ "");
        temp.repaint();
        temp = (JLabel) getComponent(5);
        temp.setText(negatives[0] + "");
        temp.repaint();
        temp = (JLabel) getComponent(7);
        temp.setText(positives[1]+ "");
        temp.repaint();
        temp = (JLabel) getComponent(8);
        temp.setText(negatives[1]+ "");
        temp.repaint();
        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
