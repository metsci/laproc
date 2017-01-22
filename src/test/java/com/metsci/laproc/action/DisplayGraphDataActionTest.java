package com.metsci.laproc.action;

import com.metsci.laproc.datareference.DataReference;
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
        DataReference ref = EasyMock.strictMock(DataReference.class);
        GraphableData graphableData = EasyMock.strictMock(GraphableData.class);
        ref.setDataDisplayOnGraph(graphableData,true);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(ref);
        DisplayGraphDataAction action = new DisplayGraphDataAction(ref);
        action.doAction(graphableData);
        EasyMock.verify(ref);
    }
}
