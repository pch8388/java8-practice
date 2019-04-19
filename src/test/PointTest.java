package test;

import ex8.testing.Point;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PointTest {

    @Test
    public void testMoveRightBy() {
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);

        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }
}
