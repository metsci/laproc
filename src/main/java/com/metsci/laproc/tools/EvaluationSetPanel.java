package com.metsci.laproc.tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.CreateNewDataSetAction;
import com.metsci.laproc.action.FilterDataSetAction;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.utils.IAction;

/**
 * 
 * Panel for manipulating evaluation sets into dataset groups
 * Created by patterjm on 10/5/2016.
 *
 */
public class EvaluationSetPanel implements ITool, DataObserver {
	private JPanel panel;
	private IAction createAction;
	private IAction unionAction;
	private JPanel tagPanel;
	private JComboBox dataSets;
	/**
	 * Default constructor, requires a window for context
	 */
	public EvaluationSetPanel(InputDataReference inref, OutputDataReference outref){
		inref.addObserver(this);
		this.panel = new JPanel();
		this.createAction = new CreateNewDataSetAction(inref, outref);
		this.unionAction = new FilterDataSetAction(inref, outref);
		setDataSheet(inref);
	}

	/**
     * Return DataSheet, composed of JTable
     *
     * @params: InputDataReference
     */
	private void setDataSheet(InputDataReference ref) {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

		List<TagHeader> headers = ref.getTagHeaders();
		JPanel tagPanel = new JPanel();
		this.tagPanel = tagPanel;
		tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));

		for(TagHeader header: headers){
			DefaultTableModel model = new DefaultTableModel();
			JLabel label = new JLabel(header.getName());
			JTable table = new JTable(model);
			model.addColumn("Tag Name");
			for(String tag : header.getTags()){
				model.addRow(new Object[] {tag});
			}
			tagPanel.add(label);
			tagPanel.add(table);
		}

		JScrollPane tagScrollPane = new JScrollPane(tagPanel);
		tagScrollPane.setViewportView(tagPanel);
//		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		this.panel.add(tagScrollPane);

		JButton newEvalSetButton = new JButton("Create New Eval Set");
		ActionListener createListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAction.doAction(e);
			}
		};
		newEvalSetButton.addActionListener(createListener);
		this.panel.add(newEvalSetButton);

		JButton unionEvalSetButton = new JButton("Union with Eval Set");
		ActionListener unionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unionAction.doAction(EvaluationSetPanel.this);
			}
		};
		unionEvalSetButton.addActionListener(unionListener);
		this.panel.add(unionEvalSetButton);

		final JLabel setOperationLabel = new JLabel();

		final JComboBox dataSets = new JComboBox();
		ActionListener changeDataSet = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataSets.getSelectedItem() != null) {
					ClassifierDataSet selectedItem = (ClassifierDataSet) dataSets.getSelectedItem();
					setOperationLabel.setText(selectedItem.getSetOperations());
				}
			}
		};
		dataSets.addActionListener(changeDataSet);
		JPanel dataSetPanel = new JPanel();
		dataSetPanel.add(dataSets);
		this.panel.add(dataSetPanel);
		this.dataSets = dataSets;

		this.panel.add(setOperationLabel);

	}

	/**
	 * Getter for the selected tags in the tag panel
	 * @return A list of tags seperated into lists based on tag headers
	 */
	public List<List<String>> getSelectedTags(){
		Component[] components = this.tagPanel.getComponents();
		List<List<String>> totalList = new ArrayList<List<String>>();
		for(Component comp: components){
			if(comp instanceof JTable){
				JTable currentTable = (JTable)comp;
				List<String> currentList = new ArrayList<String>();
				for(int row : currentTable.getSelectedRows()){
					currentList.add((String)currentTable.getValueAt(row,0));
				}
				totalList.add(currentList);
			}
		}
		return totalList;
	}

	/**
	 * Getter for the currently selected data set group
	 * @return selected data set group
	 */
	public ClassifierDataSet getSelectedDataSet() {
		return (ClassifierDataSet)this.dataSets.getSelectedItem();
	}

	public View getView() {
		return new View("Data Sheet", this.panel, "Data Sheet", true);
	}

	public int getDefaultPosition() {
		return ITool.LEFTPOSITION;
	}

	public void update(InputDataReference ref) {
		ClassifierDataSet selectedDataSet = (ClassifierDataSet)this.dataSets.getSelectedItem();
		this.dataSets.removeAllItems();
		for(ClassifierDataSet dataSet: ref.getDataSetGroups()){
			this.dataSets.addItem(dataSet);
		}
		if(selectedDataSet != null) {
			this.dataSets.setSelectedItem(selectedDataSet);
		}
		this.dataSets.repaint();
	}
}
