package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.pointmetrics.ParametricFunction;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel implements ITool, Observer{
    private JPanel panel;
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, ParametricFunction> metricsMap;
    private JButton updateButton;
    private Graph graph;


    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel(Graph graph) {
        this.panel = new JPanel();
        this.graph = graph;
        this.metricsMap = new HashMap<String, ParametricFunction>();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.xaxis = new JComboBox();
        this.xaxis.setName("X-Axis");
        this.yaxis = new JComboBox();
        this.yaxis.setName("Y-Axis");
        this.xaxis.setMaximumRowCount(6);
        this.yaxis.setMaximumRowCount(6);
        this.xaxis.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        this.yaxis.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));

        this.panel.add(new JLabel("X-Axis"));
        this.panel.add(xaxis);
        this.panel.add(new JLabel("Y-Axis"));
        this.panel.add(yaxis);

        this.updateButton = new JButton("Update");
        this.panel.add(updateButton);
    }

    /**
     * Updates the combo boxes with the metrics from the graph
     */
    public void populateOptions() {
       Iterable<ParametricFunction> metrics = graph.getAxisFunctions();

        if(updateButton.getActionListeners() != null) {
            this.updateButton.removeAll();
        }

        UpdateAxesActionListener listener = new UpdateAxesActionListener(graph, this);
        this.updateButton.addActionListener(listener);

        Iterator<ParametricFunction> metricIterator = metrics.iterator();
        while(metricIterator.hasNext()) {
            ParametricFunction temp = metricIterator.next();
            if(!metricsMap.containsKey(temp.getDescriptor())) {
                this.xaxis.addItem(temp.getDescriptor());
                this.yaxis.addItem(temp.getDescriptor());
            }
            this.metricsMap.put(temp.getDescriptor(), temp);
        }

        this.xaxis.revalidate();
        this.yaxis.revalidate();
        this.xaxis.repaint();
        this.yaxis.repaint();
        this.panel.revalidate();
        this.panel.repaint();
    }

    /**
     * Gets the appropriate matric based on the currently selected item
     * @return
     */
    public ParametricFunction getSelectedXAxis() {
        return this.metricsMap.get(this.xaxis.getSelectedItem());
    }

    /**
     * Gets the appropriate matric based on the currently selected item
     * @return
     */
    public ParametricFunction getSelectedYAxis() {
        return this.metricsMap.get(this.yaxis.getSelectedItem());
    }

    public void initialize() {
        this.populateOptions();
    }

    public View getView() {
        return new View("Options", this.panel, "Options", true);
    }

    public void addAction() {

    }

    public void update(Observable o, Object arg) {

    }
}
