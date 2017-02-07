package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.*;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.uicomponents.ParametrizedCheckBox;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.uicomponents.GraphExporter;
import com.metsci.laproc.uicomponents.graphfeatures.CompositeFunctionDrawer;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 * Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel implements ITool, IObserver<OutputDataReference>{
    private JPanel panel;
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, ParametricFunction> metricsMap;
    private JButton updateButton;
    private IAction updateAxesAction;
    private GraphDisplayManager manager;

    private IAction<GraphFeature> addGraphFeatureAction;
    private IAction<GraphFeature> removeGraphFeatureAction;

    /**
     * Default constructor for Graphoptions Panel
     * Created by porterjc on 10/26/2016.
     */
    public GraphOptionsPanel(final OutputDataReference reference, GraphDisplayManager displayManager) {
        manager = displayManager;
        reference.addObserver(this);
        this.panel = new JPanel();
        this.updateAxesAction = new UpdateAxesAction(reference);
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

        this.addGraphFeatureAction = new AddGraphFeatureAction(manager);
        this.removeGraphFeatureAction = new RemoveGraphFeatureAction(manager);
        setupCompositeFunctionOptions();
        JButton exportButton = new JButton("Export Graph");
        final JTextField exportTextField = new JTextField();
        exportTextField.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GraphExporter.exportGraph(exportTextField.getText() + ".png", reference.createGraph());
                } catch (IOException exe){
                    exe.printStackTrace();
                }
            }
        });
        this.panel.add(exportTextField);
        this.panel.add(exportButton);
    }

    /**
     * Updates the combo boxes with the metrics from the graph
     */
    public void populateOptions(Iterable<ParametricFunction> metrics) {

        if(updateButton.getActionListeners() != null) {
            this.updateButton.removeAll();
        }

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ParametricFunction[] axes = new ParametricFunction[2];
                axes[0] = getSelectedXAxis();
                axes[1] = getSelectedYAxis();
                updateAxesAction.doAction(axes);

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
     * @return the selected x axis
     */
    public ParametricFunction getSelectedXAxis() {
        return this.metricsMap.get(this.xaxis.getSelectedItem());
    }

    /**
     * Gets the appropriate matric based on the currently selected item
     * @return the selected y axis
     */
    public ParametricFunction getSelectedYAxis() {
        return this.metricsMap.get(this.yaxis.getSelectedItem());
    }

    /**
     * Sets up the check boxes for the composite funtions
     */
    private void setupCompositeFunctionOptions() {
        addCheckBox(new CompositeFunctionDrawer(new VerticalAverageFunction()), "Display Vertical Average");
        addCheckBox(new CompositeFunctionDrawer(new VarianceFunction()), "Display Variance");
        addCheckBox(new CompositeFunctionDrawer(new StandardDeviationFunction()), "Display Standard Deviation");
    }

    /**
     * Adds a checkbox with the given name and function
     * @param feature The feature associated with this checkbox
     * @param text Text the checkbox displays
     */
    private void addCheckBox(GraphFeature feature, String text) {
        ParametrizedCheckBox<GraphFeature> checkBox = new ParametrizedCheckBox<GraphFeature>(text, feature);
        checkBox.addActionWhenChecked(addGraphFeatureAction);
        checkBox.addActionWhenUnchecked(removeGraphFeatureAction);
        this.panel.add(checkBox);
    }

    public View getView() {
        return new View("Options", this.panel, "Options", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(OutputDataReference graphReference) {
        populateOptions(graphReference.getAxisFunctions());
    }
}
