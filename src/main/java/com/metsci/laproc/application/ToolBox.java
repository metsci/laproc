package com.metsci.laproc.application;

import com.metsci.laproc.tools.ITool;
import com.metsci.laproc.tools.DataReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by porterjc on 12/9/2016.
 */
public abstract class ToolBox {
    private DataReference reference;
    private List<ITool> tools;

    protected ToolBox(DataReference reference) {
        this.reference = reference;
        this.tools = new ArrayList<ITool>();
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
