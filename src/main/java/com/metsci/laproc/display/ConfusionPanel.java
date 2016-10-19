package com.metsci.laproc.display;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * Created by porterjc on 10/14/2016.
 */
public class ConfusionPanel extends JPanel {

    public ConfusionPanel(int[] positives, int[] negatives){
        GridLayout matri = new GridLayout(3, 3);
        this.setName("Confusion Matrix");
        this.setLayout(matri);
        this.add(new JLabel(""));
        this.add(new JLabel("Predicted Positives"));
        this.add(new JLabel("Predicted Negatives"));
        this.add(new JLabel("True Positives"));
        this.add(new JLabel(positives[0] + ""));
        this.add(new JLabel(negatives[0] + ""));
        this.add(new JLabel("True Negatives"));
        this.add(new JLabel(positives[1] + ""));
        this.add(new JLabel(negatives[1] + ""));
    }

    public void updateConfusionMatrix(int[] positives, int[] negatives) {
        getComponent(4).setName(positives[0]+ "");
        getComponent(5).setName(negatives[0] + "");
        getComponent(7).setName(positives[1]+ "");
        getComponent(8).setName(negatives[1]+ "");
        repaint();
    }
}
