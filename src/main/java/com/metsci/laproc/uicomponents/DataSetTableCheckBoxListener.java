package com.metsci.laproc.uicomponents;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.metsci.laproc.utils.IAction;

/**
 * Listener for the table model with text boxes that call an add or remove action
 * based on the value of the checkbox
 * Created by malinocr on 12/8/2016.
 */
public class DataSetTableCheckBoxListener implements TableModelListener{
    DataSetTable table;
    IAction add;
    IAction remove;

    public DataSetTableCheckBoxListener(IAction add, IAction remove, DataSetTable table){
        this.table = table;
        this.add = add;
        this.remove = remove;
    }

    public void tableChanged(TableModelEvent e){
        if(e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
            if (e.getSource() instanceof DataSetTableModel) {
                DataSetTableModel model = (DataSetTableModel) e.getSource();
                for(int i = e.getFirstRow(); i < e.getLastRow() + 1; i++){
                    if(model.getValueAt(i,1) instanceof Boolean && (Boolean)model.getValueAt(i,1)){
                        this.add.doAction(model.getObjectAt(i));
                    } else {
                        this.remove.doAction(model.getObjectAt(i));
                    }
                }
            }
        }
    }
}