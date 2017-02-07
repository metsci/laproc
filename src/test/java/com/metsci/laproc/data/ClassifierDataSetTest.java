package com.metsci.laproc.data;

import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests ClassifierDataSet
 * Created by malinocr on 1/21/2017.
 */
public class ClassifierDataSetTest {
    @Test
    public void TestData(){
        List<List<String>> tagHeaders = new ArrayList<List<String>>();
        String name = "Name";
        ClassifierDataSet dataSet = new ClassifierDataSet(tagHeaders, name);
        DataPoint dataPoint = EasyMock.strictMock(DataPoint.class);
        DataPoint dataPoint1 = EasyMock.strictMock(DataPoint.class);

        assertEquals(0, dataSet.getAllPoints().size());

        dataSet.add(dataPoint);
        assertEquals(1, dataSet.getAllPoints().size());
        assertTrue(dataSet.getAllPoints().contains(dataPoint));

        dataSet.add(dataPoint1);
        assertEquals(2, dataSet.getAllPoints().size());
        assertTrue(dataSet.getAllPoints().contains(dataPoint));
        assertTrue(dataSet.getAllPoints().contains(dataPoint1));

        Iterator<DataPoint> itr = dataSet.iterator();
        DataPoint next = itr.next();
        if(next.equals(dataPoint)){
            assertTrue(itr.next().equals(dataPoint1));
        } else if(next.equals(dataPoint1)){
            assertTrue(itr.next().equals(dataPoint));
        } else {
            fail();
        }
    }

    @Test
    public void TestName(){
        List<List<String>> tagHeaders = new ArrayList<List<String>>();
        String name = "Name";
        ClassifierDataSet dataSet = new ClassifierDataSet(tagHeaders, name);
        assertEquals(name, dataSet.getName());
        assertEquals(name, dataSet.toString());

        String newName = "NewName";
        dataSet.setName(newName);
        assertEquals(newName, dataSet.getName());
        assertEquals(newName, dataSet.toString());
    }

    @Test
    public void TestTags(){
        List<List<String>> tagHeaders = new ArrayList<List<String>>();
        List<String> tagSet = new ArrayList<String>();
        String tag1 = "tag1";
        tagSet.add(tag1);
        String tag2 = "tag2";
        tagSet.add(tag2);
        String name = "Name";
        tagHeaders.add(tagSet);
        ClassifierDataSet dataSet = new ClassifierDataSet(tagHeaders, name);
        assertEquals(1, dataSet.getTags().size());
        assertTrue(dataSet.getTags().contains(tagSet));
        assertEquals(2, dataSet.getTags().get(0).size());
        assertTrue(dataSet.getTags().get(0).contains(tag1));
        assertTrue(dataSet.getTags().get(0).contains(tag2));
    }

    @Test
    public void TestSetOperations(){
        List<List<String>> tagHeaders = new ArrayList<List<String>>();
        String name = "Name";
        ClassifierDataSet dataSet = new ClassifierDataSet(tagHeaders, name);
        assertEquals("", dataSet.getSetOperations());

        String newOperations = "( all )";
        dataSet.setSetOperation(newOperations);
        assertEquals(newOperations, dataSet.getSetOperations());
    }
}
