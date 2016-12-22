package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.utils.IObservable;

import java.util.Iterator;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public interface DataReference extends IObservable {

    /**
     * Sets the reference to use a new Graph object
     * @param graph The new Graph object to use
     */
    void newGraph(Graph graph);

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
     * Getter for the Graph object
     * @return The Graph object
     */
    Graph getGraph();

    /**
     * Gets a list of all evaluation sets
     * @return A list of all evaluation sets
     */
    Iterator<ClassifierDataSet> getEvaluationSets();

}
