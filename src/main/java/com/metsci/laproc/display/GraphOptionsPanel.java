package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;
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
public class GraphOptionsPanel implements ITool, Observer{
    private JPanel panel;
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, Metric> metricsMap;
    private JButton updateButton;
    private Window window;
    private GraphableData data;

    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel(Window window, GraphableData data) {
        this.data = data;
        this.panel = new JPanel();
        this.window = window;
        this.metricsMap = new HashMap<String, Metric>();
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
     * Updates the combo boxes with the metrics from graphable datas
     */
    public void populateOptions() {
        List<Metric> metrics = data.getAxes();

        if(updateButton.getActionListeners() != null) {
            this.updateButton.removeAll();
        }

        UpdateAxesActionListener listener = new UpdateAxesActionListener(data, this, window);
        this.updateButton.addActionListener(listener);

        Iterator<Metric> metricIterator = metrics.iterator();
        while(metricIterator.hasNext()) {
            Metric temp = metricIterator.next();
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

    public void initialize() {
        populateOptions();
    }

    public View getView() {
        return new View("Options", this.panel, "Options", true);
    }

    public void update(Observable o, Object arg) {
        populateOptions();
    }
}
