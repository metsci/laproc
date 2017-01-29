package com.metsci.laproc.action;

import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests DisplayGraphDataAction
 * Created by malinocr on 1/21/2017.
 */
public class DisplayGraphDataActionTest {
    @Test
    public void DoActionTest(){
        OutputDataReference ref = EasyMock.strictMock(OutputDataReference.class);
        GraphableData graphableData = EasyMock.strictMock(GraphableData.class);
        ref.showData(graphableData);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(ref);
        DisplayGraphDataAction action = new DisplayGraphDataAction(ref);
        action.doAction(graphableData);
        EasyMock.verify(ref);
    }
}
