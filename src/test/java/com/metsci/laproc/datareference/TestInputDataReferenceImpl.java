package com.metsci.laproc.datareference;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.TagHeader;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IObserver;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test GraphReferenceImpl
 * Created by malinocr on 2/7/2017.
 */
public class TestInputDataReferenceImpl {
    @Test
    public void TestEvalSets(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        ClassifierDataSet dataSet1 = EasyMock.strictMock(ClassifierDataSet.class);
        ClassifierDataSet dataSet2 = EasyMock.strictMock(ClassifierDataSet.class);
        InputDataReferenceImpl graphRef = new InputDataReferenceImpl(null);

        observer.update(graphRef);
        EasyMock.expectLastCall().times(7);
        EasyMock.replay(observer,dataSet1,dataSet2);

        graphRef.addObserver(observer);
        assertEquals(0,graphRef.getEvaluationSets().size());

        graphRef.addEvalSet(dataSet1);
        assertEquals(1,graphRef.getEvaluationSets().size());
        assertEquals(dataSet1,graphRef.getEvaluationSets().get(0));

        graphRef.addEvalSet(dataSet2);
        assertEquals(2,graphRef.getEvaluationSets().size());
        assertEquals(dataSet1,graphRef.getEvaluationSets().get(0));
        assertEquals(dataSet2,graphRef.getEvaluationSets().get(1));

        graphRef.removeEvalSet(dataSet1);
        assertEquals(1,graphRef.getEvaluationSets().size());
        assertEquals(dataSet2,graphRef.getEvaluationSets().get(0));

        graphRef.addEvalSet(dataSet1);
        graphRef.removeEvalSet(dataSet2);
        assertEquals(1,graphRef.getEvaluationSets().size());
        assertEquals(dataSet1,graphRef.getEvaluationSets().get(0));

        graphRef.removeEvalSet(dataSet1);
        assertEquals(0,graphRef.getEvaluationSets().size());

        graphRef.removeEvalSet(dataSet1);

        graphRef.addObserver(observer);
        EasyMock.verify(observer,dataSet1,dataSet2);
    }

    @Test
    public void TestDataSetGroups(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        ClassifierDataSet dataSet1 = EasyMock.strictMock(ClassifierDataSet.class);
        ClassifierDataSet dataSet2 = EasyMock.strictMock(ClassifierDataSet.class);
        InputDataReferenceImpl graphRef = new InputDataReferenceImpl(null);

        observer.update(graphRef);
        EasyMock.expectLastCall().times(7);
        EasyMock.replay(observer,dataSet1,dataSet2);

        graphRef.addObserver(observer);
        assertEquals(0,graphRef.getDataSetGroups().size());

        graphRef.addDataSetGroup(dataSet1);
        assertEquals(1,graphRef.getDataSetGroups().size());
        assertEquals(dataSet1,graphRef.getDataSetGroups().get(0));

        graphRef.addDataSetGroup(dataSet2);
        assertEquals(2,graphRef.getDataSetGroups().size());
        assertEquals(dataSet1,graphRef.getDataSetGroups().get(0));
        assertEquals(dataSet2,graphRef.getDataSetGroups().get(1));

        graphRef.removeDataSetGroup(dataSet1);
        assertEquals(1,graphRef.getDataSetGroups().size());
        assertEquals(dataSet2,graphRef.getDataSetGroups().get(0));

        graphRef.addDataSetGroup(dataSet1);
        graphRef.removeDataSetGroup(dataSet2);
        assertEquals(1,graphRef.getDataSetGroups().size());
        assertEquals(dataSet1,graphRef.getDataSetGroups().get(0));

        graphRef.removeDataSetGroup(dataSet1);
        assertEquals(0,graphRef.getDataSetGroups().size());

        graphRef.removeDataSetGroup(dataSet1);

        graphRef.addObserver(observer);
        EasyMock.verify(observer,dataSet1,dataSet2);
    }

    @Test
    public void TestDataSetGraphMap(){
        IObserver observer = EasyMock.strictMock(IObserver.class);
        ClassifierDataSet classDataSet1 = EasyMock.strictMock(ClassifierDataSet.class);
        ClassifierDataSet classDataSet2 = EasyMock.strictMock(ClassifierDataSet.class);
        GraphableData graphDataSet1 = EasyMock.strictMock(GraphableData.class);
        GraphableData graphDataSet2 = EasyMock.strictMock(GraphableData.class);
        InputDataReferenceImpl graphRef = new InputDataReferenceImpl(null);

        observer.update(graphRef);
        EasyMock.expectLastCall().times(2);
        EasyMock.replay(observer,classDataSet1,classDataSet2,graphDataSet1,graphDataSet2);

        graphRef.addObserver(observer);
        graphRef.addToDataSetGraphMap(classDataSet1, graphDataSet1);
        graphRef.addToDataSetGraphMap(classDataSet2, graphDataSet2);
        assertEquals(graphDataSet1, graphRef.getGraphfromDataSet(classDataSet1));
        assertEquals(graphDataSet2, graphRef.getGraphfromDataSet(classDataSet2));

        EasyMock.verify(observer,classDataSet1,classDataSet2,graphDataSet1,graphDataSet2);
    }

    @Test
    public void TestTagHeaders(){
        ArrayList<TagHeader> tags = null;
        InputDataReferenceImpl graphRef = new InputDataReferenceImpl(tags);

        assertEquals(tags, graphRef.getTagHeaders());

        tags = new ArrayList<TagHeader>();
        graphRef = new InputDataReferenceImpl(tags);

        assertEquals(tags, graphRef.getTagHeaders());
    }
}
