package com.metsci.laproc.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Observable class
 * Created by malinocr on 1/18/2017.
 */
public class ObservableTest {
    //TODO:Should we test null?
    @Test
    public void TestNotifyObserver(){
        MockObserver mockObserver = new MockObserver();
        assertFalse(mockObserver.isUpdated());

        Observable observable = new Observable();
        observable.addObserver(mockObserver);
        observable.notifyObservers();

        assertTrue(mockObserver.isUpdated());
    }

    @Test
    public void TestNotifyRemovedObserver(){
        MockObserver mockObserver = new MockObserver();
        assertFalse(mockObserver.isUpdated());

        Observable observable = new Observable();
        observable.addObserver(mockObserver);
        observable.removeObserver(mockObserver);
        observable.notifyObservers();

        assertFalse(mockObserver.isUpdated());
    }

    private class MockObserver implements IObserver<Observable>{
        boolean updated = false;
        public MockObserver(){
        }

        public void update(Observable object) {
            this.updated = true;
        }

        public boolean isUpdated(){
            return this.updated;
        }
    }
}
