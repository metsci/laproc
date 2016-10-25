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
public class PointInfoPanel extends JLabel{

    /**
     * Basic constructor for the PointInfoPanel
     */
    public PointInfoPanel() {
        GridLayout matri = new GridLayout(7, 2);
        this.setName("Point Analytics");
        this.setLayout(matri);
        this.add(new JLabel("Classifier Score"));
        this.add(new JLabel(""));
        this.add(new JLabel("True Positive Rate"));
        this.add(new JLabel(""));
        this.add(new JLabel("False Positive Rate"));
        this.add(new JLabel(""));
        this.add(new JLabel("True Negative Rate"));
        this.add(new JLabel(""));
        this.add(new JLabel("False Negative Rate"));
        this.add(new JLabel(""));
        this.add(new JLabel("Cutpoint"));
        this.add(new JLabel(""));
        this.add(new JLabel("Accuracy"));
        this.add(new JLabel(""));
    }

    /**
     * updates the rows of the point info panel
     *
     * @param point
     */
    public void update(GraphPoint point){
        //TODO fix
       /* Map <String, Double> data = point;
        int i = 1;
        for(String key : point.keySet()) {
            if(i > this.getComponentCount())
                break;
            JLabel temp = (JLabel) this.getComponent(i);
            temp.setText(data.get(key) + "");
            temp.repaint();
            i += 2;
        }*/

        this.revalidate();
        this.repaint();
        this.getParent().repaint();
    }
}
