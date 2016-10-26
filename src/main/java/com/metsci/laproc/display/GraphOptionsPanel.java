package com.metsci.laproc.display;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.Metric;

import javax.swing.*;
import java.util.List;
import java.util.Iterator;

/**
 * Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel extends JPanel{
    private JComboBox xaxis;
    private JComboBox yaxis;


    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.xaxis = new JComboBox();
        this.yaxis = new JComboBox();
        this.xaxis.setMaximumRowCount(6);
        this.yaxis.setMaximumRowCount(6);

        this.add(xaxis);
        this.add(yaxis);

        JButton displaySetButton = new JButton("Set new Axes");

        this.add(displaySetButton);
    }

    public void populateOptions(Graph graph) {
        Iterator<GraphableData> data = graph.getData().iterator();
        List<Metric> metrics = data.next().getAnalytics();

        Iterator<Metric> metricIterator = metrics.iterator();
        while(metricIterator.hasNext()) {
            this.xaxis.addItem(metricIterator.next().getDescriptor());
            this.yaxis.addItem(metricIterator.next().getDescriptor());
        }

        this.xaxis.revalidate();
        this.yaxis.revalidate();
        this.xaxis.repaint();
        this.yaxis.repaint();
        this.revalidate();
        this.repaint();
    }
}
