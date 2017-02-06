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

    /**
     * Default constructor for a toolbox
     * @param inputReference input reference for the toolbox
     * @param outputReference output reference for the toolbox
     */
    protected ToolBox(InputDataReference inputReference, OutputDataReference outputReference) {
        this.inputReference = inputReference;
        this.outputReference = outputReference;
        this.tools = new ArrayList<ITool>();
    }

    /**
     * Initializes the tools for the toolbox
     */
    protected abstract void initializeTools();

    /**
     * Adds a tool to the toolbox
     * @param tool tool to add
     */
    protected void addTool(ITool tool) {
        this.tools.add(tool);
    }

    /**
     * Getter for the tools in the toolbox
     * @return tools in the toolbox
     */
    public List<ITool> getTools() {
        return this.tools;
    }

    /**
     * Getter for the input reference
     * @return input reference
     */
    public InputDataReference getInputReference() {
        return inputReference;
    }

    /**
     * Getter for the output reference
     * @return output reference
     */
    public OutputDataReference getOutputReference() {
        return outputReference;
    }

}
