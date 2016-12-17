package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.ActionHandlers.CreateGraphAction;
import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObservable;
import com.metsci.laproc.utils.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel that handles interacting with the created data sets
 * Created by malinocr on 10/17/2016.
 */
public class DataSetPanel implements ITool, IObserver {
    private JPanel panel;
    private DataSetTable table;
    private DataReference reference;

    /**
     * Default constructor for the DataSetPanel
     */
    public DataSetPanel(DataReference ref){
        this.panel = new JPanel();
        this.reference = ref;
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
                new CreateGraphAction(table, reference).doAction();
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

    public void update(IObservable object) {

    }

    public void initialize() {

    }

    public View getView() {
        return new View("Data Set", this.panel, "Data Set", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }
}
