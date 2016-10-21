package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.GraphableFunctionOutput;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A JTable that displays the names of the data sets and is able to access the sets by index
 * Created by malinocr on 10/17/2016.
 */
public class DataSetTable extends JTable{
    private ObjectBigArrayBigList<GraphableFunctionOutput> classList;
    private DefaultTableModel model;

    /**
     * Default constructor for DataSetTable
     */
    public DataSetTable(){
        super();
        this.setTableHeader(null);
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
        classList = new ObjectBigArrayBigList<GraphableFunctionOutput>();
    }

    /**
     * Removes all the data sets
     */
    public void clear(){
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
    }

    /**
     * Adds a data set
     * @param dataSetName name of the data set
     * @param dataSet data set to add
     */
    public void addDataSet(String dataSetName, GraphableFunctionOutput dataSet){
        this.model.addRow(new Object[]{dataSetName});
        this.classList.add(dataSet);
    }

    /**
     * Gets the selected data sets based on selected names
     * @return selected data sets
     */
    public ObjectOpenHashBigSet<GraphableFunctionOutput> getSelectedValues(){
        ObjectOpenHashBigSet<GraphableFunctionOutput> selectedValues = new ObjectOpenHashBigSet<GraphableFunctionOutput>();
        int[] selectedRows = this.getSelectedRows();
        for(int index : selectedRows) {
            selectedValues.add(classList.get(index));
        }
        return selectedValues;
    }
}
