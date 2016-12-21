package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableDataWithMetrics;
import it.unimi.dsi.fastutil.booleans.BooleanBigArrayBigList;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table model for displaying data sets
 * Created by malinocr on 12/8/2016.
 */
public class DataSetTableModel extends AbstractTableModel {
    private String[] columnNames = new String[]{"DataSet", "Display"};
    private ObjectBigArrayBigList<GraphableData> dataObjects = new ObjectBigArrayBigList<GraphableData>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (int)dataObjects.size64();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if(col == 0){
            return dataObjects.get(row).getName();
        }
        return dataObjects.get(row).isDisplayed();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        if((col == 0) && value instanceof String) {
            dataObjects.get(row).setName((String)value);
            fireTableCellUpdated(row, col);
        } else if ((col == 1) && value instanceof Boolean){
            dataObjects.get(row).setDisplay((Boolean) value);
            fireTableCellUpdated(row, col);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adds a row of graphable data to the table
     * @param data data to add
     */
    public void addRow(GraphableData data){
        this.dataObjects.add(data);
        fireTableRowsInserted(data.getSize() - 1,data.getSize() - 1);
    }

    /**
     * Gets the object associated with a row in the table
     * @param row row in the table
     * @return associated graphable data object
     */
    public GraphableData getObjectAt(int row){
        return this.dataObjects.get(row);
    }

	public void clear() {
		dataObjects.clear();
	}

}