package com.metsci.laproc.ActionHandlers;

import com.metsci.laproc.utils.IAction;
import com.metsci.laproc.utils.IActionReceiver;

/**
 * Created by robinsat on 12/17/2016.
 */
public class UpdateGenericDisplayAction<T> implements IAction<T> {

    private IActionReceiver<T> receiver;

    public UpdateGenericDisplayAction(IActionReceiver<T> receiver) {
        this.receiver = receiver;
    }
    public void doAction(T argument) {
        receiver.respondToAction(argument);
    }
}
