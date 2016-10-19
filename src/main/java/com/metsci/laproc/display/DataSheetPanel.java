package com.metsci.laproc.display;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.metsci.laproc.data.ClassifierDataSet;

/**
 * 
 * A datasheet panel creator.
 * Created by patterjm on 10/5/2016.
 *
 */
public class DataSheetPanel extends JPanel{
	private Window window;
	
	public DataSheetPanel(Window window){
		this.window = window;
	}
	/**
	 * Return DataSheet, composed of JTable
	 * 
	 * @params: ClassifierDataSet
	 */
	public void setDataSheet(ClassifierDataSet data) {
		TableDisplayer tableDisplayer = new TableDisplayer(data);
		
		JTable table = tableDisplayer.getTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
		
		JButton newEvalSetButton = new JButton("Create New Eval Set");
        NewEvalSetActionListener nesaInstance = new NewEvalSetActionListener(this.window, tableDisplayer);
		newEvalSetButton.addActionListener(nesaInstance);
		this.add(newEvalSetButton);
		
		this.add(scrollPane);
		
	}
	
}
