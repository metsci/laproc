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
import com.metsci.laproc.action.UnionDataSetAction;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.uicomponents.FilterActionListener;
import com.metsci.laproc.uicomponents.TableDisplayer;
import com.metsci.laproc.utils.IAction;

/**
 * 
 * A datasheet panel creator.
 * Created by patterjm on 10/5/2016.
 *
 */
public class DataSheetPanel implements ITool, DataObserver {
	private JPanel panel;
	private IAction createAction;
	private IAction unionAction;
	private JPanel tagPanel;
	private JComboBox dataSets;
	/**
	 * Default constructor, requires a window for context
	 */
	public DataSheetPanel(DataReference ref){
		ref.addObserver(this);
		this.panel = new JPanel();
		this.createAction = new CreateNewDataSetAction(ref);
		this.unionAction = new UnionDataSetAction(ref);
		setDataSheet(ref);
	}

	/**
     * Return DataSheet, composed of JTable
     *
     * @params: DataReference
     */
	private void setDataSheet(DataReference ref) {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

//		final TableDisplayer tableDisplayer = new TableDisplayer(data);
//		JTable table = tableDisplayer.getTable();
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
				unionAction.doAction(DataSheetPanel.this);
			}
		};
		unionEvalSetButton.addActionListener(unionListener);
		this.panel.add(unionEvalSetButton);

		JComboBox dataSetNames = new JComboBox();
		JPanel dataSetPanel = new JPanel();
		dataSetPanel.add(dataSetNames);
		this.panel.add(dataSetPanel);
		this.dataSets = dataSetNames;

//		JPanel filterPanel = new JPanel();
//		JTextField truthField = new JTextField(10);
//		filterPanel.add(new JLabel("Truth"));
//		filterPanel.add(truthField);
//		JTextField valueField = new JTextField(10);
//		filterPanel.add(new JLabel("Value"));
//		filterPanel.add(valueField);

//		JButton applyFilterButton = new JButton("Apply Filter");
//		FilterActionListener filterActionListener = new FilterActionListener(table,
//											tableDisplayer.getDataSheetTableModel(),
//											truthField,
//											valueField);
//		applyFilterButton.addActionListener(filterActionListener);
//		filterPanel.add(applyFilterButton);
//		this.panel.add(filterPanel);



	}

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

	public ClassifierDataSet getSelectedDataSet() {
		return (ClassifierDataSet)this.dataSets.getSelectedItem();
	}

	public View getView() {
		return new View("Data Sheet", this.panel, "Data Sheet", true);
	}

	public int getDefaultPosition() {
		return ITool.LEFTPOSITION;
	}

	public void update(DataReference ref) {
		this.dataSets.removeAllItems();
		for(ClassifierDataSet dataSet: ref.getDataSetGroups()){
			this.dataSets.addItem(dataSet);
		}
		this.dataSets.repaint();
	}
}
