package com.metsci.laproc.application;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.tools.ITool;
import com.metsci.laproc.datareference.InputDataReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for a toolbox that stores the data reference and tools for the application
 * Created by porterjc on 12/9/2016.
 */
public abstract class ToolBox {
    private InputDataReference inputReference;
    private OutputDataReference outputReference;
    private List<ITool> tools;

    protected ToolBox(InputDataReference inputReference, OutputDataReference outputReference) {
        this.inputReference = inputReference;
        this.outputReference = outputReference;
        this.tools = new ArrayList<ITool>();
    }

    protected abstract void initializeTools();

    protected void addTool(ITool tool) {
        this.tools.add(tool);
    }

    public List<ITool> getTools() {
        return this.tools;
    }

    public InputDataReference getInputReference() {
        return inputReference;
    }

    public OutputDataReference getOutputReference() {
        return outputReference;
    }

}
