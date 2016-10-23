package com.metsci.laproc.display;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

/**
 * Preface:
 * Lots of credit for this implementation goes to:
 * http://stackoverflow.com/questions/12559287/how-to-set-a-custom-object-in-a-jtable-row
 * 
 * This acts as a way to store dataPoints as the rows of a table, rather than using the default Object[][] JTable constructor.
 * It also ensures the rows can't be edited, other such properties can be extended
 * @author patterjm
 *
 */
public class DataSheetTableModel extends AbstractTableModel {
	
	private List<DataPoint> dataPoints;
	private String[] tempTags = {"Truth","Value"}; 
	
	public DataSheetTableModel(ClassifierDataSet data){
		ArrayList<DataPoint> dataPointsArray = new ArrayList<DataPoint>();
		Iterator<DataPoint> iter = data.getAllPoints();
		while(iter.hasNext()){
			dataPointsArray.add(iter.next());
		}
		this.dataPoints = dataPointsArray;
	}
	
	
	public int getRowCount() {
		return this.dataPoints.size();
	}

	
	public int getColumnCount() {
		return 2;
	}
	
	
	public String getColumnName(int columnIndex){
		return this.tempTags[columnIndex];
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) {
		DataPoint dataPoint = this.dataPoints.get(rowIndex);
		return columnIndex == 0 ? dataPoint.getTruth() : dataPoint.getValues()[0];
	}
    
	
	public DataPoint getDataPointAt(int index){
		return this.dataPoints.get(index);
	}
	
	public boolean isCellEditable(int row, int col) {
        return false;
    }

}
