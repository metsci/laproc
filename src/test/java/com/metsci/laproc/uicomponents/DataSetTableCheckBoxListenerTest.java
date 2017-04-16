package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphableData;
import com.metsci.laproc.utils.IAction;
import org.easymock.EasyMock;
import org.junit.Test;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Tests DataSetTableCheckBoxListener
 * Created by malinocr on 3/12/2017.
 */
public class DataSetTableCheckBoxListenerTest {
    @Test
    public void TestShow(){
        IAction<GraphableData> show = EasyMock.strictMock(IAction.class);
        IAction<GraphableData> hide = EasyMock.strictMock(IAction.class);
        TableModelEvent e = EasyMock.strictMock(TableModelEvent.class);
        DataSetTableModel model = EasyMock.strictMock(DataSetTableModel.class);
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.expect(e.getColumn()).andReturn(1);
        EasyMock.expect(e.getType()).andReturn(TableModelEvent.UPDATE);
        EasyMock.expect(e.getSource()).andReturn(model);
        EasyMock.expect(e.getSource()).andReturn(model);
        EasyMock.expect(e.getFirstRow()).andReturn(1);
        EasyMock.expect(e.getLastRow()).andReturn(1);
        EasyMock.expect(model.getValueAt(1,1)).andReturn(true);
        EasyMock.expect(model.getValueAt(1,1)).andReturn(true);
        EasyMock.expect(model.getObjectAt(1)).andReturn(data);
        show.doAction(data);
        EasyMock.expectLastCall().times(1);
        EasyMock.expect(e.getLastRow()).andReturn(1);
        EasyMock.replay(show,hide,e,model,data);
        DataSetTableCheckBoxListener listener = new DataSetTableCheckBoxListener(show,hide);
        listener.tableChanged(e);
        EasyMock.verify(show,hide,e,model,data);
    }

    @Test
    public void TestHide(){
        IAction<GraphableData> show = EasyMock.strictMock(IAction.class);
        IAction<GraphableData> hide = EasyMock.strictMock(IAction.class);
        TableModelEvent e = EasyMock.strictMock(TableModelEvent.class);
        DataSetTableModel model = EasyMock.strictMock(DataSetTableModel.class);
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.expect(e.getColumn()).andReturn(1);
        EasyMock.expect(e.getType()).andReturn(TableModelEvent.UPDATE);
        EasyMock.expect(e.getSource()).andReturn(model);
        EasyMock.expect(e.getSource()).andReturn(model);
        EasyMock.expect(e.getFirstRow()).andReturn(1);
        EasyMock.expect(e.getLastRow()).andReturn(1);
        EasyMock.expect(model.getValueAt(1,1)).andReturn(false);
        EasyMock.expect(model.getValueAt(1,1)).andReturn(false);
        EasyMock.expect(model.getObjectAt(1)).andReturn(data);
        hide.doAction(data);
        EasyMock.expectLastCall().times(1);
        EasyMock.expect(e.getLastRow()).andReturn(1);
        EasyMock.replay(show,hide,e,model,data);
        DataSetTableCheckBoxListener listener = new DataSetTableCheckBoxListener(show,hide);
        listener.tableChanged(e);
        EasyMock.verify(show,hide,e,model,data);
    }

    @Test
    public void TestNotFirstColumn(){
        IAction<GraphableData> show = EasyMock.strictMock(IAction.class);
        IAction<GraphableData> hide = EasyMock.strictMock(IAction.class);
        TableModelEvent e = EasyMock.strictMock(TableModelEvent.class);
        DataSetTableModel model = EasyMock.strictMock(DataSetTableModel.class);
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.expect(e.getColumn()).andReturn(0);
        EasyMock.replay(show,hide,e,model,data);
        DataSetTableCheckBoxListener listener = new DataSetTableCheckBoxListener(show,hide);
        listener.tableChanged(e);
        EasyMock.verify(show,hide,e,model,data);
    }

    @Test
    public void TestNotUpdate(){
        IAction<GraphableData> show = EasyMock.strictMock(IAction.class);
        IAction<GraphableData> hide = EasyMock.strictMock(IAction.class);
        TableModelEvent e = EasyMock.strictMock(TableModelEvent.class);
        DataSetTableModel model = EasyMock.strictMock(DataSetTableModel.class);
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.expect(e.getColumn()).andReturn(1);
        EasyMock.expect(e.getType()).andReturn(TableModelEvent.DELETE);
        EasyMock.replay(show,hide,e,model,data);
        DataSetTableCheckBoxListener listener = new DataSetTableCheckBoxListener(show,hide);
        listener.tableChanged(e);
        EasyMock.verify(show,hide,e,model,data);
    }

    @Test
    public void TestNotDataSetTableModel(){
        IAction<GraphableData> show = EasyMock.strictMock(IAction.class);
        IAction<GraphableData> hide = EasyMock.strictMock(IAction.class);
        TableModelEvent e = EasyMock.strictMock(TableModelEvent.class);
        DefaultTableModel model = EasyMock.strictMock(DefaultTableModel.class);
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.expect(e.getColumn()).andReturn(1);
        EasyMock.expect(e.getType()).andReturn(TableModelEvent.UPDATE);
        EasyMock.expect(e.getSource()).andReturn(model);
        EasyMock.replay(show,hide,e,model,data);
        DataSetTableCheckBoxListener listener = new DataSetTableCheckBoxListener(show,hide);
        listener.tableChanged(e);
        EasyMock.verify(show,hide,e,model,data);
    }
}
