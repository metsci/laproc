package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.*;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.uicomponents.graphfeatures.AverageDrawer;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import com.metsci.laproc.uicomponents.graphfeatures.VarianceDrawer;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.*;

/**
 * Example Panel for selecting graph options
 * Created by porterjc on 10/26/2016.
 */
public class GraphOptionsPanel implements ITool, IObserver<OutputDataReference>{
    private JPanel panel;
    private JComboBox xaxis;
    private JComboBox yaxis;
    private Map<String, ParametricFunction> metricsMap;
    private GraphDisplayManager manager;
    private OutputDataReference reference;

    private IAction<GraphFeature> addGraphFeatureAction;
    private IAction<GraphFeature> removeGraphFeatureAction;

    private boolean listenersEnabled = false;

    /**
     * Default constructor
     * @param reference output data reference for the application
     * @param displayManager display manager for the application
     */
    public GraphOptionsPanel(final OutputDataReference reference, GraphDisplayManager displayManager) {
        //Initialize fields for the tool
        this.reference = reference;
        manager = displayManager;
        reference.addObserver(this);
        this.panel = new JPanel();
        this.metricsMap = new HashMap<String, ParametricFunction>();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        //Create a combo box for the x axis
        addJLabel("X-Axis");
        this.xaxis = createJComboBox("X-Axis");
        this.panel.add(xaxis);

        //Create a combo box for the y axis
        addJLabel("Y-Axis");
        this.yaxis = createJComboBox("Y-Axis");
        this.panel.add(yaxis);

        this.addGraphFeatureAction = new AddGraphFeatureAction(manager);
        this.removeGraphFeatureAction = new RemoveGraphFeatureAction(manager);

        setupCompositeFunctionOptions();

        //Add text field for exporting the graph
        addJLabel("Export Graph File Path:");
        final JTextField exportTextField = new JTextField();
        exportTextField.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        exportTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(exportTextField);

        //Add button to submit a request to update the graph
        JButton exportButton = new JButton("Export Graph");
        exportButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        //Clicking the button causes the graph exporter to export the graph to an image using the text field as
        //a file path.
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GraphExporter.exportGraph(exportTextField.getText() + ".png", reference.createGraph());
                } catch (IOException exe){
                    exe.printStackTrace();
                }
            }
        });
        this.panel.add(exportButton);
        this.listenersEnabled = true;
    }

    /**
     * Updates the combo boxes with the metrics from the graph
     * @param  metrics current metrics from graph
     */
    private void populateOptions(Iterable<ParametricFunction> metrics,
                                ParametricFunction selectedX, ParametricFunction selectedY) {
        //Add all the new metrics for axes to the metrics map
        listenersEnabled = false;
        Iterator<ParametricFunction> metricIterator = metrics.iterator();
        while(metricIterator.hasNext()) {
            ParametricFunction temp = metricIterator.next();
            if(!metricsMap.containsKey(temp.getDescriptor())) {
                this.xaxis.addItem(temp.getDescriptor());
                this.yaxis.addItem(temp.getDescriptor());
            }
            this.metricsMap.put(temp.getDescriptor(), temp);
        }

        this.xaxis.setSelectedItem(selectedX.getDescriptor());
        this.yaxis.setSelectedItem(selectedY.getDescriptor());

        this.xaxis.revalidate();
        this.xaxis.repaint();
        this.yaxis.revalidate();
        this.yaxis.repaint();
        this.panel.revalidate();
        this.panel.repaint();
        listenersEnabled = true;
    }

    /**
     * Gets the appropriate metric based on the currently selected item
     * @return the selected x axis
     */
    public ParametricFunction getSelectedXAxis() {
        return this.metricsMap.get(this.xaxis.getSelectedItem());
    }

    /**
     * Gets the appropriate metric based on the currently selected item
     * @return the selected y axis
     */
    public ParametricFunction getSelectedYAxis() {
        return this.metricsMap.get(this.yaxis.getSelectedItem());
    }

    public View getView() {
        return new View("Options", this.panel, "Options", false);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(OutputDataReference graphReference) {
        populateOptions(graphReference.getAxisFunctions(), graphReference.getXAxisFunction(),
                graphReference.getYAxisFunction());
    }

    /**
     * Sets up the check boxes for the composite functions specific to this application
     */
    private void setupCompositeFunctionOptions() {
        addCheckBox(new AverageDrawer(), "Display Vertical Average");
        addCheckBox(new VarianceDrawer(), "Display Variance");
    }

    /**
     * Adds a checkbox with the given name and function
     * @param feature The feature associated with this checkbox
     * @param text Text the checkbox displays
     */
    private void addCheckBox(final GraphFeature feature, String text) {
        final JCheckBox checkBox = new JCheckBox(text);
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(checkBox.isSelected()) {
                    addGraphFeatureAction.doAction(feature);
                } else {
                    removeGraphFeatureAction.doAction(feature);
                }
                updateGraph();
            }
        });
        this.panel.add(checkBox);
    }

    /**
     * Creates a combo box with default settings
     * @param name name of the combo box
     * @return combo box with default settings
     */
    private JComboBox createJComboBox(String name){
        JComboBox box = new JComboBox();
        box.setName(name);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setMaximumRowCount(6);
        box.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        box.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (listenersEnabled && e.getStateChange() == ItemEvent.SELECTED) {
                    updateGraph();
                }
            }
        });
        return box;
    }

    /**
     * Adds a JLabel with a given text to the panel
     * @param text text of the label
     */
    private void addJLabel(String text){
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(label);
    }

    /**
     * Calling this method whenever a change is made to the graph options will cause the graph to update
     */
    private void updateGraph() {
        reference.updateGraphWithAxes(getSelectedXAxis(), getSelectedYAxis());
    }

}
