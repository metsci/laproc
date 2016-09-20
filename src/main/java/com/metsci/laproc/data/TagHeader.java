package com.metsci.laproc.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a header with a collection of associated values
 * Created by robinsat on 9/20/2016.
 */
public class TagHeader {

    /** The printable header that describes the values */
    private String name;
    /** The values associated with this tag */
    private Collection<String> tags;

    /**
     * Constructor
     * @param name The name describing a set of tags
     */
    public TagHeader(String name) {
        this.name = name;
        this.tags = new ArrayList<String>();
    }

    /**
     * Getter for the name of this set of tags
     * @return The name describing this set of values
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the set of values pertaining to the data
     * @return The values associated with this header
     */
    public Collection<String> getTags() {
        return this.tags;
    }

    /**
     * Adds a value to associate with this header
     * @param tag The value to associate with this header
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

}
