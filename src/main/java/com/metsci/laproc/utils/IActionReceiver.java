package com.metsci.laproc.utils;

/**
 * Created by robinsat on 12/17/2016.
 */
public interface IActionReceiver<T> {
    /**
     * Perform some action in response to the result of some IAction object
     *
     * @param argument
     */
    void respondToAction(T argument);
}
