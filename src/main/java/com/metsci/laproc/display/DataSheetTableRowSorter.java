package com.metsci.laproc.display;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class DataSheetTableRowSorter<DataSheetTableModel extends TableModel> extends TableRowSorter<TableModel> {

	public void setRowFilter(DataSheetRowFilter<DataSheetTableModel, Integer> rowFilter) {
		// TODO Auto-generated method stub
		
	}}
