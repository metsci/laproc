package com.metsci.laproc.application;

import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.tools.ITool;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for a toolbox that stores the data reference and tools for the application
 * Created by porterjc on 12/9/2016.
 */
public abstract class ToolBox {
    private InputDataReference inputDataReference;
    private OutputDataReference outputDataReference;
    private List<ITool> tools;

    protected ToolBox(InputDataReference inputDataReference, OutputDataReference outputDataReference) {
        this.inputDataReference = inputDataReference;
        this.outputDataReference = outputDataReference;
        this.tools = new ArrayList<ITool>();
    }

    protected abstract void initializeTools();

    protected void addTool(ITool tool) {
        this.tools.add(tool);
    }

    public List<ITool> getTools() {
        return this.tools;
    }

    public InputDataReference getInputDataReference() {
        return inputDataReference;
    }

    public OutputDataReference getOutputDataReference() {
        return outputDataReference;
    }

}
