package com.metsci.laproc.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.TableRowSorter;

import com.metsci.laproc.data.DataPoint;
/**
 * Note: this is a work in progress and may get deleted.
 * Mostly I need to figure out how to apply row filters to the DataSheetPanel, regardless of whether it uses custom subclasses
 * @author patterjm
 *
 */
public class FilterActionListener implements ActionListener {
	private DataSheetTableModel dataSheetTableModel;
	private JTable table;
	private JTextField truthVal;
	private JTextField valueVal;
	
	/**
	 * Custom IAction Listener that applys a filter to the model, which then updates the JTable on which it rests.
	 * Further includes any JTextFields that were used for the custom filtering. Currently accepts a truth boolean
	 * and a lowerbound on the data set
	 * @param table2
	 * @param dataSheetTableModel2
	 * @param truthField
	 * @param valueField
	 */
	public FilterActionListener(JTable table2, DataSheetTableModel dataSheetTableModel2, JTextField truthField, JTextField valueField) {
		this.table = table2;
		this.dataSheetTableModel = dataSheetTableModel2;
		this.truthVal = truthField;
		this.valueVal = valueField;
	}

	/**
	 * Accepts the newly filtered rows of the JTable
	 * and creates a new evaluation set
	 */
	public void actionPerformed(ActionEvent e) {
		RowFilter<DataSheetTableModel, Integer> filter = new RowFilter<DataSheetTableModel, Integer>() {
            public boolean include(Entry<? extends DataSheetTableModel, ? extends Integer> entry) {
            	DataSheetTableModel dstm = entry.getModel();
            	DataPoint dp = dstm.getDataPointAt(entry.getIdentifier());
            	boolean truthFilter = true;
            	if(!truthVal.getText().isEmpty()){
            		if(truthVal.getText().equals("false")){
            			truthFilter = !dp.getTruth();
            		}else if(truthVal.getText().equals("true")){
            			truthFilter = dp.getTruth();
            		}
            	}
            	
            	boolean valueFilter = true;
            	if(!valueVal.getText().isEmpty()){
            		double lowerBound = Double.parseDouble(valueVal.getText());
            		valueFilter = dp.getValues()[0] > lowerBound;
            	}
            	
                return truthFilter && valueFilter;
            }
        };
        
		TableRowSorter<DataSheetTableModel> sorter = new TableRowSorter<DataSheetTableModel>(dataSheetTableModel);
		sorter.setRowFilter(filter);
		this.table.setRowSorter(sorter);
		this.table.selectAll();
		
		this.table.revalidate();
		
	}

}
