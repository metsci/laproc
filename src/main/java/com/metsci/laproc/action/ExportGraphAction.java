package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;
import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;

/**
 * Action to export graph to a file location
 * Created by malinocr on 1/14/2017.
 */
public class ExportGraphAction implements IAction<String> {
    private DataReference reference;

    /**
     * Default construction
     * @param ref data reference to use
     */
    public ExportGraphAction(DataReference ref){
        this.reference = ref;
    }

    /**
     * Set data display on graph
     * @param filePath to set the display
     */
    public void doAction(String filePath) {
        try {
            this.reference.exportGraph(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
