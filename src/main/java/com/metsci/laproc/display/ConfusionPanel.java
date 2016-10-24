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
        getComponent(4).setName(positives[0]+ "");
        getComponent(4).repaint();
        getComponent(5).setName(negatives[0] + "");
        getComponent(4).repaint();
        getComponent(7).setName(positives[1]+ "");
        getComponent(4).repaint();
        getComponent(8).setName(negatives[1]+ "");
        getComponent(4).repaint();
        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
