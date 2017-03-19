package com.metsci.laproc;

import com.metsci.laproc.action.*;
import com.metsci.laproc.data.AllDataTest;
import com.metsci.laproc.data.ClassifierDataSetTest;
import com.metsci.laproc.data.DataPointImplTest;
import com.metsci.laproc.data.TagHeaderTest;
import com.metsci.laproc.datareference.AllDataReferenceTest;
import com.metsci.laproc.datareference.GraphReferenceImplTest;
import com.metsci.laproc.datareference.InputDataReferenceImplTest;
import com.metsci.laproc.datareference.OutputDataReferenceImplTest;
import com.metsci.laproc.plotting.*;
import com.metsci.laproc.pointmetrics.MetricsTest;
import com.metsci.laproc.uicomponents.AllUIComponentsTest;
import com.metsci.laproc.uicomponents.DataSetTableCheckBoxListenerTest;
import com.metsci.laproc.uicomponents.DataSetTableModelTest;
import com.metsci.laproc.uicomponents.DataSetTableTest;
import com.metsci.laproc.utils.AllUtilsTest;
import com.metsci.laproc.utils.FiltererTest;
import com.metsci.laproc.utils.ObservableTest;
import com.metsci.laproc.utils.UtilsTest;
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
