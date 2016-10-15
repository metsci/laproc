package com.metsci.laproc.display;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;

import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;

public class TableDisplayer {
	private JTable table;
	private ObjectBigArrayBigList<DataPoint> dataList;
	
	public TableDisplayer(ClassifierDataSet data){
		this.dataList = new ObjectBigArrayBigList<DataPoint>();
		//instantiate variables
		int sizeOfArray = data.getAllPoints().next().getValues().length + 1;//+1 for truth column
		String[] columnNames = new String[sizeOfArray];
		Object[][] dataList = new Object[100][100];
		int tempCounter = 0;
		long index = 0;
		//write in all data into a 2D Object array, JTable requirement
		for(DataPoint dataPoint : data){
			this.dataList.add(index, dataPoint);
			index++;
			//when at max length, double array size and copy contents over
			if(tempCounter == dataList.length){
				Object[][] newArray = new Object[dataList.length*2][sizeOfArray];
				for(int i = 0; i < dataList.length; i++){
					for(int j = 0; j < sizeOfArray; j++){
						newArray[i][j] = dataList[i][j];
					}
				}
				dataList = newArray;
			}
			
			Object[] datavals = new Object[sizeOfArray];
			double[] getValTemp = dataPoint.getValues();
			
			for(int i = 1; i < sizeOfArray; i++){
				datavals[i] = getValTemp[i-1];
			}
			datavals[0] = dataPoint.getTruth();
			dataList[tempCounter++] = datavals;
		}
		
		//TODO: Implement column headers
		for(int i = 0; i < sizeOfArray; i++){
			columnNames[i] = "Woohoo";
		}
		
		//Init Java Swing settings
		this.table = new JTable(dataList, columnNames);
	}
	
	public JTable getTable(){
		return this.table;
	}
	
	public DataPoint getDataPointAtIndex(long index){
		return this.dataList.get(index);
	}
}
