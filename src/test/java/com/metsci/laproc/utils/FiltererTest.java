package com.metsci.laproc.utils;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.DataPointImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * A test class for Filterer
 * Created by malinocr on 1/16/2017.
 */
public class FiltererTest {
    ClassifierDataSet dataSet;
    List<List<String>> tags;
    List<ClassifierDataSet> evalSets;

    @Before
    public void setUp() {
        dataSet = new ClassifierDataSet(new ArrayList<String>(), "Update");
        tags = new ArrayList<List<String>>();

        tags.add(new ArrayList<String>());
        tags.add(new ArrayList<String>());

        evalSets = new ArrayList<ClassifierDataSet>();

        ArrayList<String> tags1 =  new ArrayList<String>();
        tags1.add("a1");
        tags1.add("b1");
        ClassifierDataSet evalSet1 =  new ClassifierDataSet(tags1, "EvalSet1");
        evalSet1.add(new DataPointImpl(false, 1, 1));
        evalSets.add(evalSet1);

        ArrayList<String> tags2 =  new ArrayList<String>();
        tags2.add("a2");
        tags2.add("b1");
        ClassifierDataSet evalSet2 =  new ClassifierDataSet(tags2, "EvalSet2");
        evalSet2.add(new DataPointImpl(false, 2, 1));
        evalSets.add(evalSet2);

        ArrayList<String> tags3 =  new ArrayList<String>();
        tags3.add("a1");
        tags3.add("b2");
        ClassifierDataSet evalSet3 =  new ClassifierDataSet(tags3, "EvalSet3");
        evalSet3.add(new DataPointImpl(false, 1, 2));
        evalSets.add(evalSet3);

        ArrayList<String> tags4 =  new ArrayList<String>();
        tags4.add("a2");
        tags4.add("b2");
        ClassifierDataSet evalSet4 =  new ClassifierDataSet(tags4, "EvalSet4");
        evalSet4.add(new DataPointImpl(false, 2, 2));
        evalSets.add(evalSet4);

        ArrayList<String> tags5 =  new ArrayList<String>();
        tags5.add("a3");
        tags5.add("b1");
        ClassifierDataSet evalSet5 =  new ClassifierDataSet(tags5, "EvalSet5");
        evalSet5.add(new DataPointImpl(false, 3, 1));
        evalSets.add(evalSet5);
    }

    @Test
    public void testDefaultTags(){
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( all )", setOperations);
    }

    @Test
    public void testSingleSet(){
        this.tags.get(0).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a3 )", setOperations);
    }

    @Test
    public void testUnionSingleTag(){
        this.tags.get(0).add("a1");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 )", setOperations);
    }

    @Test
    public void testUnionMultipleTags(){
        this.tags.get(0).add("a1");
        this.tags.get(0).add("a2");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 4 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 V a2 )", setOperations);
    }

    @Test
    public void testIntersect(){
        this.tags.get(0).add("a1");
        this.tags.get(1).add("b1");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 ) /\\ ( b1 )", setOperations);
    }

    @Test
    public void testIntersectAndUnionMultipleBefore(){
        this.tags.get(0).add("a1");
        this.tags.get(0).add("a2");
        this.tags.get(1).add("b2");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 V a2 ) /\\ ( b2 )", setOperations);
    }

    @Test
    public void testIntersectAndUnionMultipleAfter(){
        this.tags.get(0).add("a1");
        this.tags.get(1).add("b1");
        this.tags.get(1).add("b2");
        Filterer.filterAndUnion(this.dataSet, this.tags, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 ) /\\ ( b1 V b2 )", setOperations);
    }

    private DataPoint getEvalSetPoint(int i){
        return evalSets.get(i).getAllPoints().iterator().next();
    }
}
