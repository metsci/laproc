package com.metsci.laproc.datareference;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all data reference tests
 * Created by malinocr on 3/12/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GraphReferenceImplTest.class,
        InputDataReferenceImplTest.class,
        OutputDataReferenceImplTest.class})
public class AllDataReferenceTest {
}
