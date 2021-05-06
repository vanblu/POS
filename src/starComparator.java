import java.util.Comparator;

public class starComparator implements Comparator<IRestaurant> {

    @Override
    public int compare(IRestaurant o1, IRestaurant o2) {
        if (o1.getStars() == o2.getStars()) {
            return o1.getName().compareTo(o2.getName());
        } else {
            return (int) ((o1.getStars() - o2.getStars()) * 100);
        }
    }

}
