package com.beacon.test1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.clearspring.analytics.stream.cardinality.RegisterSet;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testGetAndSet_withSmallBits() throws Exception {
        RegisterSet rs = new RegisterSet(6);
        rs.set(0, 11);
        System.out.println(rs.get(0));
        assertEquals(11, rs.get(0));
    }
}
