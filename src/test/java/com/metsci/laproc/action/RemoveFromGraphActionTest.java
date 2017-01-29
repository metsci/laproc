package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests RemoveFromGraphAction
 * Created by malinocr on 1/21/2017.
 */
public class RemoveFromGraphActionTest {
    @Test
    public void DoActionTest(){
        OutputDataReference ref = EasyMock.strictMock(OutputDataReference.class);
        GraphableData graphableData = EasyMock.strictMock(GraphableData.class);
        ref.deleteGraphableData(graphableData);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(ref);
        RemoveFromGraphAction action = new RemoveFromGraphAction(ref);
        action.doAction(graphableData);
        EasyMock.verify(ref);
    }
}
