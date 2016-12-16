package com.metsci.laproc.utils;

/**
 * Created by robinsat on 12/16/2016.
 */
public interface IObservable<T extends IObservable> {

    void addObserver(IObserver<T> observer);
    void removeObserver(IObserver<T> observer);
    void notifyObservers(T object);
}
