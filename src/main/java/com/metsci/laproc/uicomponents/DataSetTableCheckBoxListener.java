package com.metsci.laproc.uicomponents;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.metsci.laproc.utils.IAction;

/**
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
//    	System.out.println("Is insert? : " + (e.getType() == TableModelEvent.INSERT));
//    	System.out.println("Is delete? : " + (e.getType() == TableModelEvent.DELETE));
//    	System.out.println("Is update? : " + (e.getType() == TableModelEvent.UPDATE));
//    	System.out.println("Listener check");
        if(e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
            if (e.getSource() instanceof DataSetTableModel) {
                DataSetTableModel model = (DataSetTableModel) e.getSource();
                for(int i = e.getFirstRow(); i < e.getLastRow() + 1; i++){
                    if(model.getValueAt(i,1) instanceof Boolean && (Boolean)model.getValueAt(i,1)){
                        this.add.doAction(this.table);
//                        System.out.println("Add");
                    } else {
                        this.remove.doAction(this.table);
//                        System.out.println("Remove");
                    }
                }
            }
        }
    }
}