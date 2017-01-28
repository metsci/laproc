package com.metsci.laproc.data;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by malinocr on 1/21/2017.
 */
public class TagHeaderTest {
    @Test
    public void TestName(){
        String name = "Name";
        TagHeader header = new TagHeader(name);

        assertEquals(name, header.getName());
    }

    @Test
    public void TestTags(){
        String name = "Name";
        TagHeader header = new TagHeader(name);

        assertEquals(0, header.getTags().size());

        String tag = "tag";
        header.addTag(tag);
        assertEquals(1, header.getTags().size());
        assertTrue(header.getTags().contains(tag));

        String tag1 = "tag1";
        header.addTag(tag1);
        assertEquals(2, header.getTags().size());
        assertTrue(header.getTags().contains(tag));
        assertTrue(header.getTags().contains(tag1));
    }
}
