package com.metsci.laproc.uicomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	/**
	 * Given a ClassifierDataSet, builds a custom table model using
	 * DataPoints as rows in the table
	 * @param data
	 */
	public DataSheetTableModel(ClassifierDataSet data){
		ArrayList<DataPoint> dataPointsArray = new ArrayList<DataPoint>();
		Set<DataPoint> dataset = data.getAllPoints();
		for(DataPoint point: dataset) {
			dataPointsArray.add(point);
		}
		this.dataPoints = dataPointsArray;
	}
	
	/**
	 * Returns the number of DataPoints in the set
	 * interpreted as the RowCount of the table
	 */
	public int getRowCount() {
		return this.dataPoints.size();
	}

	/**
	 * Returns the number of tags per DataPoint
	 * interpreted as the ColumnCount of the table
	 */
	public int getColumnCount() {
		return 2;
	}
	
	/**
	 * Returns the value of the tag given an index
	 * interpreted as the ColumnName of the table's column
	 */
	public String getColumnName(int columnIndex){
		return this.tempTags[columnIndex];
	}

	/**
	 * Returns the value of a DataPoint's given
	 * tag, interpreted as the Value at an ordered pair (rowIndex, columnIndex)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		DataPoint dataPoint = this.dataPoints.get(rowIndex);
		return columnIndex == 0 ? dataPoint.getTruth() : dataPoint.getValues()[0];
	}
    
	/**
	 * Given a row index, returns the DataPoint object
	 * instantiated at the Table 
	 * @param index
	 * @return
	 */
	public DataPoint getDataPointAt(int index){
		return this.dataPoints.get(index);
	}
	
	/**
	 * A way for us to prevent any editing of the JTable once
	 * displayed on the Window
	 */
	public boolean isCellEditable(int row, int col) {
        return false;
    }

}
