package com.metsci.laproc.action;

import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Action to add a specified graphable data to the graph
 */
public class AddToGraphAction implements IAction<GraphableData<?>> {
	private OutputDataReference reference;

	/**
	 * Default constructor
	 * @param ref data reference to affect
	 */
	//TODO where is this used?
	public AddToGraphAction(OutputDataReference ref){
		this.reference = ref;
	}

	/**
	 * Add data to graph
	 * @param data to add
	 */
	public void doAction(GraphableData<?> data) {
		this.reference.addDataToGraph(data, true);
	}

}
