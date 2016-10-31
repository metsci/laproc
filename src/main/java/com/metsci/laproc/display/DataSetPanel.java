package com.metsci.laproc.display;

import com.metsci.laproc.plotting.GraphableData;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that handles interacting with the created data sets
 * Created by malinocr on 10/17/2016.
 */
public class DataSetPanel extends JPanel {
    private Window window;
    private DataSetTable table;

    /**
     * Default constructor for the DataSetPanel
     * @param window the window that holds the DataSetPanel
     */
    public DataSetPanel(Window window){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.window = window;
        String[] columnNames = new String[1];
        columnNames[0] = "Data Set";
        this.table = new DataSetTable();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton displaySetButton = new JButton("Display Set");
        DisplayDataSetActionListener displayListener = new DisplayDataSetActionListener(this.window,this.table);
        displaySetButton.addActionListener(displayListener);
        buttonPanel.add(displaySetButton);

        JButton selectSetButton = new JButton("Select Set");
        SelectDataSetActionListener selectListenter = new SelectDataSetActionListener(this.window,this.table);
        selectSetButton.addActionListener(selectListenter);
        buttonPanel.add(selectSetButton);

        this.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }

    /**
     * Clears the table in the panel
     */
    public void clearTable(){
        this.table.clear();
    }

    /**
     * Adds a data set to the table in the panel
     * @param name name of the data set to be added
     * @param data data set to be added
     */
    public void addDataSetToTable(String name, GraphableData data){
        this.table.addDataSet(name, data);
    }
}
