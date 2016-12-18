package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.DataSetTable;
import com.metsci.laproc.utils.IAction;

public class AddToGraphAction implements IAction<DataSetTable> {
	private DataReference reference;
	
	public AddToGraphAction(DataReference ref){
		this.reference = ref;
	}

	public void doAction(DataSetTable dataSetTable) {
		for(GraphableData<?> data : dataSetTable.getSelectedValues()){
			this.reference.addDataToGraph(data);
		}
	}

}
