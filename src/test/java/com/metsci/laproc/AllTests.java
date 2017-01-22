package com.metsci.laproc;

import com.metsci.laproc.action.*;
import com.metsci.laproc.plotting.BasicGraphTest;
import com.metsci.laproc.plotting.ROCCurveTest;
import com.metsci.laproc.pointmetrics.MetricsTest;
import com.metsci.laproc.uicomponents.DataSetTableModelTest;
import com.metsci.laproc.uicomponents.DataSetTableTest;
import com.metsci.laproc.utils.ObservableTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by malinocr on 1/18/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AddToGraphActionTest.class,
        DisplayGraphDataActionTest.class,
        HideGraphDataActionTest.class,
        RemoveFromGraphActionTest.class,
        UpdateAxesActionTest.class,
        ROCCurveTest.class,
        MetricsTest.class,
        AppTest.class,
        ObservableTest.class,
        DataSetTableModelTest.class,
        DataSetTableTest.class})
public class AllTests {

}
