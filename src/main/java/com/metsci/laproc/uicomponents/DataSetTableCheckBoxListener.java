package com.metsci.laproc.uicomponents;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Listener for a table model with text boxes that calls actions when a checkbox is toggled
 * Created by malinocr on 12/8/2016.
 */
public class DataSetTableCheckBoxListener implements TableModelListener{
    private DataSetTable table;
    private IAction<GraphableData> show;
    private IAction<GraphableData> hide;

    /**
     * Constructor for the checkbox listener
     * @param show action to perform when a checkbox is turned on
     * @param hide action to perform when a checkbox is turned off
     * @param table table to observe
     */
    public DataSetTableCheckBoxListener(IAction<GraphableData> show, IAction<GraphableData> hide, DataSetTable table){
        this.table = table;
        this.show = show;
        this.hide = hide;
    }

    /**
     * This function is called whenever the table this listener is observing is modified.
     * It check to see if the second column of the modified row is a checkbox that has been toggled
     * and call the appropriate action.
     * @param e table model event created by the observed table when it is modified
     */
    public void tableChanged(TableModelEvent e){
        if(e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
            if (e.getSource() instanceof DataSetTableModel) {
                DataSetTableModel model = (DataSetTableModel) e.getSource();
                for(int i = e.getFirstRow(); i < e.getLastRow() + 1; i++){
                    if(model.getValueAt(i,1) instanceof Boolean && (Boolean)model.getValueAt(i,1)){
                        this.show.doAction(model.getObjectAt(i));
                    } else {
                        this.hide.doAction(model.getObjectAt(i));
                    }
                }
            }
        }
    }
}
