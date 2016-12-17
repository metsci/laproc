package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;

/**
 * Created by porterjc on 12/7/2016.
 */
public interface ITool {

    int CENTERPOSITION = 0;
    int LEFTPOSITION = 1;
    int BOTTOMPOSITION = 2;

    View getView();

    int getDefaultPosition();

}
