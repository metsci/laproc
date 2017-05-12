package com.metsci.laproc.utils;

/**
 * Used to interact with IAction implementations
 * Created by robinsat on 12/17/2016.
 */
public interface IActionReceiver<T> {
    /**
     * Perform some action in response to the result of some IAction object
     * @param argument The argument to use for this action function
     */
    void respondToAction(T argument);
}
