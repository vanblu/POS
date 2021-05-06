
public class Coordinates {
    
    final static double RADIUS = 6378137.0; // radius of the Earth (assume spherical)
    
    public static Point latLongToPoint(double latitude, double longitude) {
        double x = RADIUS * Math.toRadians(longitude);
        double y = RADIUS * Math.toRadians(latitude);
        return new Point(x, y);
    }
    
    public static void main(String[] args) {
        
        System.out.println(latLongToPoint(40.017675845638266, -105.28327300072598));
        System.out.println(latLongToPoint(40.0157246662949, -105.277114431823));
        
        System.out.println(latLongToPoint(40.017675845638266, -105.28327300072598).
                distanceTo(latLongToPoint(40.0157246662949, -105.277114431823)));
       
    }
}
