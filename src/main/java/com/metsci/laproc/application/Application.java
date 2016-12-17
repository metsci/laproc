package com.metsci.laproc.application;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.uicomponents.BasicWindow;
import com.metsci.laproc.tools.ITool;
import com.metsci.laproc.uicomponents.Window;
import com.metsci.laproc.tools.DataReference;

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
        globalDataReference = new DataReference();
        globalDataReference.addEvalSet(this.baseInputSet);
        globalToolBox = new DefaultToolBox(globalDataReference);
    }

    public void run() {
        //TODO create a window, initialize layout, load tool configuration
        globalToolBox.initializeTools();
        Window window = new BasicWindow();

        for(ITool t : globalToolBox.getTools()) {
            window.addViewToTile(t.getView(), t.getDefaultPosition());
        }

        window.display();
    }
}
