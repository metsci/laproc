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
    
    private String name;

    public ClassifierDataSet(List<String> givenTags, String name) {
        this.tags = givenTags;
        this.data = new ObjectOpenHashBigSet<DataPoint>();
        this.name = name;
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
    
    public String getName(){
    	return this.name;
    }
    
    public void setName(String name){
    	this.name = name;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}
