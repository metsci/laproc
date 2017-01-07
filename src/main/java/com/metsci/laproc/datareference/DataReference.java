package com.metsci.laproc.datareference;

import java.util.List;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObservable;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public interface DataReference extends IObservable {
    
    /**
     * Getter for the Graph object
     * @return The Graph object
     */
    Graph getGraph();  
    
    /**
     * Adds a ClassifierDataSet to the collection of Evaluation Sets
     * @param dataSet The set to add
     */
    void addEvalSet(ClassifierDataSet dataSet);

    /**
     * Removes a ClassifierDataSet from the collection of Evaluation Sets
     * @param dataSet The set to remove
     */
    void removeEvalSet(ClassifierDataSet dataSet);
    
    /**
     * Gets a list of all evaluation sets
     * @return A list of all evaluation sets
     */
    List<ClassifierDataSet> getEvaluationSets();

    /**
     * Adds a GraphableData to the graph
     * @param graphSet The set to graph
     * @param display True if the set should be displayed
     */
    void addDataToGraph(GraphableData<?> graphSet, boolean display);

    /**
     * Sets the given data set to be displayed or hidden
     * @param graphSet The set to update
     * @param display True if the set should be displayed
     */
    void setDataDisplayOnGraph(GraphableData<?> graphSet, boolean display);
    
    /**
     * Removes a GraphableData from the graph
     * @param graphSet The set to remove
     */
    void removeDataFromGraph(GraphableData<?> graphSet);

    /**
     * Replace a given graphable data set on the graph with a new graphable data set
     * @param graphSet old graphable data set to replace
     * @param newGraphSet new graphable data set used to replace the old data set
     */
    void replaceDataOnGraph(GraphableData<?> graphSet, GraphableData<?> newGraphSet);

    /**
     * Add a data set group to the data reference
     * @param dataSetGroup new group to add
     */
	void addDataSetGroup(ClassifierDataSet dataSetGroup);

    /**
     * Remove a data set group from the data reference
     * @param dataSetGroup group to remove
     */
    void removeDataSetGroup(ClassifierDataSet dataSetGroup);

    /**
     * Getter from the data set groups in the data reference
     * @return data set groups
     */
    List<ClassifierDataSet> getDataSetGroups();

    /**
     * Add a data set group/graph set pair to the map that associates the two kinds of sets
     * @param dataSetGroup data set to associate with the graph set
     * @param graphSet graph set to associate with the data set
     */
    void addToDataSetGraphMap(ClassifierDataSet dataSetGroup, GraphableData<?> graphSet);

    /**
     * Get the associated graph set for a given data set group
     * @param dataSetGroup data set group to get the associate graph for
     * @return associated graph for the data set group
     */
    GraphableData<?> getGraphfromDataSet(ClassifierDataSet dataSetGroup);

    /**
     * Getter for the tag headers in the data reference
     * @return tag headers
     */
	List<TagHeader> getTagHeaders();
	
}
