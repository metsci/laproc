package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphTest;
import com.metsci.laproc.plotting.GraphableData;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test DataSetTableModel
 * Created by malinocr on 1/19/2017.
 */
public class DataSetTableModelTest {
    DataSetTableModel model;

    @Before
    public void setUp(){
        this.model = new DataSetTableModel();
    }

    @Test
    public void testGetColumnCount(){
        assertEquals(2, model.getColumnCount());
    }

    @Test
    public void testGetColumnName(){
        assertEquals("Data", model.getColumnName(0));
        assertEquals("Display", model.getColumnName(1));
        try{
            model.getColumnName(2);
        } catch (IllegalArgumentException e){
            return;
        }
        fail();
    }

    @Test
    public void testAddRowTrue(){
        assertEquals(0, model.getRowCount());
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        String dataName = "Name";
        EasyMock.expect(data.getName()).andReturn(dataName);
        EasyMock.replay(data);
        boolean bool = true;
        model.addRow(data, bool);
        assertEquals(1, model.getRowCount());
        assertEquals(dataName, model.getValueAt(0,0));
        assertEquals(bool, model.getValueAt(0,1));
        assertEquals(data, model.getObjectAt(0));
        EasyMock.verify(data);
    }

    @Test
    public void testAddRowFalse(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        String dataName = "Name";
        EasyMock.replay(data);
        boolean bool = false;
        model.addRow(data, bool);
        assertEquals(bool, model.getValueAt(0,1));
        EasyMock.verify(data);
    }

    @Test
    public void testAddTwoRow(){
        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        String dataName1 = "Name1";
        GraphableData data2 = EasyMock.strictMock(GraphableData.class);
        String dataName2 = "Name2";
        EasyMock.expect(data1.getName()).andReturn(dataName1);
        EasyMock.expect(data2.getName()).andReturn(dataName2);
        EasyMock.replay(data1, data2);
        boolean bool1 = true;
        boolean bool2 = false;
        model.addRow(data1, bool1);
        model.addRow(data2, bool2);
        assertEquals(2, model.getRowCount());
        assertEquals(dataName1, model.getValueAt(0,0));
        assertEquals(bool1, model.getValueAt(0,1));
        assertEquals(data1, model.getObjectAt(0));
        assertEquals(dataName2, model.getValueAt(1,0));
        assertEquals(bool2, model.getValueAt(1,1));
        assertEquals(data2, model.getObjectAt(1));
        EasyMock.verify(data1, data2);
    }

    @Test
    public void testIsEditable(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.replay(data);
        boolean bool = false;
        model.addRow(data, bool);
        assertEquals(true, model.isCellEditable(0,0));
        assertEquals(true, model.isCellEditable(0,1));
        try{
            model.isCellEditable(1,0);
        } catch (IllegalArgumentException e){
            try{
                model.isCellEditable(0,2);
            } catch (IllegalArgumentException e1){
                EasyMock.verify(data);
                return;
            }
            fail("Failed for column exceeds column count");
        }
        fail("Failed for row exceeds row count");
    }

    @Test
    public void testGetValueAtEdgeCases(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.replay(data);
        boolean bool = false;
        model.addRow(data, bool);
        try{
            model.getValueAt(1,0);
        } catch (IllegalArgumentException e){
            try{
                model.getValueAt(0,2);
            } catch (IllegalArgumentException e1){
                EasyMock.verify(data);
                return;
            }
            fail("Failed for column exceeds column count");
        }
        fail("Failed for row exceeds row count");
    }

    @Test
    public void testGetObjectAtEdgeCase(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.replay(data);
        boolean bool = false;
        model.addRow(data, bool);
        try{
            model.getObjectAt(1);
        } catch (IllegalArgumentException e){
            return;
        }
        fail();
    }

    @Test
    public void testSetValueAt(){
        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        GraphableData data2 = EasyMock.strictMock(GraphableData.class);
        String dataName2 = "Name2";
        String dataName3 = "Name3";
        data1.setName(dataName3);
        EasyMock.expectLastCall().times(1);
        EasyMock.expect(data1.getName()).andReturn(dataName3);
        EasyMock.expect(data2.getName()).andReturn(dataName2);
        EasyMock.replay(data1, data2);

        boolean bool1 = true;
        boolean bool2 = false;
        model.addRow(data1, bool1);
        model.addRow(data2, bool2);
        model.setValueAt(dataName3, 0, 0);
        assertEquals(2, model.getRowCount());
        assertEquals(dataName3, model.getValueAt(0,0));
        assertEquals(bool1, model.getValueAt(0,1));
        assertEquals(data1, model.getObjectAt(0));
        assertEquals(dataName2, model.getValueAt(1,0));
        assertEquals(bool2, model.getValueAt(1,1));
        assertEquals(data2, model.getObjectAt(1));

        model.setValueAt(bool2, 0, 1);
        assertEquals(bool2, model.getValueAt(0,1));

        try{
            model.setValueAt(data1, 0, 0);
        } catch (IllegalArgumentException e){
            try{
                model.setValueAt(data1, 0, 1);
            } catch (IllegalArgumentException e1){
                try{
                    model.setValueAt(dataName3, 2, 0);
                } catch (IllegalArgumentException e2){
                    try{
                        model.setValueAt(bool2, 0, 2);
                    } catch (IllegalArgumentException e3){
                        EasyMock.verify(data1, data2);
                        return;
                    }
                    fail("Failed for column exceeds column count");
                }
                fail("Failed for row exceeds row count");
            }
            fail("Failed for setting second column to invalid value");
        }
        fail("Failed for setting first column to invalid value");
    }

    @Test
    public void testClear(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        EasyMock.replay(data);
        boolean bool = true;
        model.addRow(data,bool);
        model.addRow(data,bool);
        assertEquals(2, model.getRowCount());
        model.clear();
        assertEquals(0, model.getRowCount());
        EasyMock.verify(data);
    }

    @Test
    public void testGetRowOfObject(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        EasyMock.replay(data, data1);

        assertEquals(-1,model.getRowOfObject(data));
        boolean bool = true;
        model.addRow(data,bool);
        model.addRow(data1,bool);
        assertEquals(0,model.getRowOfObject(data));
        assertEquals(1,model.getRowOfObject(data1));
    }

}
