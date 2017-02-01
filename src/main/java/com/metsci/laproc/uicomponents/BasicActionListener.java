package com.metsci.laproc.uicomponents;

import com.metsci.laproc.utils.IAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener that can be added to a component and perform an Action
 * Created by robinsat on 12/22/2016.
 */
public class BasicActionListener<T> implements ActionListener {

    /** Tha IAction to perform when actionPerformed is invoked */
    private IAction<T> action;
    /** The parameter to use for the action */
    private T parameter;

    /** Constructor
     * @param action The IAction to perform when actionPerformed is invoked
     * @param parameter The parameter to use for the action
     */
    public BasicActionListener(IAction action, T parameter) {
        this.action = action;
        this.parameter = parameter;
    }

    /**
     * Invoked when an action occurs.
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        action.doAction(parameter);
    }
}
