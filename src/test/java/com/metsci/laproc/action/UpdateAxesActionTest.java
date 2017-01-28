package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests UpdateAxisAction
 * Created by malinocr on 1/21/2017.
 */
public class UpdateAxesActionTest {
    @Test
    public void DoActionTest(){
        DataReference ref = EasyMock.strictMock(DataReference.class);
        ParametricFunction xAxis = EasyMock.strictMock(ParametricFunction.class);
        ParametricFunction yAxis = EasyMock.strictMock(ParametricFunction.class);
        ref.setAxisFunctionOnGraph(xAxis,yAxis);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(ref);
        UpdateAxesAction action = new UpdateAxesAction(ref);
        action.doAction(new ParametricFunction[]{xAxis, yAxis});
        EasyMock.verify(ref);
    }
}
