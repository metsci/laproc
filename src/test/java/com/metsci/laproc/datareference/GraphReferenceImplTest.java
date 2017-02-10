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
        EasyMock.replay(ref,graph);
        GraphReferenceImpl graphRef = new GraphReferenceImpl(ref);
        assertEquals(graph, graphRef.getGraph());
        EasyMock.verify(ref,graph);
    }

    @Test
    public void TestUpdate(){
        OutputDataReference ref = EasyMock.niceMock(OutputDataReference.class);
        IObserver observer = EasyMock.strictMock(IObserver.class);
        Graph graph = EasyMock.niceMock(Graph.class);
        Graph graph1 = EasyMock.niceMock(Graph.class);
        EasyMock.expect(ref.createGraph()).andReturn(graph);
        EasyMock.expect(ref.createGraph()).andReturn(graph1);
        EasyMock.replay(ref,graph,graph1);

        GraphReferenceImpl graphRef = new GraphReferenceImpl(ref);

        observer.update(graphRef);
        EasyMock.expectLastCall().times(1);
        EasyMock.replay(observer);

        graphRef.addObserver(observer);
        graphRef.update(ref);
        assertEquals(graph1, graphRef.getGraph());
        EasyMock.verify(ref,graph,graph1,observer);
    }
}
