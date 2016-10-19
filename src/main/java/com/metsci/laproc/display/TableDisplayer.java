package com.metsci.laproc.display;

import javax.swing.JTable;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

public class TableDisplayer {
	private JTable table;
	private DataSheetTableModel dataSheetTableModel;
	
	public TableDisplayer(ClassifierDataSet data){
		DataSheetTableModel dataSheetTableModel = new DataSheetTableModel(data);
		this.dataSheetTableModel = dataSheetTableModel;
		this.table = new JTable(dataSheetTableModel);
	}
	
	
	public JTable getTable(){
		return this.table;
	}
	
	
	public DataPoint getDataPointAtIndex(int index){
		return this.dataSheetTableModel.getDataPointAt(index);
	}
}
