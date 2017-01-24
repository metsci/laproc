package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;

/**
 * Action to remove a specified GraphableData from graph
 */
public class RemoveFromGraphAction implements IAction<GraphableData<?>> {
	private OutputDataReference reference;

	/**
	 * Default Constructor
	 * @param ref data reference to affect
	 */
	public RemoveFromGraphAction(OutputDataReference ref){
		this.reference = ref;
	}

	/**
	 * Remove data from graph
	 * @param data data to remove
	 */
	public void doAction(GraphableData<?> data) {
		this.reference.deleteGraphableData(data);
	}

}
