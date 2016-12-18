package com.metsci.laproc.action;

import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.utils.IActionReceiver;

/**
 * Created by robinsat on 12/17/2016.
 */
public class UpdateGenericDisplayAction<T> implements IAction<T> {

    private IActionReceiver<T> receiver;

    /**
     * Basic constuctor that takes a Action Reciever object
     * @param receiver
     */
    public UpdateGenericDisplayAction(IActionReceiver<T> receiver) {
        this.receiver = receiver;
    }

    /**
     *
     * @param argument
     */
    public void doAction(T argument) {
        receiver.respondToAction(argument);
    }
}
