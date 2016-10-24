package com.metsci.laproc.data;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class representing a set of data that has been classified
 * Created by robinsat on 9/20/2016.
 */
public class ClassifierDataSet extends ObjectOpenHashBigSet<DataPoint> {

    /** Represents the set of tags used to categorize this data */
    private Collection<TagHeader> tags;

    /**
     * Getter for an iterable set of points;
     * @return An iterable set of points;
     */
    public Iterator<DataPoint> getAllPoints() {
        return this.iterator();
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
