package com.metsci.laproc.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An abstract class providing the basic functionality for the observer pattern 
 * Created by robinsat on 12/16/2016.
 */
public class Observable implements IObservable {

    /** The observers registered to observe this object */
    private Collection<IObserver<Observable>> observers;

    /**
     * Constructor
     */
    public Observable() {
        this.observers = new ArrayList<IObserver<Observable>>();
    }

    /**
     * Adds an observer to be notified of updates to this object
     * @param observer The observer to add
     */
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Removes an observer from the list
     * @param observer The observer to remove
     */
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifies all observers of an update to this object
     */
    public void notifyObservers() {
        for(IObserver<Observable> observer : this.observers) {
            observer.update(this);
        }
    }
}
