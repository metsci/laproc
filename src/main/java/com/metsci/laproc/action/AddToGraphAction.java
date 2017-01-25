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
	public AddToGraphAction(OutputDataReference ref){
		this.reference = ref;
	}

	/**
	 * Add data to graph
	 * @param data to add
	 */
	public void doAction(GraphableData<?> data) {
		//TODO Is this the appropriate way to do this? I'm not sure where this action is actually used
		this.reference.addGraphableData(data);
	}

}
