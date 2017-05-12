package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.tools.EvaluationSetPanel;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Test FilterDataSetAction
 * Created by malinocr on 3/11/2017.
 */
public class FilterDataSetActionTest {
    @Test
    public void DoActionTestWithName(){
        InputDataReference inputRef = EasyMock.strictMock(InputDataReference.class);
        OutputDataReference outputRef = EasyMock.strictMock(OutputDataReference.class);
        GraphableFunction graphFunction = EasyMock.strictMock(GraphableFunction.class);
        EvaluationSetPanel evaluationSetPanel = EasyMock.strictMock(EvaluationSetPanel.class);
        ClassifierDataSet updateSet = EasyMock.strictMock(ClassifierDataSet.class);
        GraphableData oldGraph = EasyMock.strictMock(GraphableData.class);
        GraphableData output = EasyMock.strictMock(GraphableData.class);

        EasyMock.expect(evaluationSetPanel.getSelectedDataSet()).andReturn(updateSet);

        ArrayList<List<String>> tags = new ArrayList<List<String>>();
        EasyMock.expect(evaluationSetPanel.getSelectedTags()).andReturn(tags);

        ArrayList<ClassifierDataSet> evalSets = new ArrayList<ClassifierDataSet>();
        EasyMock.expect(inputRef.getEvaluationSets()).andReturn(evalSets);

        EasyMock.expect(inputRef.getGraphFromDataSet(updateSet)).andReturn(oldGraph);

        EasyMock.expect(graphFunction.compute(updateSet)).andReturn(output);

        String name = "Name";
        EasyMock.expect(updateSet.getName()).andReturn(name);

        output.setName(name);
        EasyMock.expectLastCall().times(1);

        outputRef.replaceDataOnGraph(oldGraph,output);
        EasyMock.expectLastCall().times(1);

        inputRef.replaceDataSetGraphMap(updateSet,output);
        EasyMock.expectLastCall().times(1);

        EasyMock.replay(inputRef, outputRef, graphFunction, evaluationSetPanel, updateSet, oldGraph, output);

        FilterDataSetAction action = new FilterDataSetAction(inputRef, outputRef, graphFunction);
        action.doAction(evaluationSetPanel);

        EasyMock.verify(inputRef, outputRef, graphFunction, evaluationSetPanel, updateSet, oldGraph, output);
    }
}
