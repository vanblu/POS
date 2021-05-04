
// -------------------------------------------------------------------------
/**
 * A 2D Point class
 *
 * @author ericfouh, modified by Henry Hung
 */
public class Point {
    private double x;
    private double y;

    // ----------------------------------------------------------
    /**
     * Create a new Point object.
     * 
     * @param x
     * @param y
     */
    Point(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    // ----------------------------------------------------------
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    // ----------------------------------------------------------
    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    // ----------------------------------------------------------
    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    // ----------------------------------------------------------
    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point that = (Point) o;
        return this.x == that.getX() && this.y == that.getY();
    }
    
    // return distance between two points
    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow((this.getX() - other.getX()), 2.0) + 
                Math.pow((this.getY() - other.getY()), 2.0));
    }

}
