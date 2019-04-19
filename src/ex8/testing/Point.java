package ex8.testing;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Point {
    private final int x;
    private final int y;

    public final static Comparator<Point> compareByXAndThenY =
        comparing(Point::getX).thenComparing(Point::getY);

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
        return points.stream()
                    .map(p -> new Point(p.getX() + x, p.getY()))
                    .collect(toList());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Point)) return false;

        Point p1 = (Point)obj;

        return this.getX() == p1.getX() && this.getY() == p1.getY();
    }
}
