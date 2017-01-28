package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.DisplayGraphDataAction;
import com.metsci.laproc.action.HideGraphDataAction;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.uicomponents.DataSetTableCheckBoxListener;
import com.metsci.laproc.uicomponents.DataSetTableModel;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.action.AddToGraphAction;
import com.metsci.laproc.action.RemoveFromGraphAction;
import com.metsci.laproc.plotting.GraphableData;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A JPanel that handles interacting with classifer data sets
 * Created by malinocr on 10/17/2016.
 */
public class DataSetPanel implements ITool, DataObserver {
    private JPanel panel;
    private DataSetTable table;
    private DataReference reference;
    private IAction showAction;
    private IAction hideAction;

    /**
     * Default constructor for the DataSetPanel
     */
    public DataSetPanel(DataReference ref){
        ref.addObserver(this);
        this.panel = new JPanel();
        this.reference = ref;
        this.showAction = new DisplayGraphDataAction(reference);
        this.hideAction = new HideGraphDataAction(reference);
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.table = new DataSetTable();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
        
        this.table.getModel().addTableModelListener(new DataSetTableCheckBoxListener(showAction, hideAction, table));

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
     * @param display true if the data is displayed
     */
    public void addDataSetToTable(GraphableData<?> data, boolean display){
        this.table.addDataSet(data, display);
    }

    /**
     * Getter for the view for the data set panel
     * @return data set panel view
     */
    public View getView() {
        return new View("Data Set", this.panel, "Data Set", true);
    }

    /**
     * Getter for the default position of the panel
     * @return default position of the panel
     */
    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    /**
     * Updates the panel when the reference is updated
     * @param reference referance to observe
     */
    public void update(DataReference reference) {
        ObjectOpenHashBigSet<GraphableData> selected = this.table.getSelectedValues();
    	this.clearTable();
        List<Pair<GraphableData,Boolean>> data = reference.getGraph().getDataPairs();
        for(int i = 0; i < data.size(); i++){
            this.addDataSetToTable(data.get(i).getKey(), data.get(i).getValue());
        }
        this.table.setSelectedValues(selected);
    }
}
