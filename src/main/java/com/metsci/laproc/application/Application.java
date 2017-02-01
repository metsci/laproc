package com.metsci.laproc.application;

import java.util.List;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.InputDataReferenceImpl;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.datareference.OutputDataReferenceImpl;
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
    /** A global reference to the input data (represented as ClassifierDataSets) that is used by Tools */
    private InputDataReference globalInputDataReference;
    /** A global reference to the output data (represented as GraphableData) that is used by Tools */
    private OutputDataReference globalOutputDataReference;


    public Application(List<ClassifierDataSet> dataSets, List<TagHeader> tagHeaders) {
        globalInputDataReference = new InputDataReferenceImpl(tagHeaders);
        globalOutputDataReference = new OutputDataReferenceImpl();
        for(ClassifierDataSet data : dataSets){
        	globalInputDataReference.addEvalSet(data);
        }
        globalToolBox = new DefaultToolBox(globalInputDataReference, globalOutputDataReference);
    }

    public void run() {
        globalToolBox.initializeTools();
        Window window = new BasicWindow();

        for(ITool t : globalToolBox.getTools()) {
            window.addViewToTile(t.getView(), t.getDefaultPosition());
        }

        window.display();
    }
}
