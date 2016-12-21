package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.uicomponents.DataSetTableCheckBoxListener;
import com.metsci.laproc.uicomponents.DataSetTableModel;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.action.AddToGraphAction;
import com.metsci.laproc.action.RemoveFromGraphAction;
import com.metsci.laproc.plotting.GraphableData;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A JPanel that handles interacting with the created data sets
 * Created by malinocr on 10/17/2016.
 */
public class DataSetPanel implements ITool, DataObserver {
    private JPanel panel;
    private DataSetTable table;
    private DataReference reference;
    private IAction addAction;
    private IAction removeAction;

    /**
     * Default constructor for the DataSetPanel
     */
    public DataSetPanel(DataReference ref){
        ref.addObserver(this);
        this.panel = new JPanel();
        this.reference = ref;
        this.addAction = new AddToGraphAction(reference);
        this.removeAction = new RemoveFromGraphAction(reference);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.table = new DataSetTable();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
        
        this.table.getModel().addTableModelListener(new DataSetTableCheckBoxListener(addAction, removeAction, table));

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
    public void addDataSetToTable(GraphableData<?> data){
        this.table.addDataSet(data);
    }

    public View getView() {
        return new View("Data Set", this.panel, "Data Set", true);
    }

    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    public void update(DataReference reference) {
    	List<GraphableData<?>> graphSets = reference.getGraphSets();
    	this.clearTable();
    	for(GraphableData<?> data : graphSets){
    		this.addDataSetToTable(data);
    	}
    }
}
