package com.metsci.laproc.utils;

/**
 * Allows implementations to be "observed," meaning that they may notify their observers that they have updated
 * Created by robinsat on 12/16/2016.
 */
public interface IObservable {

    /**
     * Adds an observer to be notified of updates to this object
     * @param observer The observer to add
     */
    void addObserver(IObserver observer);

    /**
     * Removes an observer from the list
     * @param observer The observer to remove
     */
    void removeObserver(IObserver observer);

    /**
     * Notifies all observers of an update to this object
     */
    void notifyObservers();
}
