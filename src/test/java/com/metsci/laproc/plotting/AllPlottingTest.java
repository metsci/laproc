package com.metsci.laproc.plotting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all plotting tests
 * Created by malinocr on 3/12/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasicAxisTest.class,
        CompositeFunctionTest.class,
        GraphableDataTest.class,
        ROCCurveTest.class})
public class AllPlottingTest {
}
