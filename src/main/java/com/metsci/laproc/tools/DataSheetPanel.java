package com.metsci.laproc.tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.CreateNewDataSetAction;
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
	private IAction action;
	private JTable table;
	/**
	 * Default constructor, requires a window for context
	 */
	public DataSheetPanel(DataReference ref){
		ref.addObserver(this);
		this.panel = new JPanel();
		this.action = new CreateNewDataSetAction(ref);
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
		JPanel tablePanels = new JPanel();
		tablePanels.setLayout(new BoxLayout(tablePanels, BoxLayout.Y_AXIS));

		for(TagHeader header: headers){
			DefaultTableModel model = new DefaultTableModel();
			JLabel label = new JLabel(header.getName());
			JTable table = new JTable(model);
			model.addColumn("Tag Name");
			for(String tag : header.getTags()){
				model.addRow(new Object[] {tag});
			}
			tablePanels.add(label);
			tablePanels.add(table);
		}

		JScrollPane scrollPane = new JScrollPane(tablePanels);
		scrollPane.setViewportView(tablePanels);
//		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		this.panel.add(scrollPane);

		JButton newEvalSetButton = new JButton("Create New Eval Set");
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.doAction(e);
			}
		};
		newEvalSetButton.addActionListener(listener);
		this.panel.add(newEvalSetButton);
		
		JTable dataSetNames = new JTable();
		dataSetNames.setModel(new DefaultTableModel(0,1));
		this.panel.add(dataSetNames);
		this.table = dataSetNames;

		JPanel filterPanel = new JPanel();
		JTextField truthField = new JTextField(10);
		filterPanel.add(new JLabel("Truth"));
		filterPanel.add(truthField);
		JTextField valueField = new JTextField(10);
		filterPanel.add(new JLabel("Value"));
		filterPanel.add(valueField);

//		JButton applyFilterButton = new JButton("Apply Filter");
//		FilterActionListener filterActionListener = new FilterActionListener(table,
//											tableDisplayer.getDataSheetTableModel(),
//											truthField,
//											valueField);
//		applyFilterButton.addActionListener(filterActionListener);
//		filterPanel.add(applyFilterButton);
//		this.panel.add(filterPanel);



	}

	public View getView() {
		return new View("Data Sheet", this.panel, "Data Sheet", true);
	}

	public int getDefaultPosition() {
		return ITool.LEFTPOSITION;
	}

	public void update(DataReference ref) {
		this.table.setModel(new DefaultTableModel(0,1));
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		for(ClassifierDataSet dataSet: ref.getDataSetGroups()){
			model.addRow(new Object[]{dataSet.getName()});
		}
		this.table.repaint();
		
	}
}
