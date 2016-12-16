package com.metsci.laproc.utils;

/**
 * Created by robinsat on 12/16/2016.
 */
public interface IObserver<T extends IObservable> {

    void update(T object);
}
