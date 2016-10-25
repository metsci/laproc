package com.metsci.laproc.display;

import javax.swing.RowFilter;
/**
 * Note: this is a work in progress and may get deleted.
 * Mostly I need to figure out how to apply row filters to the DataSheetPanel, regardless of whether it uses custom subclasses
 * @author patterjm
 *
 * @param <DataSheetTableModel>
 * @param <Integer>
 */
public class DataSheetRowFilter<DataSheetTableModel, Integer> extends RowFilter<DataSheetTableModel, Integer>{

	/**
	 * The purpose of the include method is to return true on records that will be included in the new subset,
	 * false otherwise.
	 */
	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends DataSheetTableModel, ? extends Integer> entry) {
//		DataSheetTableModel dataSheetTableModel = entry.getModel();
//		DataPoint dataPoint = dataSheetTableModel.getDataPointAt((Integer) entry.getIdentifier());
//		System.out.println(dataPoint.getTruth());
		return true;
	}

}
