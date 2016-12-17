package com.metsci.laproc.application;

import com.metsci.laproc.data.ClassifierDataSet;

/**
 * This class represents the highest level of an application.
 * Created by robinsat on 12/16/2016.
 */
public class Application {

    /** The Toolbox that provides all features in this application */
    private ToolBox globalToolBox;
    /** A global reference to the relevant data that is used by Tools */
    private DataReference globalDataReference;

    private ClassifierDataSet baseInputSet;

    public Application(ClassifierDataSet baseInputSet) {
        this.baseInputSet = baseInputSet;
        globalToolBox = new ToolBox();
        globalDataReference = new DataReference();
    }

    public void run() {
        //TODO create a window, initialize layout, load tool configuration 
    }
}
