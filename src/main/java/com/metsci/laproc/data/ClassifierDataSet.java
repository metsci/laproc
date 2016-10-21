package com.metsci.laproc.data;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A class wrapping a set of data that has been classified
 * Created by robinsat on 9/20/2016.
 */
public class ClassifierDataSet implements Iterable<DataPoint> {

    /** Represents the set of tags used to categorize this data */
    private Collection<TagHeader> tags;

    /** The actual data set */
    private ObjectOpenHashBigSet<DataPoint> data;

    public ClassifierDataSet() {
        this.tags = new ArrayList<TagHeader>();
        this.data = new ObjectOpenHashBigSet<DataPoint>();
    }

    /**
     * Adds a point to the data set
     * @param point The point to add
     */
    public void add(DataPoint point) {
        this.data.add(point);
    }

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
     * Getter for the set of points with the given tag
     * @param tag The tag to find points for
     * @return The points associated with the given tag
     */
    public Iterator<DataPoint> getAllPointsWithTag(String tag) {
        //TODO implement this
        return null;
    }

    /**
     * Getter for the set of tags used to categorize this data
     * @return The set of tags used to categorize this data
     */
    public Collection<TagHeader> getTags() {
        return this.tags;
    }

}
