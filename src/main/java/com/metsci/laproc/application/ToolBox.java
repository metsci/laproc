package com.metsci.laproc.application;

import com.metsci.laproc.display.ITool;
import com.metsci.laproc.plotting.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by porterjc on 12/9/2016.
 */
public abstract class ToolBox {
    private DataReference reference;
    private List<ITool> tools;

    protected ToolBox() {
        this.reference = new DataReference();
        this.tools = new ArrayList<ITool>();
        this.initializeTools();
    }

    protected abstract void initializeTools();

    protected void addTool(ITool tool) {
        this.tools.add(tool);
    }

    public List<ITool> getTools() {
        return this.tools;
    }

    public DataReference getDataReference() {
        return reference;
    }

}
