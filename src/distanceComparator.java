import java.util.Comparator;

public class distanceComparator implements Comparator<IRestaurant> {
    
    Point from;

    public distanceComparator(Point from) {
        this.from = from;
    }

    public int compare(IRestaurant o1, IRestaurant o2) {
        if (from.distanceTo(o1.getLocation()) - from.distanceTo(o2.getLocation()) > 0.01) {
            return 1;
        } else if (from.distanceTo(o1.getLocation()) - from.distanceTo(o2.getLocation()) < -0.01) {
            return -1;
        } else {
            return 0;
        }
    }

}
