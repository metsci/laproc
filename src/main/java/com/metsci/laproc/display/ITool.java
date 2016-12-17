package com.metsci.laproc.display;

import com.metsci.glimpse.docking.View;

/**
 * Created by porterjc on 12/7/2016.
 */
public interface ITool {

    int CENTERPOSITION = 0;
    int LEFTPOSITION = 1;
    int BOTTOMPOSITION = 2;

    /**
     * Returns a View that can be displayed in a Tile
     * @return
     */
    View getView();

    /**
     * Returns default position of a tile implementation
     * @return
     */
    int getDefaultPosition();

}
