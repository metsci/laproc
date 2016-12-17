package com.metsci.laproc.application;

import com.metsci.laproc.display.ITool;
import com.metsci.laproc.plotting.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by porterjc on 12/9/2016.
 */
public class ToolBox {
    private DataReference reference;
    private List<ITool> tools;

    protected ToolBox() {
        this.reference = new DataReference();
        this.tools = new ArrayList<ITool>();
    }

    public DataReference getDataReference() {
        return reference;
    }

}
