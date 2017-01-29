package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
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
        OutputDataReference ref = EasyMock.strictMock(OutputDataReference.class);
        ParametricFunction xAxis = EasyMock.strictMock(ParametricFunction.class);
        ParametricFunction yAxis = EasyMock.strictMock(ParametricFunction.class);
        ref.useAxisFunctions(xAxis, yAxis);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(ref);
        UpdateAxesAction action = new UpdateAxesAction(ref);
        action.doAction(new ParametricFunction[]{xAxis, yAxis});
        EasyMock.verify(ref);
    }
}
