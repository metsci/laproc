package com.metsci.laproc.ActionHandlers;

/**
 * Created by porterjc on 12/14/2016.
 */
public interface Action<T> {
    void doAction(T argument);
}
