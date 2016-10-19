package com.metsci.laproc.display;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

public class TableDisplayer {
	private JTable table;
	private DataSheetTableModel dataSheetTableModel;
	
	public TableDisplayer(ClassifierDataSet data){
		DataSheetTableModel dataSheetTableModel = new DataSheetTableModel(data);
		this.dataSheetTableModel = dataSheetTableModel;
		this.table = new JTable(dataSheetTableModel);
		
//		DataSheetTableRowSorter<DataSheetTableModel> sorter = new DataSheetTableRowSorter<DataSheetTableModel>();
//		System.out.println("Past sorter construction" + sorter.toString());
//		DataSheetRowFilter<DataSheetTableModel,Integer> rowFilter = new DataSheetRowFilter<DataSheetTableModel, Integer>();
//		System.out.println("Past rowFilter construction" + rowFilter.toString());
//		sorter.setRowFilter(rowFilter);
//		System.out.println("Row Filter applied to sorter" + sorter.getRowFilter().toString());
//		table.setRowSorter(sorter);
//		System.out.println("Table set row sorter" + table.getRowSorter().toString());
	}
	
	
	public JTable getTable(){
		return this.table;
	}
	
	
	public DataPoint getDataPointAtIndex(int index){
		return this.dataSheetTableModel.getDataPointAt(index);
	}
}
