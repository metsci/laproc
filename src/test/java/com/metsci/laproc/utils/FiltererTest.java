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
    List<List<String>> tagSet;
    List<ClassifierDataSet> evalSets;

    @Before
    public void setUp() {
        dataSet = new ClassifierDataSet(new ArrayList<List<String>>(), "Update");

        initializetagSet();

        evalSets = new ArrayList<ClassifierDataSet>();

        List<List<String>> tagHeaders1 = new ArrayList<List<String>>();
        List<String> tagSet1 = new ArrayList<String>();
        tagHeaders1.add(tagSet1);
        tagSet1.add("a1");
        tagSet1.add("b1");
        ClassifierDataSet evalSet1 =  new ClassifierDataSet(tagHeaders1, "EvalSet1");
        evalSet1.add(new DataPointImpl(false, 1, 1));
        evalSets.add(evalSet1);

        List<List<String>> tagHeaders2 = new ArrayList<List<String>>();
        ArrayList<String> tagSet2 =  new ArrayList<String>();
        tagHeaders2.add(tagSet2);
        tagSet2.add("a2");
        tagSet2.add("b1");
        ClassifierDataSet evalSet2 =  new ClassifierDataSet(tagHeaders2, "EvalSet2");
        evalSet2.add(new DataPointImpl(false, 2, 1));
        evalSets.add(evalSet2);

        List<List<String>> tagHeaders3 = new ArrayList<List<String>>();
        ArrayList<String> tagSet3 =  new ArrayList<String>();
        tagHeaders3.add(tagSet3);
        tagSet3.add("a1");
        tagSet3.add("b2");
        ClassifierDataSet evalSet3 =  new ClassifierDataSet(tagHeaders3, "EvalSet3");
        evalSet3.add(new DataPointImpl(false, 1, 2));
        evalSets.add(evalSet3);

        List<List<String>> tagHeaders4 = new ArrayList<List<String>>();
        ArrayList<String> tagSet4 =  new ArrayList<String>();
        tagHeaders4.add(tagSet4);
        tagSet4.add("a2");
        tagSet4.add("b2");
        ClassifierDataSet evalSet4 =  new ClassifierDataSet(tagHeaders4, "EvalSet4");
        evalSet4.add(new DataPointImpl(false, 2, 2));
        evalSets.add(evalSet4);

        List<List<String>> tagHeaders5 = new ArrayList<List<String>>();
        ArrayList<String> tagSet5 =  new ArrayList<String>();
        tagHeaders5.add(tagSet5);
        tagSet5.add("a3");
        tagSet5.add("b1");
        ClassifierDataSet evalSet5 =  new ClassifierDataSet(tagHeaders5, "EvalSet5");
        evalSet5.add(new DataPointImpl(false, 3, 1));
        evalSets.add(evalSet5);
    }

    @Test
    public void testDefaulttagSet(){
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
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
        this.tagSet.get(0).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a3 )", setOperations);
    }

    @Test
    public void testUnionSingleTag(){
        this.tagSet.get(0).add("a1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 )", setOperations);
    }

    @Test
    public void testUnionMultipletagSet(){
        this.tagSet.get(0).add("a1");
        this.tagSet.get(0).add("a2");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
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
        this.tagSet.get(0).add("a1");
        this.tagSet.get(1).add("b1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 ) /\\ ( b1 )", setOperations);
    }

    @Test
    public void testIntersectAndUnionMultipleBefore(){
        this.tagSet.get(0).add("a1");
        this.tagSet.get(0).add("a2");
        this.tagSet.get(1).add("b2");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 V a2 ) /\\ ( b2 )", setOperations);
    }

    @Test
    public void testIntersectAndUnionMultipleAfter(){
        this.tagSet.get(0).add("a1");
        this.tagSet.get(1).add("b1");
        this.tagSet.get(1).add("b2");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 ) /\\ ( b1 V b2 )", setOperations);
    }

    @Test
    public void testFilerTwiceSingleOperation(){
        this.tagSet.get(0).add("a1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 )", setOperations);

        this.initializetagSet();

        this.tagSet.get(1).add("b1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 4 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a1 ) ] V [ ( b1 ) ]", setOperations);
    }

    @Test
    public void testFilerTwiceDoubleOperationIntersectBefore(){
        this.tagSet.get(0).add("a1");
        this.tagSet.get(1).add("b1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 ) /\\ ( b1 )", setOperations);

        this.initializetagSet();

        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a1 ) /\\ ( b1 ) ] V [ ( a3 ) ]", setOperations);
    }

    @Test
    public void testFilerTwiceDoubleOperationIntersectAfter(){
        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a3 )", setOperations);

        initializetagSet();

        this.tagSet.get(0).add("a1");
        this.tagSet.get(1).add("b1");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 2 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a3 ) ] V [ ( a1 ) /\\ ( b1 ) ]", setOperations);

        this.initializetagSet();
    }

    @Test
    public void testFilerTwiceDoubleOperationUnionBefore(){
        this.tagSet.get(0).add("a1");
        this.tagSet.get(0).add("a2");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 4 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a1 V a2 )", setOperations);

        this.initializetagSet();

        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a1 V a2 ) ] V [ ( a3 ) ]", setOperations);
    }

    @Test
    public void testFilerTwiceDoubleOperationUnionAfter(){
        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a3 )", setOperations);

        this.initializetagSet();

        this.tagSet.get(0).add("a1");
        this.tagSet.get(0).add("a2");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a3 ) ] V [ ( a1 V a2 ) ]", setOperations);

    }

    @Test
    public void testFilerTwiceAllBefore(){
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( all )", setOperations);

        this.initializetagSet();

        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( all ) ] V [ ( a3 ) ]", setOperations);
    }

    @Test
    public void testFilerTwiceAllAfter(){
        this.tagSet.get(1).add("a3");
        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        Set<DataPoint> points = this.dataSet.getAllPoints();

        assertEquals( 1 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(4)));

        String setOperations = this.dataSet.getSetOperations();
        assertEquals("( a3 )", setOperations);

        this.initializetagSet();

        Filterer.filterAndUnion(this.dataSet, this.tagSet, this.evalSets);
        points = this.dataSet.getAllPoints();

        assertEquals( 5 , points.size());

        assertTrue(points.contains(this.getEvalSetPoint(0)));
        assertTrue(points.contains(this.getEvalSetPoint(1)));
        assertTrue(points.contains(this.getEvalSetPoint(2)));
        assertTrue(points.contains(this.getEvalSetPoint(3)));
        assertTrue(points.contains(this.getEvalSetPoint(4)));

        setOperations = this.dataSet.getSetOperations();
        assertEquals("[ ( a3 ) ] V [ ( all ) ]", setOperations);
    }

    private DataPoint getEvalSetPoint(int i){
        return evalSets.get(i).getAllPoints().iterator().next();
    }

    private void initializetagSet(){
        tagSet = new ArrayList<List<String>>();

        tagSet.add(new ArrayList<String>());
        tagSet.add(new ArrayList<String>());
    }
}
