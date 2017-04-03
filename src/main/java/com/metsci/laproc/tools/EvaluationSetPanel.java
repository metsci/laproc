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
import com.metsci.laproc.utils.IObserver;

/**
 * Example Panel for manipulating evaluation sets into classifier data sets
 * Created by patterjm on 10/5/2016.
 *
 */
public class EvaluationSetPanel implements ITool, IObserver<InputDataReference> {
	private JPanel panel;
	private IAction createAction;
	private IAction unionAction;
	private JPanel tagPanel;
	private JComboBox dataSets;
	private JTextField nameTextField;

    /**
     * Default constructor
     * @param ref input data reference for the application
     * @param outref output data reference for the application
     */
	public EvaluationSetPanel(InputDataReference ref, OutputDataReference outref){
		ref.addObserver(this);
		this.panel = new JPanel();
		this.createAction = new CreateNewDataSetAction(ref, outref);
		this.unionAction = new FilterDataSetAction(ref, outref);
		createUI(ref);
	}

    /**
     * Creates the UI elements of the tool
     * @param ref input data reference for the application
     */
	private void createUI(InputDataReference ref) {
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

		//Create text field for the user to input the name of a new classifier data set.
        JTextField nameTextField = new JTextField();
        this.nameTextField = nameTextField;
        this.nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
        nameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(nameTextField);

        //Create a button to press to create a new classifier set.
        JButton newClassifierSetButton = new JButton("Create New Classifier Set");
        ActionListener createListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //The create action will handle the creating logic for a classifier set.
                createAction.doAction(EvaluationSetPanel.this);
            }
        };
        newClassifierSetButton.addActionListener(createListener);
        this.panel.add(newClassifierSetButton);

        //Create a panel to display the tag headers and their associated tags.
		List<TagHeader> headers = ref.getTagHeaders();
		JPanel tagPanel = new JPanel();
		this.tagPanel = tagPanel;
		tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
		//Each tag header will have a JLable displaying its name and a table with its tags.
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
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
        tagScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.panel.add(tagScrollPane);

        //Create a button to union additional evaluation sets into a created classifier set.
		JButton unionEvalSetButton = new JButton("Union with Classifier Set");
		ActionListener unionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    //The union action will handle the logic for unioning selected evaluation sets into the selected
                //classifier set.
				unionAction.doAction(EvaluationSetPanel.this);
			}
		};
		unionEvalSetButton.addActionListener(unionListener);
		this.panel.add(unionEvalSetButton);

		final JTextArea setOperationHistoryTextArea = new JTextArea();

		//Create a combo box to display the created classifier data sets
		this.dataSets = new JComboBox();
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

        //Create a text area to display the operation history for
		final JLabel setOperationTitle = new JLabel("Set Operations:");
        this.panel.add(setOperationTitle);
	    setOperationHistoryTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
	    setOperationHistoryTextArea.setWrapStyleWord(true);
	    setOperationHistoryTextArea.setLineWrap(true);
	    setOperationHistoryTextArea.setOpaque(false);
	    setOperationHistoryTextArea.setEditable(false);
	    setOperationHistoryTextArea.setFocusable(false);
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

	/**
	 * Gets the value of the name text field
	 * @return value of the name text field
	 */
	public String getNameText(){
		return nameTextField.getText();
	}

    /**
     * Repopulates the combo box of classifier data sets when the input data reference is updated
     * @param ref input data reference for the application
     */
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

    /**
     * Sets the selected data set to the given data set
     * @param dataSet data set to set as selected
     */
	public void setSelectedDataSet(ClassifierDataSet dataSet){
		this.dataSets.setSelectedItem(dataSet);
	}
}
