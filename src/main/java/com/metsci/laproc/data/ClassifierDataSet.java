package com.metsci.laproc.data;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A class wrapping a set of data that has been classified
 * Created by robinsat on 9/20/2016.
 */
public class ClassifierDataSet implements Iterable<DataPoint> {

    /** Represents the set of tags used to categorize this data */
    private List<String> tags;

    /** The actual data set */
    private ObjectOpenHashBigSet<DataPoint> data;

    /** Name of the classifier data set */
    private String name;

    /** Set operations that have been used to create this classifier data set */
    private String setOperations;

    /**
     * Constructor for a classifer data set
     * @param givenTags tags that uniquely identify the classifier data set (if it is an evaluation set)
     * @param name name of the classifier data set
     */
    public ClassifierDataSet(List<String> givenTags, String name) {
        this.tags = givenTags;
        this.data = new ObjectOpenHashBigSet<DataPoint>();
        this.name = name;
        this.setOperations = "";
    }

    /**
     * Adds a point to the data set
     * @param point The point to add
     */
    public void add(DataPoint point) {
        this.data.add(point);
    }

    /**
     * Returns the complete set of data points.
     * @return
     */
    public Set<DataPoint> getAllPoints() {
        return this.data;
    }

    /**
     * Getter for an iterable set of points;
     * @return An iterable set of points;
     */
    public Iterator<DataPoint> iterator() {
        return this.data.iterator();
    }

    /**
     * Getter for the set of tags used to categorize this data
     * @return The set of tags used to categorize this data
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Getter for the name of the data set
     * @return the name of the data set
     */
    public String getName(){
    	return this.name;
    }

    /**
     * Setter for the name of the data set
     * @param name new name of the data set
     */
    public void setName(String name){
    	this.name = name;
    }

    /**
     * Getter for the set operations of the data set
     * @return the set operations of the data set
     */
    public String getSetOperations(){
        return this.setOperations;
    }

    /**
     * Setter for the set operations of the data set
     * @param setOperations new set operations of the data set
     */
    public void setSetOperation(String setOperations){
        this.setOperations = setOperations;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
