package com.metsci.laproc.uicomponents;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Listener for the table model with text boxes that call an add or remove action
 * based on the value of the checkbox
 * Created by malinocr on 12/8/2016.
 */
public class DataSetTableCheckBoxListener implements TableModelListener{
    private IAction<GraphableData> show;
    private IAction<GraphableData> hide;

    /**
     * Constructor for the checkbox listener
     * @param show action to perform when a checkbox is turned on
     * @param hide action to perform when a checkbox is turned off
     */
    public DataSetTableCheckBoxListener(IAction<GraphableData> show, IAction<GraphableData> hide){
        this.show = show;
        this.hide = hide;
    }

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
