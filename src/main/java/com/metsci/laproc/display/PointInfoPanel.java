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
    private JPanel panel;

    /**
     * Basic constructor for the PointInfoPanel
     */
    public PointInfoPanel() {
        GridLayout matri = new GridLayout(7, 2);
        this.panel = new JPanel();
        setName("Point Analytics");
        setLayout(matri);
        add(new JLabel("Classifier Score"));
        add(new JLabel(""));
        add(new JLabel("True Positive Rate"));
        add(new JLabel(""));
        add(new JLabel("False Positive Rate"));
        add(new JLabel(""));
        add(new JLabel("True Negative Rate"));
        add(new JLabel(""));
        add(new JLabel("False Negative Rate"));
        add(new JLabel(""));
        add(new JLabel("Cutpoint"));
        add(new JLabel(""));
        add(new JLabel("Accuracy"));
        add(new JLabel(""));
    }

    /**
     * updates the rows of the point info panel
     *
     * @param point
     */
    public void update(GraphPoint point){
        Map <String, Double> data = point;
        int i = 0;
        GridLayout matri = new GridLayout(data.size(), 2);
        this.setLayout(matri);
        for(String key : point.keySet()) {
            if(i >= this.getComponentCount()){
                this.add(new JLabel(key));
                this.add(new JLabel(data.get(key) + ""));
                i += 2;
            } else {
                JLabel temp = (JLabel) this.getComponent(i);
                temp.setText(key);
                temp.repaint();
                temp = (JLabel) this.getComponent(i + 1);
                temp.setText(data.get(key) + "");
                temp.repaint();
                i += 2;
            }
        }

        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
