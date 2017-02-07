package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.*;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.uicomponents.ParametrizedCheckBox;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.uicomponents.GraphExporter;
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

    private IAction<CompositeFunction> addCompositeFunctionAction;
    private IAction<CompositeFunction> removeCompositeFunctionAction;

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

        addJLabel("X-Axis");

        this.xaxis = createJComboBox("X-Axis");
        this.panel.add(xaxis);

        addJLabel("Y-Axis");

        this.yaxis = createJComboBox("Y-Axis");
        this.panel.add(yaxis);

        this.addCompositeFunctionAction = new AddCompositeFunctionAction(manager);
        this.removeCompositeFunctionAction = new RemoveCompositeFunctionAction(manager);
        setupCompositeFunctionOptions();

        this.updateButton = new JButton("Update");
        this.updateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(updateButton);

        addJLabel("Export Graph File Path:");

        final JTextField exportTextField = new JTextField();
        exportTextField.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        exportTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(exportTextField);

        JButton exportButton = new JButton("Export Graph");
        exportButton.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        this.xaxis.repaint();
        this.yaxis.revalidate();
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

    public View getView() {
        return new View("Options", this.panel, "Options", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(OutputDataReference graphReference) {
        populateOptions(graphReference.getAxisFunctions());
    }

    /**
     * Sets up the check boxes for the composite funtions
     */
    private void setupCompositeFunctionOptions() {
        addCheckBox(new VerticalAverageFunction(), "Display Vertical Average");
        addCheckBox(new VarianceFunction(), "Display Variance");
        addCheckBox(new StandardDeviationFunction(), "Display Standard Deviation");
    }

    /**
     * Adds a checkbox with the given name and function
     * @param function function of the checkbox
     * @param text text the checkbox displays
     */
    private void addCheckBox(CompositeFunction function, String text) {
        ParametrizedCheckBox<CompositeFunction> checkBox = new ParametrizedCheckBox<CompositeFunction>(text, function);
        checkBox.addActionWhenChecked(addCompositeFunctionAction);
        checkBox.addActionWhenUnchecked(removeCompositeFunctionAction);
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
        return box;
    }

    /**
     * Adds a JLabel with a given text to the panel
     * @param text text of the lable
     */
    private void addJLabel(String text){
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(label);
    }
}
