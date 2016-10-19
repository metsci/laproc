package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A JTable that displays the names of the classifer sets and is able to access the sets by index
 * Created by malinocr on 10/17/2016.
 */
public class ClassifierTable extends JTable{
    private ObjectBigArrayBigList<ClassifierDataSet> classList;
    private DefaultTableModel model;

    /**
     * Default constructor for ClassifierTable
     */
    public ClassifierTable(){
        super();
        this.setTableHeader(null);
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
        classList = new ObjectBigArrayBigList<ClassifierDataSet>();
    }

    /**
     * Removes all the elements from the table
     */
    public void clear(){
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
    }

    /**
     * Adds a classifer data to the table
     * @param dataSetName name of the classifer data set
     * @param dataSet data of the classifier data set
     */
    public void addClassifierDataSet(String dataSetName, ClassifierDataSet dataSet){
        this.model.addRow(new Object[]{dataSetName});
        this.classList.add(dataSet);
    }

    /**
     * Gets the selected ClassifierDataSets based on selected names
     * @return selected ClassiferDataSets
     */
    public ObjectOpenHashBigSet<ClassifierDataSet> getSelectedValues(){
        ObjectOpenHashBigSet<ClassifierDataSet> selectedValues = new ObjectOpenHashBigSet<ClassifierDataSet>();
        int[] selectedRows = this.getSelectedRows();
        for(int index : selectedRows) {
            selectedValues.add(classList.get(index));
        }
        return selectedValues;
    }
}
