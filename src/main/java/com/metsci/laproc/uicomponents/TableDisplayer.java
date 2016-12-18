package com.metsci.laproc.uicomponents;

import javax.swing.JTable;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
/**
 * Accepts an evaluation set and constructs the custom table model
 * and table that uses the dataset
 * @author patterjm
 *
 */
public class TableDisplayer {
	private JTable table;
	private DataSheetTableModel dataSheetTableModel;
	
	/**
	 * Given a ClassifierDataSet, construct the custom table model
	 * and apply said model to a new JTable
	 * @param data
	 */
	public TableDisplayer(ClassifierDataSet data){
		DataSheetTableModel dataSheetTableModel = new DataSheetTableModel(data);
		this.dataSheetTableModel = dataSheetTableModel;
		this.table = new JTable(dataSheetTableModel);
	}
	
	/**
	 * Returns the JTable constructed within the TableDisplayer
	 * @return
	 */
	public JTable getTable(){
		return this.table;
	}
	
	/**
	 * Calls the getDataPointAt method written in the custom TableModel class
	 * returns the DataPoint at a given index
	 * @param index
	 * @return
	 */
	public DataPoint getDataPointAtIndex(int index){
		return this.dataSheetTableModel.getDataPointAt(index);
	}
	
	/**
	 * Returns the DataSheetTableModel used to construct the JTable
	 * @return
	 */
	public DataSheetTableModel getDataSheetTableModel(){
		return this.dataSheetTableModel;
	}
}
