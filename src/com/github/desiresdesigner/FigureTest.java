package com.github.desiresdesigner;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author desiresdesigner
 * @since 11/14/13
 */
public class FigureTest {
    private Figure figure;
    private int R;

    @Before
    public void before() {
        R = 100;
        figure = new Figure(R);
    }

    @Test
    public void testFirstQuadrantIn() {
        Mark m1 = new Mark(R/4, R/2);
        Mark m2 = new Mark(R/4, R/4);
        Mark m3 = new Mark(R/4, R/2 + R/4);
        assertTrue(figure.contains(m1));
        assertTrue(figure.contains(m2));
        assertTrue(figure.contains(m3));
    }

    @Test
    public void testSecondQuadrantIn() {
        Mark m1 = new Mark(-R/5, R/5);
        Mark m2 = new Mark(-R/4 + R/10, R/4  - R/10);
        Mark m3 = new Mark(-R/6, R/6);
        assertTrue(figure.contains(m1));
        assertTrue(figure.contains(m2));
        assertTrue(figure.contains(m3));
    }

    @Test
    public void testThirdQuadrantIn() {
        Mark m1 = new Mark(-R/5, -R/5);
        Mark m2 = new Mark(-R/4, -R/4);
        Mark m3 = new Mark(-R/2, -R/2);
        assertFalse(figure.contains(m1));
        assertFalse(figure.contains(m2));
        assertFalse(figure.contains(m3));
    }

    @Test
    public void testFourthQuadrantIn() {
        Mark m1 = new Mark(R/4, -R/4);
        Mark m2 = new Mark(R/4, -R/6);
        Mark m3 = new Mark(R/6, -R/4);
        assertTrue(figure.contains(m1));
        assertTrue(figure.contains(m2));
        assertTrue(figure.contains(m3));
    }

    @Test
    public void testBorder() {
        Mark m1 = new Mark(0, 0);
        Mark m2 = new Mark(0, -R/2);
        Mark m3 = new Mark(R/2, 0);
        Mark m4 = new Mark(R/2, R);
        Mark m5 = new Mark(0, R);
        Mark m6 = new Mark(0, R/2);
        Mark m7 = new Mark(-R/2, 0);
        assertFalse(figure.contains(m1));
        assertFalse(figure.contains(m2));
        assertFalse(figure.contains(m3));
        assertFalse(figure.contains(m4));
        assertFalse(figure.contains(m5));
        assertFalse(figure.contains(m6));
        assertFalse(figure.contains(m7));
    }

    @Test
    public void testAxisInFigure() {
        Mark m1 = new Mark(0, R/4);
        Mark m2 = new Mark(R/4, 0);
        assertTrue(figure.contains(m1));
        assertTrue(figure.contains(m2));
    }

    @Test
    public void testOutPoints() {
        Mark m1 = new Mark(R+1, R+1);
        Mark m2 = new Mark(-R/2, R/2);
        Mark m3 = new Mark(-R/10, -R/10);
        Mark m4 = new Mark(R/2, -R/2);
        assertFalse(figure.contains(m1));
        assertFalse(figure.contains(m2));
        assertFalse(figure.contains(m3));
        assertFalse(figure.contains(m4));
    }

    @Test(expected=Exception.class)
    public void testMainWithTextInput() {
        String[] args = {"lol"};
        MainForLab3.mainWithException(args);
    }

    @Test(expected=Exception.class)
    public void testMainWithNegativeNumbers() {
        String[] args = {"-7"};
        MainForLab3.mainWithException(args);
    }
}
