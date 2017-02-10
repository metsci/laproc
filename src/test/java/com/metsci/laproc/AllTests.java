package com.metsci.laproc;

import com.metsci.laproc.action.*;
import com.metsci.laproc.data.ClassifierDataSetTest;
import com.metsci.laproc.data.DataPointImplTest;
import com.metsci.laproc.data.TagHeaderTest;
import com.metsci.laproc.datareference.GraphReference;
import com.metsci.laproc.datareference.GraphReferenceImplTest;
import com.metsci.laproc.datareference.TestInputDataReferenceImpl;
import com.metsci.laproc.plotting.CompositeFunctionTest;
import com.metsci.laproc.plotting.GraphableDataTest;
import com.metsci.laproc.plotting.ROCCurveTest;
import com.metsci.laproc.pointmetrics.MetricsTest;
import com.metsci.laproc.uicomponents.DataSetTableModelTest;
import com.metsci.laproc.uicomponents.DataSetTableTest;
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
        AddCompositeFunctionActionTest.class,
        DisplayGraphDataActionTest.class,
        HideGraphDataActionTest.class,
        RemoveCompositeFunctionActionTest.class,
        UpdateAxesActionTest.class,
        ClassifierDataSetTest.class,
        DataPointImplTest.class,
        TagHeaderTest.class,
        GraphReferenceImplTest.class,
        TestInputDataReferenceImpl.class,
        CompositeFunctionTest.class,
        GraphableDataTest.class,
        ROCCurveTest.class,
        MetricsTest.class,
        DataSetTableModelTest.class,
        DataSetTableTest.class,
        FiltererTest.class,
        ObservableTest.class,
        UtilsTest.class,
        AppTest.class})
public class AllTests {
}
