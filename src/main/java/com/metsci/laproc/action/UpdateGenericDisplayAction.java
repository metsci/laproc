package com.metsci.laproc.action;

import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.utils.IActionReceiver;

/**
 * Generic Action
 * Created by robinsat on 12/17/2016.
 */
public class UpdateGenericDisplayAction<T> implements IAction<T> {

    private IActionReceiver<T> receiver;

    /**
     * Basic constructor that takes a Action Receiver object
     * @param receiver receiver of the action
     */
    public UpdateGenericDisplayAction(IActionReceiver<T> receiver) {
        this.receiver = receiver;
    }

    /**
     * Action to perform on the receiver
     * @param argument argument for the action
     */
    public void doAction(T argument) {
        receiver.respondToAction(argument);
    }
}
