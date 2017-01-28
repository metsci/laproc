package com.metsci.laproc.uicomponents;

import com.metsci.laproc.plotting.GraphableData;
import it.unimi.dsi.fastutil.objects.ObjectBigArrayBigList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by malinocr on 1/20/2017.
 */
public class DataSetTableTest {
    DataSetTable table;

    @Before
    public void setUp(){
        this.table = new DataSetTable();
    }

    @Test
    public void TestGetColumnClass(){
        assertEquals(String.class, this.table.getColumnClass(0));
        assertEquals(Boolean.class, this.table.getColumnClass(1));
        try{
            this.table.getColumnClass(2);
        } catch (IllegalArgumentException e){
            return;
        }
        fail();
    }

    @Test
    public void TestAddRow(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        String dataName = "Name";
        boolean bool = true;

        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        String dataName1 = "Name1";
        boolean bool1 = false;

        EasyMock.expect(data.getName()).andReturn(dataName);
        EasyMock.expect(data.getName()).andReturn(dataName);
        EasyMock.expect(data1.getName()).andReturn(dataName1);
        EasyMock.replay(data, data1);

        assertEquals(0, this.table.getRowCount());
        this.table.addDataSet(data,bool);
        assertEquals(1, this.table.getRowCount());
        assertEquals(dataName,this.table.getValueAt(0,0));
        assertEquals(bool,this.table.getValueAt(0,1));

        this.table.addDataSet(data1,bool1);
        assertEquals(2, this.table.getRowCount());
        assertEquals(dataName,this.table.getValueAt(0,0));
        assertEquals(bool,this.table.getValueAt(0,1));
        assertEquals(dataName1,this.table.getValueAt(1,0));
        assertEquals(bool1,this.table.getValueAt(1,1));

        EasyMock.verify(data, data1);
    }

    @Test
    public void TestClear(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        boolean bool = true;

        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        boolean bool1 = false;

        EasyMock.replay(data, data1);

        assertEquals(0, this.table.getRowCount());
        this.table.addDataSet(data,bool);
        this.table.addDataSet(data1,bool1);
        assertEquals(2, this.table.getRowCount());
        this.table.clear();
        assertEquals(0, this.table.getRowCount());
        EasyMock.verify(data, data1);
    }

    @Test
    public void TestSetSelectedValues(){
        GraphableData data = EasyMock.strictMock(GraphableData.class);
        boolean bool = true;

        GraphableData data1 = EasyMock.strictMock(GraphableData.class);
        boolean bool1 = false;

        EasyMock.replay(data, data1);

        this.table.addDataSet(data,bool);
        this.table.addDataSet(data1,bool1);
        assertEquals(0,(int)this.table.getSelectedValues().size64());
        ObjectOpenHashBigSet<GraphableData> dataSet = new ObjectOpenHashBigSet<GraphableData>();
        dataSet.add(data);
        this.table.setSelectedValues(dataSet);
        assertEquals(1,(int)this.table.getSelectedValues().size64());
        assertTrue(this.table.getSelectedValues().contains(data));
        EasyMock.verify(data, data1);
    }
}
