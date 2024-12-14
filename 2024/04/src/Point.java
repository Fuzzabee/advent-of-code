public class Point {
    private int x;
    private int y;
    private boolean visited;

    public Point(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }

        if (o instanceof Point) {
            Point oPoint = (Point) o;
            return this.x == oPoint.x && this.y == oPoint.y;
        }

        return false;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}