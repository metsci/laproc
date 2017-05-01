package com.metsci.laproc.datareference;

import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.pointmetrics.ParametricFunction;
import com.metsci.laproc.utils.IObserver;
import org.easymock.EasyMock;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TestOutputDataReferenceImpl
 * Created by malinocr on 2/10/2017.
 */
public class OutputDataReferenceImplTest {
    @Test
    public void TestAddAndRemoveData(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        GraphableData dataSet1 = EasyMock.strictMock(GraphableData.class);
        GraphableData dataSet2 = EasyMock.strictMock(GraphableData.class);
        OutputDataReferenceImpl graphRef = new OutputDataReferenceImpl();

        observer.update(graphRef);
        EasyMock.expectLastCall().times(6);
        EasyMock.replay(observer,dataSet1,dataSet2);

        graphRef.addObserver(observer);
        assertEquals(0, numElementsInIterator(graphRef.getDisplayedData()));

        graphRef.addGraphableData(dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));

        graphRef.addGraphableData(dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.addGraphableData(dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.deleteGraphableData(dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.addGraphableData(dataSet1);
        graphRef.deleteGraphableData(dataSet2);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));

        graphRef.deleteGraphableData(dataSet1);
        assertEquals(0, numElementsInIterator(graphRef.getDisplayedData()));

        graphRef.deleteGraphableData(dataSet1);
        assertEquals(0, numElementsInIterator(graphRef.getDisplayedData()));

        EasyMock.verify(observer,dataSet1,dataSet2);
    }

    @Test
    public void TestShowHideData(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        GraphableData dataSet1 = EasyMock.strictMock(GraphableData.class);
        GraphableData dataSet2 = EasyMock.strictMock(GraphableData.class);
        GraphableData dataSet3 = EasyMock.strictMock(GraphableData.class);
        OutputDataReferenceImpl graphRef = new OutputDataReferenceImpl();

        observer.update(graphRef);
        EasyMock.expectLastCall().times(6);
        EasyMock.replay(observer,dataSet1,dataSet2,dataSet3);

        graphRef.addObserver(observer);
        graphRef.addGraphableData(dataSet1);
        graphRef.addGraphableData(dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getAllData()));
        assertTrue(graphRef.isDisplayed(dataSet1));
        assertTrue(graphRef.isDisplayed(dataSet2));

        graphRef.hideData(dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getAllData()));
        assertFalse(graphRef.isDisplayed(dataSet1));
        assertTrue(graphRef.isDisplayed(dataSet2));

        graphRef.hideData(dataSet2);
        assertEquals(0, numElementsInIterator(graphRef.getDisplayedData()));
        assertFalse(graphRef.isDisplayed(dataSet1));
        assertFalse(graphRef.isDisplayed(dataSet2));

        graphRef.hideData(dataSet2);
        assertEquals(0, numElementsInIterator(graphRef.getDisplayedData()));

        graphRef.showData(dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getAllData()));
        assertTrue(graphRef.isDisplayed(dataSet1));
        assertFalse(graphRef.isDisplayed(dataSet2));

        graphRef.showData(dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));
        assertTrue(graphRef.isDisplayed(dataSet1));
        assertTrue(graphRef.isDisplayed(dataSet2));

        graphRef.showData(dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.showData(dataSet3);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.showData(null);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.hideData(dataSet3);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        graphRef.hideData(null);
        assertEquals(2, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));

        EasyMock.verify(observer,dataSet1,dataSet2,dataSet3);
    }

    @Test
    public void TestReplaceData(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        GraphableData dataSet1 = EasyMock.strictMock(GraphableData.class);
        GraphableData dataSet2 = EasyMock.strictMock(GraphableData.class);
        GraphableData dataSet3 = EasyMock.strictMock(GraphableData.class);
        OutputDataReferenceImpl graphRef = new OutputDataReferenceImpl();

        observer.update(graphRef);
        EasyMock.expectLastCall().times(5);
        EasyMock.replay(observer,dataSet1,dataSet2,dataSet3);

        graphRef.addObserver(observer);
        graphRef.addGraphableData(dataSet1);
        graphRef.addGraphableData(dataSet2);
        graphRef.hideData(dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getAllData()));

        graphRef.replaceDataOnGraph(dataSet1, dataSet3);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet2, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet3, graphRef.getAllData()));

        graphRef.replaceDataOnGraph(dataSet2, dataSet1);
        assertEquals(1, numElementsInIterator(graphRef.getDisplayedData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getDisplayedData()));
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet3, graphRef.getAllData()));

        graphRef.replaceDataOnGraph(dataSet3, dataSet1);
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet3, graphRef.getAllData()));

        graphRef.replaceDataOnGraph(dataSet2, dataSet2);
        assertEquals(2, numElementsInIterator(graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet1, graphRef.getAllData()));
        assertTrue(containedInIterator(dataSet3, graphRef.getAllData()));

        EasyMock.verify(observer,dataSet1,dataSet2,dataSet3);
    }

    @Test
    public void TestAxis(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        ParametricFunction function1 = EasyMock.strictMock(ParametricFunction.class);
        ParametricFunction function2 = EasyMock.strictMock(ParametricFunction.class);
        OutputDataReferenceImpl graphRef = new OutputDataReferenceImpl();

        observer.update(graphRef);
        EasyMock.expectLastCall().times(4);
        EasyMock.replay(observer,function1,function2);

        graphRef.addObserver(observer);

        graphRef.updateGraphWithAxes(function1,function1);
        graphRef.updateGraphWithAxes(function1,function2);
        graphRef.updateGraphWithAxes(function1,function1);
        graphRef.updateGraphWithAxes(function2,function1);
        graphRef.updateGraphWithAxes(function2,function1);

        //TODO:Test getAxisFunctions

        EasyMock.verify(observer,function1,function2);
    }

    private int numElementsInIterator(Iterable iterable){
        int count = 0;
        for(Object o : iterable){
            count++;
        }
        return count;
    }

    private boolean containedInIterator(Object object, Iterable iterable){
        boolean found = false;
        for(Object o : iterable){
            if(o.equals(object)){
                found = true;
                break;
            }
        }
        return found;
    }
}
