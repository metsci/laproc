package com.metsci.laproc.datareference;

import com.metsci.glimpse.axis.factory.AxisFactory2D;
import com.metsci.glimpse.axis.factory.ConditionalEndsWithAxisFactory2D;
import com.metsci.glimpse.axis.factory.FixedAxisFactory2D;
import com.metsci.glimpse.canvas.FBOGlimpseCanvas;
import com.metsci.glimpse.canvas.NewtSwingGlimpseCanvas;
import com.metsci.glimpse.context.GlimpseTargetStack;
import com.metsci.glimpse.gl.util.GLUtils;
import com.metsci.glimpse.plot.SimplePlot2D;
import com.metsci.glimpse.support.font.FontUtils;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.BasicGraph;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphPoint;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.uicomponents.GraphDisplayer;
import com.metsci.laproc.uicomponents.GraphExporter;
import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.Observable;

import javax.imageio.ImageIO;
import javax.media.opengl.GLOffscreenAutoDrawable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.metsci.glimpse.context.TargetStackUtil.newTargetStack;

/**
 * This reference allows tools to access the raw input data represented as ClassifierDataSets
 * Created by porterjc on 12/14/2016.
 */
public class InputDataReferenceImpl extends Observable implements InputDataReference {
    private List<ClassifierDataSet> evalSets;
    //TODO:using a normal classifier data set to store groups is slow
    private List<ClassifierDataSet> dataSetGroups;
    private HashMap<ClassifierDataSet, GraphableData> dataSetGraphMap;
    private List<TagHeader> tagHeaders;

    /**
     * Constructor for the Default Data Reference
     * @param tagHeaders TagHeaders for the data reference
     */
    public InputDataReferenceImpl(List<TagHeader> tagHeaders) {
        super();
        evalSets = new ArrayList<ClassifierDataSet>();
        dataSetGroups = new ArrayList<ClassifierDataSet>();
        dataSetGraphMap = new HashMap<ClassifierDataSet, GraphableData>();
        this.tagHeaders = tagHeaders;
    }

    public void addEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.add(dataSet);
        notifyObservers();
    }

    public void removeEvalSet(ClassifierDataSet dataSet) {
        this.evalSets.remove(dataSet);
        notifyObservers();
    }

    public List<ClassifierDataSet> getEvaluationSets() {
        return this.evalSets;
    }


	public void addDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.add(dataSetGroup);
		notifyObservers();
	}

	public void addToDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet){
        this.dataSetGraphMap.put(dataSetGroup, graphSet);
        notifyObservers();
    }

    public GraphableData<?> getGraphfromDataSet(ClassifierDataSet dataSetGroup){
        return this.dataSetGraphMap.get(dataSetGroup);
    }

	public void removeDataSetGroup(ClassifierDataSet dataSetGroup){
		this.dataSetGroups.remove(dataSetGroup);
		notifyObservers();
	}

	public List<ClassifierDataSet> getDataSetGroups(){
		return this.dataSetGroups;
	}

	public List<TagHeader> getTagHeaders() {
		return this.tagHeaders;
	}

}
