package com.metsci.laproc.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
/**
 * 
 * A datasheet panel creator.
 * Created by patterjm on 10/5/2016.
 *
 */
public class DataSheetPanel {
	/**
	 * Return DataSheet, composed of JTable
	 * 
	 * @params: ClassifierDataSet
	 */
	public static JScrollPane GetDataSheet(ClassifierDataSet data) {
		//instantiate variables
		int sizeOfArray = data.iterator().next().getValues().length + 1;//+1 for truth column
		String[] columnNames = new String[sizeOfArray];
		Object[][] dataList = new Object[100][100];
		int tempCounter = 0;
		
		//write in all data into a 2D Object array, JTable requirement
		for(DataPoint dataPoint : data){
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
		JTable table = new JTable(dataList, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
		return scrollPane;
	}

}
