package com.metsci.laproc.datareference;

import java.util.List;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.plotting.GraphableDataSet;

/**
 * This reference allows tools to access the graph and associated data
 * Created by porterjc on 12/14/2016.
 */
public interface DataReference {
    
    /**
     * Getter for the currently displayed Graph object
     * @return The Graph object
     */
    Graph getGraph();


    //TODO this may change
    void setGraph(Graph graph);

    /**
     * Getter for the currently displayed GraphableDataSet object
     * @return the currently displayed GraphableDataSet object
     */
    GraphableDataSet getGraphableDataSet();
    
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
     * Getter for the tag headers in the data reference
     * @return tag headers
     */
	List<TagHeader> getTagHeaders();


    /* ---------- Observer functions ---------- */

    /**
     * Allows some DataObserver to observe updates to the Graph
     * @param observer The object that will observe updates to the Graph
     */
    void addGraphObserver(DataObserver observer);

    /**
     * Allows some DataObserver to observe updates to the GraphableDataSet
     * @param observer The object that will observe updates to the GraphableDataSet
     */
    void addGraphableDataSetObserver(DataObserver observer);

    /**
     * Allows some DataObserver to observe updates to the DataSetGroup
     * @param observer The object that will observe updates to the DataSetGroup
     */
    void addDataSetGroupsObserver(DataObserver observer);

    /**
     * Allows some DataObserver to observe updates to the Eval sets
     * @param observer The object that will observe updates to the Eval sets
     */
    void addEvalSetObserver(DataObserver observer);

    /**
     * Called whenever Graph is updated
     */
    void updateGraph();

    /**
     * Called whenever the GraphableDataSet is updated
     */
    void updateGraphableDataSet();

    /**
     * Called whenever the DataSetGroups are updated
     */
    void updateDataSetGroups();

    /**
     * Called whenever the Eval sets are updated
     */
    void updateEvalSets();
	
}
