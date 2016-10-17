package com.metsci.laproc.display;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Created by malinocr on 10/17/2016.
 */
public class ClassifierTable extends JTable{
    private ObjectBigArrayBigList<ClassifierDataSet> evaluationList;
    private DefaultTableModel model;

    public ClassifierTable(){
        super();
        this.setTableHeader(null);
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
        evaluationList = new ObjectBigArrayBigList<ClassifierDataSet>();
    }

    public void clear(){
        this.model = new DefaultTableModel(0,1);
        this.setModel(this.model);
    }

    public void addClassifierDataSet(String dataSetName, ClassifierDataSet dataSet){
        this.model.addRow(new Object[]{dataSetName});
        this.evaluationList.add(dataSet);
    }
}
