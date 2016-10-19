package com.metsci.laproc.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.plotting.Axis;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.plotting.GraphableFunctionOutput;
import com.metsci.laproc.plotting.ROCCurve;

public class NewEvalSetActionListener implements ActionListener{
	private Window window;
	private TableDisplayer tableDisplayer;
	private int currentAddedIndex = 1;

	public NewEvalSetActionListener(Window window, TableDisplayer tableDisplayer) {
		this.window = window;
		this.tableDisplayer = tableDisplayer;
	}

	public void actionPerformed(ActionEvent e) {
		JTable table = this.tableDisplayer.getTable();
		int[] indexes = table.getSelectedRows();
		ClassifierDataSet data = new ClassifierDataSet();
		for(int i = 0; i < indexes.length; i++){
			data.add(this.tableDisplayer.getDataPointAtIndex(i));
		}
		this.window.addDataToClass("New Data Set " + currentAddedIndex++,data);
	}

}
