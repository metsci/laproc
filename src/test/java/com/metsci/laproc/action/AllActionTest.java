package com.metsci.laproc.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all action tests
 * Created by malinocr on 3/12/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddGraphFeatureActionTest.class,
        CreateNewDataSetActionTest.class,
        DisplayGraphDataActionTest.class,
        FilterDataSetActionTest.class,
        HideGraphDataActionTest.class,
        RemoveGraphFeatureActionTest.class,
        UpdateAxesActionTest.class})
public class AllActionTest {
}
