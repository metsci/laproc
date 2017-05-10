package com.metsci.laproc.tools;

import com.metsci.glimpse.docking.View;
import com.metsci.laproc.action.DisplayGraphDataAction;
import com.metsci.laproc.action.HideGraphDataAction;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.uicomponents.DataSetTableCheckBoxListener;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObserver;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import javax.swing.*;

import java.awt.Color;

/**
 * Example tool that allows the user to show and hide graphable data using checkboxes
 * Created by malinocr on 10/17/2016.
 */
public class GraphableDataPanel implements ITool, IObserver<OutputDataReference> {
    /** The displayable panel to store smaller components */
    private JPanel panel;
    /** A table to display all data sets */
    private DataSetTable table;
    /** The OutputDataReference observed by this tool */
    private OutputDataReference reference;
    /** The action to execute when the user "shows" a data set */
    private IAction showAction;
    /** The action to execute when the user "hides" a data set */
    private IAction hideAction;

    /**
     * Default constructor for the GraphableDataPanel
     * @param ref output data reference containing graphable data to show and hide
     */
    public GraphableDataPanel(OutputDataReference ref){
        //When the graphable data (contained in the output data reference set) this tool should update as well
        ref.addObserver(this);
        this.panel = new JPanel();
        this.reference = ref;

        //These are the actions that should be taken when a checkbox next to the graphable data is toggled
        this.showAction = new DisplayGraphDataAction(reference);
        this.hideAction = new HideGraphDataAction(reference);

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.table = new DataSetTable();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

        //Add the listener for checkboxes to the table using a custom listener and the show and hide actions
        this.table.getModel().addTableModelListener(new DataSetTableCheckBoxListener(showAction, hideAction));

        JScrollPane scrollPane = new JScrollPane(table);
        this.panel.add(scrollPane);
    }

    /**
     * Clears the table in the panel
     */
    public void clearTable(){
        this.table.clear();
    }

    /**
     * Adds a data set to the table in the panel
     * @param data data set to be added
     * @param display true if the data is displayed
     */
    public void addDataSetToTable(GraphableData<?> data, boolean display){
        this.table.addDataSet(data, display);
    }

    /**
     * Getter for the view for the data set panel
     * @return data set panel view
     */
    public View getView() {
        return new View("Data Set", this.panel, "Data Set", true);
    }

    /**
     * Getter for the default position of the panel
     * @return default position of the panel
     */
    public int getDefaultPosition() {
        return ITool.LEFTPOSITION;
    }

    /**
     * Repopulates the table containing graphable data when the reference is updated
     * @param reference reference to observe
     */
    public void update(OutputDataReference reference) {
        ObjectOpenHashBigSet<GraphableData> selected = this.table.getSelectedValues();
    	this.clearTable();
        Iterable<GraphableData> data = reference.getAllData();
        for(GraphableData d : data) {
            this.addDataSetToTable(d, reference.isDisplayed(d));
        }
        this.table.setSelectedValues(selected);
    }
}
