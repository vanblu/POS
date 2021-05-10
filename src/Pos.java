import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Pos implements IPos {
    
    private List<Restaurant> restaurantSet = new ArrayList<>(); // load restaurant to this collection
    private HashMap<String, List<String>> stateAndCity = new HashMap<>();
    private QuadTree head; // head of the QuadTree storing restaurants 
    private Point userCoordinates;
    

    public HashMap<String, List<String>>  getStateAndCity(){
        
        return stateAndCity;
    }
    public int loadRestaurantInfo(String filepath) {

        JsonParser parse = new JsonParser();
        List<Restaurant> output = parse.read(filepath);
        restaurantSet = output; 
        stateAndCity = parse.readAllCity(filepath);
        return restaurantSet.size();
    }

    public QuadTree storeRestaurantsInTree(String city) {
        
        // create a quadTree centered at user's location

        head = new QuadTree(Coordinates.latLongToPoint(userCoordinates.getX(), userCoordinates.getY())); 

        for (IRestaurant r : restaurantSet) {
            if (r.getAddress().getCity().equals(city.toLowerCase())) {
                head.insert(r);
            }
        }
    
        return head;
    }

    public List<IRestaurant> searchForRestaurants(double maxDist, double lowRatng, double highRating,
            String category) {
        return head.rangeSearch(maxDist, lowRatng, highRating, category);
    }

    public List<IRestaurant> sortRestaurants(List<IRestaurant> restaurants, 
            String sortCriteria, boolean ascending) {
        Comparator<IRestaurant> comp = null;
        switch (sortCriteria) {
        case "star":
            comp = new starComparator();
            break;
        case "distance":
            comp = new distanceComparator(Coordinates.latLongToPoint(userCoordinates.getX(), userCoordinates.getY()));
            break;
        default:
            comp = new nameComparator();
        }
        if (!ascending) {
            comp = Collections.reverseOrder(comp);
        }
        Collections.sort(restaurants, comp);
        return restaurants;
    }

    public Collection<IRestaurant> getRestaurants() {
        Collection<IRestaurant> ret = new LinkedList<IRestaurant>();
        ret.addAll(this.restaurantSet);
        return ret;
    }

    
    public Point getUserCoordinates() {
        return this.userCoordinates;
    }

    public void setUserCoordinates(Point userCoordinates) {
        this.userCoordinates = userCoordinates;
    }



}
