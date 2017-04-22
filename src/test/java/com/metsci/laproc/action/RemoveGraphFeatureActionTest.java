package com.metsci.laproc.action;

import com.metsci.laproc.tools.GraphDisplayManager;
import com.metsci.laproc.uicomponents.graphfeatures.GraphFeature;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests RemoveCompositeFunctionAction
 * Created by malinocr on 1/21/2017.
 */
public class RemoveGraphFeatureActionTest {
    @Test
    public void DoActionTest(){
        GraphDisplayManager manager = EasyMock.strictMock(GraphDisplayManager.class);
        GraphFeature feature = EasyMock.strictMock(GraphFeature.class);
        manager.disableGraphFeature(feature);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(manager, feature);
        RemoveGraphFeatureAction action = new RemoveGraphFeatureAction(manager);
        action.doAction(feature);
        EasyMock.verify(manager, feature);
    }
}
