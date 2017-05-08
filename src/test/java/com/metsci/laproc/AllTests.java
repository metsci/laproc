package com.metsci.laproc;

import com.metsci.laproc.action.*;
import com.metsci.laproc.data.AllDataTest;
import com.metsci.laproc.datareference.AllDataReferenceTest;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.pointmetrics.MetricsTest;
import com.metsci.laproc.uicomponents.AllUIComponentsTest;
import com.metsci.laproc.utils.AllUtilsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all test
 * Created by malinocr on 1/18/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllActionTest.class,
        AllDataTest.class,
        AllDataReferenceTest.class,
        AllPlottingTest.class,
        MetricsTest.class,
        AllUIComponentsTest.class,
        AllUtilsTest.class,
        AppTest.class})
public class AllTests {
}
