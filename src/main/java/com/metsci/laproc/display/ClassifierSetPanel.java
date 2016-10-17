package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that displays all created classifer data sets
 * Created by malinocr on 10/17/2016.
 */
public class ClassifierSetPanel extends JPanel {
    private Window window;
    private ClassifierTable table;

    /**
     * Default constructor for the ClassifierSetPanel
     * @param window the window that holds the ClassifierSetPanel
     */
    public ClassifierSetPanel(Window window){
        this.window = window;
        String[] columnNames = new String[1];
        columnNames[0] = "Classifier Set";
        this.table = new ClassifierTable();
        JScrollPane scrollPane = new JScrollPane(table);
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

        JButton displaySetButton = new JButton("Display Set");
        DisplayEvalSetActionListener listener = new DisplayEvalSetActionListener(this.window,this.table);
        displaySetButton.addActionListener(listener);
        this.add(displaySetButton);

        this.add(scrollPane);
    }

    /**
     * Clears the table in the panel
     */
    public void clearTable(){
        this.table.clear();
    }

    /**
     * Adds a classifer to the table in the panel
     * @param name name of the classifier to be added
     * @param data classifier data
     */
    public void addClassifierSetToTable(String name, ClassifierDataSet data){
        this.table.addClassifierDataSet(name, data);
    }
}
