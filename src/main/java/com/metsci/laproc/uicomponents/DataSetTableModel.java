package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphableData;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table model for displaying data sets
 * Created by malinocr on 12/8/2016.
 */
public class DataSetTableModel extends AbstractTableModel {
    private String[] columnNames = new String[]{"Data", "Display"};
    private ObjectBigArrayBigList<GraphableData> dataObjects = new ObjectBigArrayBigList<GraphableData>();
    private ArrayList<Boolean> isDisplayed = new ArrayList<Boolean>();

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (int)dataObjects.size64();
    }

    public String getColumnName(int col) {
        if(col >= getColumnCount()){
            throw new IllegalArgumentException();
        }
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if(row >= dataObjects.size64() || col >= getColumnCount()){
            throw new IllegalArgumentException();
        }
        if(col == 0){
            return dataObjects.get(row).getName();
        }
        return isDisplayed.get(row);
    }

    public boolean isCellEditable(int row, int col) {
        if(row >= dataObjects.size64() || col >= getColumnCount()){
            throw new IllegalArgumentException();
        }
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        if(row >= dataObjects.size64() || col >= getColumnCount()){
            throw new IllegalArgumentException();
        }
        if((col == 0) && value instanceof String) {
            dataObjects.get(row).setName((String)value);
            fireTableCellUpdated(row, col);
        } else if ((col == 1) && value instanceof Boolean){
            isDisplayed.set(row, (Boolean)value);
            fireTableCellUpdated(row, col);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adds a row of graphable data to the table
     * @param data data to add
     */
    public void addRow(GraphableData data, boolean display){
        this.dataObjects.add(data);
        this.isDisplayed.add(display);
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    }

    /**
     * Gets the object associated with a row in the table
     * @param row row in the table
     * @return associated graphable data object
     */
    public GraphableData getObjectAt(int row){
        if(row >= dataObjects.size64()){
            throw new IllegalArgumentException();
        }
        return this.dataObjects.get(row);
    }

    /**
     * Clears all the rows in the table
     */
	public void clear() {
		dataObjects.clear();
	}

    /**
     * Gets the row index of a specified object
     * @param data object to get the row index of
     * @return row index
     */
	public int getRowOfObject(GraphableData data){
        return (int)dataObjects.indexOf(data);
    }
}
