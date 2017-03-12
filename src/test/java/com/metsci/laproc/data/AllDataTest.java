package com.metsci.laproc.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all data tests
 * Created by malinocr on 3/12/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClassifierDataSetTest.class,
        DataPointImplTest.class,
        TagHeaderTest.class})

public class AllDataTest {
}
