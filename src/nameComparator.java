import java.util.Comparator;

public class nameComparator implements Comparator<IRestaurant> {

    @Override
    public int compare(IRestaurant o1, IRestaurant o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
