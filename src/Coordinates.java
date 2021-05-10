
public class Coordinates {
    
    final static double RADIUS = 6378137.0; // radius of the Earth (assume spherical)
    
    public static Point latLongToPoint(double latitude, double longitude) {
        double x = RADIUS * Math.toRadians(longitude);
        double y = RADIUS * Math.toRadians(latitude);
        return new Point(x, y);
    }
}
