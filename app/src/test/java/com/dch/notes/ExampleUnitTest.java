package com.dch.notes;

import com.dch.notes.util.Calculator;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Calculator mCalculator = new Calculator();
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAdd() {
        assertEquals(4, mCalculator.add(2,2));
    }

    @Test
    public void testSub(){
        assertEquals(3,mCalculator.subtraction(101,98));
    }

    @Test
    public void testAdd2(){
        Calculator calculator = mock(Calculator.class);
        calculator.add(1,3);
        verify(calculator).add(1,3);
    }
}