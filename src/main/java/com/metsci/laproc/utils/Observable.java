package com.metsci.laproc.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by robinsat on 12/16/2016.
 */
public class Observable implements IObservable {

    private Collection<IObserver> observers;

    public Observable() {
        this.observers = new ArrayList<IObserver>();
    }
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Object object) {
        for(IObserver observer : this.observers) {
            observer.update(this);
        }
    }
}
