package com.metsci.laproc.action;

import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.datareference.InputDataReference;
import com.metsci.laproc.datareference.OutputDataReference;
import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.plotting.GraphableFunction;
import com.metsci.laproc.tools.EvaluationSetPanel;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests CreateNewDataSetAction
 * Created by malinocr on 3/9/2017.
 */
public class CreateNewDataSetActionTest {
    @Test
    public void DoActionTestWithName(){
        InputDataReference inputRef = EasyMock.strictMock(InputDataReference.class);
        OutputDataReference outputRef = EasyMock.strictMock(OutputDataReference.class);
        GraphableFunction graphFunction = EasyMock.strictMock(GraphableFunction.class);
        EvaluationSetPanel evaluationSetPanel = EasyMock.strictMock(EvaluationSetPanel.class);
        GraphableData graphableData = EasyMock.strictMock(GraphableData.class);

        String dataName = "Name";
        EasyMock.expect(evaluationSetPanel.getNameText()).andReturn(dataName);

        ArrayList<List<String>> tags = new ArrayList<List<String>>();
        EasyMock.expect(evaluationSetPanel.getSelectedTags()).andReturn(tags);

        ArrayList<ClassifierDataSet> evalSets = new ArrayList<ClassifierDataSet>();
        EasyMock.expect(inputRef.getEvaluationSets()).andReturn(evalSets);

        Capture<ClassifierDataSet> dataSetGroup1 = EasyMock.newCapture();
        EasyMock.expect(graphFunction.compute(
                    EasyMock.and(EasyMock.capture(dataSetGroup1),
                        EasyMock.isA(ClassifierDataSet.class))))
                .andReturn(graphableData);

        graphableData.setName(dataName);
        EasyMock.expectLastCall().times(1);

        outputRef.addGraphableData(graphableData);
        EasyMock.expectLastCall().times(1);

        Capture<ClassifierDataSet> dataSetGroup2 = EasyMock.newCapture();
        inputRef.addDataSetGroup(
                EasyMock.and(EasyMock.capture(dataSetGroup2),
                    EasyMock.isA(ClassifierDataSet.class)));
        EasyMock.expectLastCall().times(1);

        Capture<ClassifierDataSet> dataSetGroup3 = EasyMock.newCapture();
        Capture<GraphableData> graphableDataCapture = EasyMock.newCapture();
        inputRef.addToDataSetGraphMap(
                EasyMock.and(EasyMock.capture(dataSetGroup3),
                    EasyMock.isA(ClassifierDataSet.class)),
                EasyMock.and(EasyMock.capture(graphableDataCapture),
                        EasyMock.isA(GraphableData.class)));
        EasyMock.expectLastCall().times(1);

        Capture<ClassifierDataSet> dataSetGroup4 = EasyMock.newCapture();
        evaluationSetPanel.setSelectedDataSet(
                EasyMock.and(EasyMock.capture(dataSetGroup4),
                    EasyMock.isA(ClassifierDataSet.class)));
        EasyMock.expectLastCall().times(1);

        EasyMock.replay(inputRef, outputRef, graphFunction, evaluationSetPanel, graphableData);

        CreateNewDataSetAction action = new CreateNewDataSetAction(inputRef, outputRef, graphFunction);
        action.doAction(evaluationSetPanel);

        assertEquals(graphableDataCapture.getValue(),graphableData);
        assertEquals(dataSetGroup1.getValue(),dataSetGroup2.getValue());
        assertEquals(dataSetGroup1.getValue(),dataSetGroup3.getValue());
        assertEquals(dataSetGroup1.getValue(),dataSetGroup4.getValue());

        EasyMock.verify(inputRef, outputRef, graphFunction, evaluationSetPanel, graphableData);
    }

    @Test
    public void DoActionTestWithoutName(){
        InputDataReference inputRef = EasyMock.niceMock(InputDataReference.class);
        OutputDataReference outputRef = EasyMock.niceMock(OutputDataReference.class);
        GraphableFunction graphFunction = EasyMock.niceMock(GraphableFunction.class);
        EvaluationSetPanel evaluationSetPanel = EasyMock.niceMock(EvaluationSetPanel.class);
        GraphableData graphableData = EasyMock.niceMock(GraphableData.class);

        String dataName = "";
        EasyMock.expect(evaluationSetPanel.getNameText()).andReturn(dataName);

        ArrayList<List<String>> tags = new ArrayList<List<String>>();
        EasyMock.expect(evaluationSetPanel.getSelectedTags()).andReturn(tags);

        ArrayList<ClassifierDataSet> evalSets = new ArrayList<ClassifierDataSet>();
        EasyMock.expect(inputRef.getEvaluationSets()).andReturn(evalSets);

        EasyMock.expect(graphFunction.compute(EasyMock.anyObject())).andReturn(graphableData);

        graphableData.setName("New Data Set 1");
        EasyMock.expectLastCall().times(1);

        EasyMock.expect(evaluationSetPanel.getNameText()).andReturn(dataName);
        EasyMock.expect(evaluationSetPanel.getSelectedTags()).andReturn(tags);
        EasyMock.expect(inputRef.getEvaluationSets()).andReturn(evalSets);

        EasyMock.expect(graphFunction.compute(EasyMock.anyObject())).andReturn(graphableData);

        graphableData.setName("New Data Set 2");
        EasyMock.expectLastCall().times(1);

        EasyMock.replay(inputRef, outputRef, graphFunction, evaluationSetPanel, graphableData);

        CreateNewDataSetAction action = new CreateNewDataSetAction(inputRef, outputRef, graphFunction);
        action.doAction(evaluationSetPanel);
        action.doAction(evaluationSetPanel);

        EasyMock.verify(inputRef, outputRef, graphFunction, evaluationSetPanel, graphableData);
    }
}
