package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.Graph;
import com.metsci.laproc.utils.IObserver;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test GraphReferenceImpl
 * Created by malinocr on 2/7/2017.
 */
public class GraphReferenceImplTest {
    @Test
    public void TestGetGraph(){
        OutputDataReference ref = EasyMock.niceMock(OutputDataReference.class);
        Graph graph = EasyMock.niceMock(Graph.class);
        EasyMock.expect(ref.createGraph()).andReturn(graph);
        EasyMock.replay(ref);
        GraphReference graphRef = new GraphReferenceImpl(ref);
        assertEquals(graph, graphRef.getGraph());
        EasyMock.verify(ref);
    }

    @Test
    public void TestUpdate(){
        OutputDataReference ref = EasyMock.niceMock(OutputDataReference.class);
        IObserver observer = EasyMock.strictMock(IObserver.class);
        Graph graph = EasyMock.niceMock(Graph.class);
        EasyMock.expect(ref.createGraph()).andReturn(graph);
        EasyMock.replay(ref);
        GraphReference graphRef = new GraphReferenceImpl(ref);
        graphRef.addObserver(observer);
        assertEquals(graph, graphRef.getGraph());
        EasyMock.verify(ref);
    }
}
