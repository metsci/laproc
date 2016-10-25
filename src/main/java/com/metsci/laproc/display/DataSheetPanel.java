package com.metsci.laproc.display;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import com.metsci.laproc.data.ClassifierDataSet;

/**
 * 
 * A datasheet panel creator.
 * Created by patterjm on 10/5/2016.
 *
 */
public class DataSheetPanel extends JPanel{
	private Window window;
	/**
	 * Default constructor, requires a window for context
	 * @param window
	 */
	public DataSheetPanel(Window window){
		this.window = window;
	}
	/**
	 * Return DataSheet, composed of JTable
	 * 
	 * @params: ClassifierDataSet
	 */
	public void setDataSheet(ClassifierDataSet data) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TableDisplayer tableDisplayer = new TableDisplayer(data);
		
		JTable table = tableDisplayer.getTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
		
		this.add(scrollPane);
		//new eval set button to be put on DataSheetPanel, takes an action listener and 
		JButton newEvalSetButton = new JButton("Create New Eval Set");
        NewEvalSetActionListener nesaInstance = new NewEvalSetActionListener(this.window, tableDisplayer);
		newEvalSetButton.addActionListener(nesaInstance);
		this.add(newEvalSetButton);
		
//		JPanel filterPanel = new JPanel();
//		filterPanel.add(new JLabel("Truth"));
//		filterPanel.add(new JTextField(10));
//		filterPanel.add(new JLabel("Value"));
//		filterPanel.add(new JTextField(10));
//		
//		JButton applyFilterButton = new JButton("Apply Filter");
//		FilterActionListener filterActionListener = new FilterActionListener();
//		applyFilterButton.addActionListener(filterActionListener);
//		this.add(filterPanel);
		
		
		
	}
	
}
