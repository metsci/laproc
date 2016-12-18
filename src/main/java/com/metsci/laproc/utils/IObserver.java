package com.metsci.laproc.utils;

/**
 * Implementations of this can "observe" an object. Whenever the observed object is updated,
 * this object performs some function
 * Created by robinsat on 12/16/2016.
 */
public interface IObserver<T extends IObservable> {

    /**
     * Method called whenever the observed object updates
     * @param object The observed object
     */
    void update(T object);
}
