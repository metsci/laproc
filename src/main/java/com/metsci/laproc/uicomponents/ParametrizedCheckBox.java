package com.metsci.laproc.uicomponents;

import com.metsci.laproc.utils.IAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends JCheckBox to easily provide functionality for JCheckBoxes to perform actions
 * Created by robinsat on 12/22/2016.
 */
public class ParametrizedCheckBox<T> extends JCheckBox implements ActionListener {

    /** The object that this checkbox is associated with */
    private T parameter;
    /** The action to perform when this checkbox is selected */
    private IAction<T> actionWhenSelected;
    /** The action to perform when this checkbox is deselected */
    private IAction<T> actionWhenDeselected;

    /**
     * Constructor
     * @param text The display text for the check box
     * @param parameter The parameter to use for the action
     */
    public ParametrizedCheckBox(String text, T parameter) {
        super(text);
        this.parameter = parameter;
    }

    /**
     * Sets this checkbox to perform an action whenever it is selected
     * @param action The action to perform
     */
    public void addActionWhenChecked(IAction<T> action) {
        this.actionWhenSelected = action;
    }

    /**
     * Sets this checkbox to perform an action whenever it is dselected
     * @param action The action to perform
     */
    public void addActionWhenUnchecked(IAction<T> action) {
        this.actionWhenDeselected = action;
    }

    /**
     * Invoked when an action occurs.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if(this.isSelected()) {
            if(this.actionWhenSelected != null)
                this.actionWhenDeselected.doAction(parameter);
        } else {
            if(this.actionWhenDeselected != null) {
                this.actionWhenDeselected.doAction(parameter);
            }
        }
    }
}
