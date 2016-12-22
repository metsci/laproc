package com.metsci.laproc.application;

import java.util.List;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.datareference.DataReferenceImpl;
import com.metsci.laproc.tools.ITool;
import com.metsci.laproc.uicomponents.BasicWindow;
import com.metsci.laproc.uicomponents.Window;

/**
 * This class represents the highest level of an application.
 * Created by robinsat on 12/16/2016.
 */
public class Application {

    /** The Toolbox that provides all features in this application */
    private ToolBox globalToolBox;
    /** A global reference to the relevant data that is used by Tools */
    private DataReference globalDataReference;


    public Application(List<ClassifierDataSet> dataSets, List<TagHeader> tagHeaders) {
        globalDataReference = new DataReferenceImpl(tagHeaders);
        for(ClassifierDataSet data : dataSets){
        	globalDataReference.addEvalSet(data);
        }
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
