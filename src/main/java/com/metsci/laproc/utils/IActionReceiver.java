package com.metsci.laproc.utils;

/**
 * Created by robinsat on 12/17/2016.
 */
public interface IActionReceiver<T> {
    void respondToAction(T argument);
}
