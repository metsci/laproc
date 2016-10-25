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
/**
 * ActionListener that accepts the window and a table displayer to create a new 
 * evaluation set based on the selected rows.
 * @author patterjm
 *
 */
public class NewEvalSetActionListener implements ActionListener{
	private Window window;
	private TableDisplayer tableDisplayer;
	private int currentAddedIndex = 1;

	/**
	 * Given a window and a TableDisplayer,
	 * constructs a custom action listener
	 * @param window
	 * @param tableDisplayer
	 */
	public NewEvalSetActionListener(Window window, TableDisplayer tableDisplayer) {
		this.window = window;
		this.tableDisplayer = tableDisplayer;
	}

	/**
	 * Iterates through the selected data points and
	 * adds them to a new ClassifierDataSet, construction
	 * a new GraphableFunction, finally passing along
	 * the GraphableFunctionOutput to the window
	 */
	public void actionPerformed(ActionEvent e) {
		JTable table = this.tableDisplayer.getTable();
		int[] indexes = table.getSelectedRows();
		ClassifierDataSet data = new ClassifierDataSet();
		for(int i = 0; i < indexes.length; i++){
			data.add(this.tableDisplayer.getDataPointAtIndex(i));
		}
		GraphableFunction func = new ROCCurve(data);
		GraphableFunctionOutput output = func.compute();
		this.window.addDataSetToClass("New Data Set " + currentAddedIndex++, output);
	}

}
