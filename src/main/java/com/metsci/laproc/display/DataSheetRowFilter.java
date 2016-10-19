package com.metsci.laproc.display;

import javax.swing.RowFilter;

public class DataSheetRowFilter<DataSheetTableModel, Integer> extends RowFilter<DataSheetTableModel, Integer>{

	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends DataSheetTableModel, ? extends Integer> entry) {
//		DataSheetTableModel dataSheetTableModel = entry.getModel();
//		DataPoint dataPoint = dataSheetTableModel.getDataPointAt((Integer) entry.getIdentifier());
//		System.out.println(dataPoint.getTruth());
		return true;
	}

}
