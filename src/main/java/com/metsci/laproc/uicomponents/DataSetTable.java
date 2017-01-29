package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphableData;
import com.sun.javaws.exceptions.InvalidArgumentException;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A JTable that displays the names of the data sets and is able to access the sets by index
 * Created by malinocr on 10/17/2016.
 */
public class DataSetTable extends JTable{
	private DataSetTableModel model;

    /**
     * Default constructor for DataSetTable
     */
    public DataSetTable(){
        super();
        this.setTableHeader(null);
        this.setShowVerticalLines(false);
        this.model = new DataSetTableModel();
        this.setModel(this.model);
        this.getColumnModel().getColumn(1).setMaxWidth(20);
    }
    
    /**
     * Removes all the data sets
     */
    public void clear(){
        this.model.clear();
    }

    /**
     * Adds a data set
     * @param dataSet data set to add
     * @param display true if the data is displayed
     */
    public void addDataSet(GraphableData dataSet, boolean display){
        this.model.addRow(dataSet, display);
    }

    /**
     * Gets the selected data sets based on selected names
     * @return selected data sets
     */
    public ObjectOpenHashBigSet<GraphableData> getSelectedValues(){
        ObjectOpenHashBigSet<GraphableData> selectedValues = new ObjectOpenHashBigSet<GraphableData>();
        int[] selectedRows = this.getSelectedRows();
        for(int index : selectedRows) {
            selectedValues.add(this.model.getObjectAt(index));
        }
        return selectedValues;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            default:
                throw new IllegalArgumentException();
        }
    }


    /**
     * Sets the selected values in the table to a set of Graphable Data Objects
     * @param dataSet set of Graphable Data objects to set as selected
     */
    public void setSelectedValues(ObjectOpenHashBigSet<GraphableData> dataSet){
        this.clearSelection();
        for(GraphableData data : dataSet) {
            int index = this.model.getRowOfObject(data);
            if (index >= 0) {
                this.addRowSelectionInterval(index, index);
            }
        }
    }

}
