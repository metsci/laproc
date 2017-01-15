package com.metsci.laproc.datareference;

/**
 * A specialized Observer pattern to deal with DataReference.
 * This version allows DataReference to track which observers need to be notified of which updates.
 * UI components that only need to know about the displayed data do not need to be notified of updates to the
 * evaluation sets.
 * Created by robinsat on 12/17/2016.
 */
public interface DataObserver {

    /**
     * Called whenever a relevant part of DataReference has updated
     * @param reference
     */
    public void update(DataReference reference);
}
