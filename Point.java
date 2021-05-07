package Game;

public class Point {
    public int x = 0, y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public Point(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Point)) {
            return false;
        }

        Point pt = (Point) obj;
        return this.x == pt.x && this.y == pt.y;
    }
}
