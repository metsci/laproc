package com.metsci.laproc.uicomponents;

/**
 * Test Suit for all UIComponents
 * Created by malinocr on 3/12/2017.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all test
 * Created by malinocr on 1/18/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataSetTableCheckBoxListenerTest.class,
        DataSetTableModelTest.class,
        DataSetTableTest.class
})
public class AllUIComponentsTest {
}
