package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.utils.IAction;

/**
 * Action to add a specified graphable data to the graph
 */
public class AddToGraphAction implements IAction<GraphableData<?>> {
	private DataReference reference;

	/**
	 * Default constructor
	 * @param ref data reference to affect
	 */
	public AddToGraphAction(DataReference ref){
		this.reference = ref;
	}

	/**
	 * Add data to graph
	 * @param data to add
	 */
	public void doAction(GraphableData<?> data) {
		//TODO Is this the appropriate way to do this? I'm not sure where this action is actually used
		this.reference.getGraphableDataSet().addGraphableData(data);
	}

}
