package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.*;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.uicomponents.ParametrizedCheckBox;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.pointmetrics.ParametricFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel implements ITool, DataObserver{
    private JPanel panel;
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, ParametricFunction> metricsMap;
    private JButton updateButton;
    private IAction action;
    private IAction<CompositeFunction> addCompositeFunctionAction;


    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel(DataReference reference) {
        reference.addObserver(this);
        this.panel = new JPanel();
        this.action = new UpdateAxesAction(reference);
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

        this.addCompositeFunctionAction = new AddCompositeFunctionAction(reference);
        setupCompositeFunctionOptions();
    }

    /**
     * Updates the combo boxes with the metrics from the graph
     */
    public void populateOptions(Graph graph) {
       Iterable<ParametricFunction> metrics = graph.getAxisFunctions();

        if(updateButton.getActionListeners() != null) {
            this.updateButton.removeAll();
        }

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ParametricFunction[] axes = new ParametricFunction[2];
                axes[0] = getSelectedXAxis();
                axes[1] = getSelectedYAxis();
                action.doAction(axes);
            }
        };
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

    private void setupCompositeFunctionOptions() {
        addCheckBox(new VerticalAverageFunction(), "Display Vertical Average");
        addCheckBox(new VarianceFunction(), "Display Variance");
        addCheckBox(new StandardDeviationFunction(), "Display Standard Deviation");
    }

    private void addCheckBox(CompositeFunction function, String text) {
        ParametrizedCheckBox<CompositeFunction> checkBox = new ParametrizedCheckBox<CompositeFunction>(text, function);
        checkBox.addActionWhenChecked(addCompositeFunctionAction);
        this.panel.add(checkBox);
    }

    public View getView() {
        return new View("Options", this.panel, "Options", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(DataReference graphReference) {
        populateOptions(graphReference.getGraph());
    }
}
