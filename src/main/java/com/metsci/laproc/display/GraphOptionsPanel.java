package com.metsci.laproc.display;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.Metric;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel extends JPanel{
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, Metric> metricsMap;
    private JButton updateButton;
    private Graph graph;
    private Window window;


    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel(Window window, Graph graph) {
        this.window = window;
        this.graph = graph;
        this.metricsMap = new HashMap<String, Metric>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.xaxis = new JComboBox();
        this.xaxis.setName("X-Axis");
        this.yaxis = new JComboBox();
        this.yaxis.setName("Y-Axis");
        this.xaxis.setMaximumRowCount(6);
        this.yaxis.setMaximumRowCount(6);
        this.xaxis.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        this.yaxis.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));

        this.add(new JLabel("X-Axis"));
        this.add(xaxis);
        this.add(new JLabel("Y-Axis"));
        this.add(yaxis);

        this.updateButton = new JButton("Update");
        this.add(updateButton);
    }

    /**
     * Updates the combo boxes with the metrics from graphable datas
     * @param graph
     */
    public void populateOptions(Graph graph) {
        Iterator<GraphableData> data = graph.getData().iterator();
        GraphableData selected = data.next();
        List<Metric> metrics = selected.getAxes();

        UpdateAxesActionListener listener = new UpdateAxesActionListener(selected, this, window, graph);
        this.updateButton.addActionListener(listener);

        Iterator<Metric> metricIterator = metrics.iterator();
        while(metricIterator.hasNext()) {
            Metric temp = metricIterator.next();
            this.metricsMap.put(temp.getDescriptor(), temp);
            this.xaxis.addItem(temp.getDescriptor());
            this.yaxis.addItem(temp.getDescriptor());
        }

        this.xaxis.revalidate();
        this.yaxis.revalidate();
        this.xaxis.repaint();
        this.yaxis.repaint();
        this.revalidate();
        this.repaint();
    }

    /**
     * Gets the appropriate matric based on the currently selected item
     * @return
     */
    public Metric getSelectedXAxis() {
        return this.metricsMap.get(this.xaxis.getSelectedItem());
    }

    /**
     * Gets the appropriate matric based on the currently selected item
     * @return
     */
    public Metric getSelectedYAxis() {
        return this.metricsMap.get(this.yaxis.getSelectedItem());
    }
}
