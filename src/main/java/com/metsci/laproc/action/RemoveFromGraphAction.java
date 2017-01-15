package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.utils.IAction;

/**
 * Action to remove a specified GraphableData from graph
 */
public class RemoveFromGraphAction implements IAction<GraphableData<?>> {
	private DataReference reference;

	/**
	 * Default Constructor
	 * @param ref data reference to affect
	 */
	public RemoveFromGraphAction(DataReference ref){
		this.reference = ref;
	}

	/**
	 * Remove data from graph
	 * @param data data to remove
	 */
	public void doAction(GraphableData<?> data) {
		//TODO where does this get used?
		this.reference.getGraphableDataSet().deleteGraphableData(data);
	}

}
