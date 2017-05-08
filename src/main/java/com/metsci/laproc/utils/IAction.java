package com.metsci.laproc.utils;

/**
 * Used in place of specific action listener implementations.
 * Created by porterjc on 12/14/2016.
 */
public interface IAction<T> {
    /**
     * Perform some action for some on click interaction
     * @param argument The argument to use for this action function
     */
    void doAction(T argument);
}
