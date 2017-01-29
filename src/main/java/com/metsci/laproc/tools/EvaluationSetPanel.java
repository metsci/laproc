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
import com.metsci.laproc.datareference.DataReference;
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
	private JTextField nameTextField;
	/**
	 * Default constructor, requires a window for context
	 */
	public EvaluationSetPanel(DataReference ref){
		ref.addObserver(this);
		this.panel = new JPanel();
		this.createAction = new CreateNewDataSetAction(ref);
		this.unionAction = new FilterDataSetAction(ref);
		setDataSheet(ref);
	}

	/**
     * Return DataSheet, composed of JTable
     *
     * @params: DataReference
     */
	private void setDataSheet(DataReference ref) {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

		List<TagHeader> headers = ref.getTagHeaders();
		JPanel tagPanel = new JPanel();
		this.tagPanel = tagPanel;
		tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));

		for(TagHeader header: headers){
			DefaultTableModel model = new DefaultTableModel();
			JLabel label = new JLabel(header.getName());
			JTable table = new JTable(model);
			table.setDefaultEditor(Object.class, null);
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

		//ENTER NAME HERE
		JTextField nameTextField = new JTextField();
		this.nameTextField = nameTextField;
		this.nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
		nameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.add(nameTextField);
		
		JButton newEvalSetButton = new JButton("Create New Eval Set");
		ActionListener createListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAction.doAction(EvaluationSetPanel.this);
			}
		};
		newEvalSetButton.addActionListener(createListener);
		//CREATE NEW EVAL SET BUTTON HERE
		this.panel.add(newEvalSetButton);
		//TAG SCROLL PANE HERE
		tagScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.add(tagScrollPane);



		JButton unionEvalSetButton = new JButton("Union with Eval Set");
		ActionListener unionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unionAction.doAction(EvaluationSetPanel.this);
			}
		};
		unionEvalSetButton.addActionListener(unionListener);
		//UNION WITH EVAL SET BUTTON HERE
		this.panel.add(unionEvalSetButton);

		
		final JTextArea setOperationHistoryTextArea = new JTextArea();
		final JComboBox dataSets = new JComboBox();
		ActionListener changeDataSet = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataSets.getSelectedItem() != null) {
					ClassifierDataSet selectedItem = (ClassifierDataSet) dataSets.getSelectedItem();
					setOperationHistoryTextArea.setText(selectedItem.getSetOperations());
				}
			}
		};
		dataSets.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
		dataSets.addActionListener(changeDataSet);
		dataSets.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panel.add(dataSets);
		this.dataSets = dataSets;

		
		final JLabel setOperationTitle = new JLabel("Set Operations:");
	    
	    setOperationHistoryTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
	    setOperationHistoryTextArea.setWrapStyleWord(true);
	    setOperationHistoryTextArea.setLineWrap(true);
	    setOperationHistoryTextArea.setOpaque(false);
	    setOperationHistoryTextArea.setEditable(false);
	    setOperationHistoryTextArea.setFocusable(false);
		this.panel.add(setOperationTitle);
		this.panel.add(setOperationHistoryTextArea);

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
	
	public String getNameText(){
		return nameTextField.getText();
	}

	public void update(DataReference ref) {
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
	
	public void setSelectedDataSet(ClassifierDataSet dataSetRef){
		this.dataSets.setSelectedItem(dataSetRef);
	}
}
