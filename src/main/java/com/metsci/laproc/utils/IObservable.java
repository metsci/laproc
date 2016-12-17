package com.metsci.laproc.utils;

/**
 * Created by robinsat on 12/16/2016.
 */
public interface IObservable {

    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
