package com.metsci.laproc.display;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 * Note: this is a work in progress and may get deleted.
 * Mostly I need to figure out how to apply row filters to the DataSheetPanel, regardless of whether it uses custom subclasses
 * @author patterjm
 *
 * @param <DataSheetTableModel>
 */
public class DataSheetTableRowSorter<DataSheetTableModel extends TableModel> extends TableRowSorter<TableModel> {

	public void setRowFilter(DataSheetRowFilter<DataSheetTableModel, Integer> rowFilter) {
		// TODO Auto-generated method stub
		
	}}
