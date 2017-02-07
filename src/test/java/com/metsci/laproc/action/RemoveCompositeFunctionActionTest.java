package com.metsci.laproc.action;

import com.metsci.laproc.plotting.CompositeFunction;
import com.metsci.laproc.tools.GraphDisplayManager;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests AddCompositeFunctionAction
 * Created by malinocr on 1/21/2017.
 */
public class RemoveCompositeFunctionActionTest {
    @Test
    public void DoActionTest(){
        GraphDisplayManager manager = EasyMock.strictMock(GraphDisplayManager.class);
        CompositeFunction func = EasyMock.strictMock(CompositeFunction.class);
        manager.disableCompositeFunction(func);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(manager, func);
        RemoveCompositeFunctionAction action = new RemoveCompositeFunctionAction(manager);
        action.doAction(func);
        EasyMock.verify(manager, func);
    }
}
