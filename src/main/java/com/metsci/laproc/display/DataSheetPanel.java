package com.metsci.laproc.display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.ActionHandlers.CreateNewDataSetAction;
import com.metsci.laproc.application.DataReference;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.utils.IObservable;
import com.metsci.laproc.utils.IObserver;

/**
 * 
 * A datasheet panel creator.
 * Created by patterjm on 10/5/2016.
 *
 */
public class DataSheetPanel implements ITool {
	private JPanel panel;
	private IAction action;
	/**
	 * Default constructor, requires a window for context
	 */
	public DataSheetPanel(DataReference ref){
		this.panel = new JPanel();
		this.action = new CreateNewDataSetAction(ref);
	}

	/**
     * Return DataSheet, composed of JTable
     *
     * @params: ClassifierDataSet
     */
	public void setDataSheet(ClassifierDataSet data) {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		final TableDisplayer tableDisplayer = new TableDisplayer(data);

		JTable table = tableDisplayer.getTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		this.panel.add(scrollPane);
		JButton newEvalSetButton = new JButton("Create New Eval Set");

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.doAction(tableDisplayer);
			}
		};

		newEvalSetButton.addActionListener(listener);
		this.panel.add(newEvalSetButton);

		JPanel filterPanel = new JPanel();
		JTextField truthField = new JTextField(10);
		filterPanel.add(new JLabel("Truth"));
		filterPanel.add(truthField);
		JTextField valueField = new JTextField(10);
		filterPanel.add(new JLabel("Value"));
		filterPanel.add(valueField);

		JButton applyFilterButton = new JButton("Apply Filter");
		FilterActionListener filterActionListener = new FilterActionListener(table,
											tableDisplayer.getDataSheetTableModel(),
											truthField,
											valueField);
		applyFilterButton.addActionListener(filterActionListener);
		filterPanel.add(applyFilterButton);
		this.panel.add(filterPanel);



	}

	public View getView() {
		return new View("Data Sheet", this.panel, "Data Sheet", true);
	}

	public int getDefaultPosition() {
		return ITool.LEFTPOSITION;
	}
}
