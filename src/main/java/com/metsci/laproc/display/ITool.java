package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;

import java.util.Observer;

/**
 * Created by porterjc on 12/7/2016.
 */
public interface ITool extends Observer {
    public void initialize();
    public View getView();
    public void addAction();
}
