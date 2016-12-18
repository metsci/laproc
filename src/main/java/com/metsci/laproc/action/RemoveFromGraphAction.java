package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.utils.IAction;

public class RemoveFromGraphAction implements IAction<DataSetTable> {
	private DataReference reference;
	
	public RemoveFromGraphAction(DataReference ref){
		this.reference = ref;
	}

	public void doAction(DataSetTable dataSetTable) {
		for(GraphableData<?> data : dataSetTable.getSelectedValues()){
			this.reference.removeDataFromGraph(data);
		}
	}

}
