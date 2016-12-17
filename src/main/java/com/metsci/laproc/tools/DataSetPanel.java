package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.action.CreateGraphAction;
import com.metsci.laproc.plotting.GraphableData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel that handles interacting with the created data sets
 * Created by malinocr on 10/17/2016.
 */
public class DataSetPanel implements ITool, DataObserver {
    private JPanel panel;
    private DataSetTable table;
    private DataReference reference;
    private IAction action;

    /**
     * Default constructor for the DataSetPanel
     */
    public DataSetPanel(DataReference ref){
        ref.addObserver(this);
        this.panel = new JPanel();
        this.reference = ref;
        this.action = new CreateGraphAction(reference);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.table = new DataSetTable();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton displaySetButton = new JButton("Display Set");

        displaySetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.doAction(table);
            }
        });
        buttonPanel.add(displaySetButton);

        JButton selectSetButton = new JButton("Select Set");
        buttonPanel.add(selectSetButton);

        this.panel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(table);
        this.panel.add(scrollPane);
    }

    /**
     * Clears the table in the panel
     */
    public void clearTable(){
        this.table.clear();
    }

    /**
     * Adds a data set to the table in the panel
     * @param data data set to be added
     */
    public void addDataSetToTable(GraphableData data){
        this.table.addDataSet(data);
    }

    public View getView() {
        return new View("Data Set", this.panel, "Data Set", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(DataReference reference) {
        Graph graph = reference.getGraph();
        for(GraphableData data : graph.getData()) {
            addDataSetToTable(data);
        }

    }
}
