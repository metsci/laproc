package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;

import javax.swing.*;
import java.awt.*;

/**
 * Created by malinocr on 10/17/2016.
 */
public class ClassifierSetPanel extends JPanel {
    private Window window;
    private ClassifierTable table;

    public ClassifierSetPanel(Window window){
        this.window = window;
        String[] columnNames = new String[1];
        columnNames[0] = "Classifier Set";
        this.table = new ClassifierTable();
        JScrollPane scrollPane = new JScrollPane(table);
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
        this.add(scrollPane);
    }

    public void clearTable(){
        this.table.clear();
    }

    public void addClassifierSetToTable(String name, ClassifierDataSet data){
        this.table.addClassifierDataSet(name, data);
    }
}
