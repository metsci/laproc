package com.metsci.laproc;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

public class DataSheetGUI {

	public static JScrollPane GetDataSheet() {
		int NUM_COLS = 100;
		int NUM_ROWS = 1500;
		JPanel dataPanel = new JPanel();
		String[] columnNames = new String[NUM_COLS];
		Object[][] data = new Object[NUM_ROWS][NUM_COLS];
		TableColumn column = null;
		for (int i = 0; i < NUM_COLS; i++) {
			columnNames[i] = "Column Header " + i;
			for (int j = 0; j < NUM_ROWS; j++) {
				data[j][i] = "Row value " + (j * NUM_COLS + i);
			}
		}
		JTable table = new JTable(data, columnNames);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		for (int i = 0; i < NUM_COLS; i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(150);
		}
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
		return scrollPane;
	}

}
