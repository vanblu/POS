import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Pos implements IPos {
    
    List<Restaurant> restaurantSet; // load restaurant to this collection
    QuadTree head; // head of the QuadTree storing restaurants 
    Point userCoordinates;
    

    public int loadRestaurantInfo(String filepath) {
        restaurantSet =  new ArrayList<>();
        JsonParser parse = new JsonParser();
        List<Restaurant> output = parse.read(filepath);
        restaurantSet = output; 
        
        return restaurantSet.size();
    }

    public QuadTree storeRestaurantsInTree(String city) {
        
        // create a quadTree centered at user's location
        head = new QuadTree(userCoordinates); 
        for (IRestaurant r : restaurantSet) {
            
//            if (r.getAddress().getCity().equals(city.toLowerCase())) {
               
                head.insert(r);
//            }
        }
        return head;
    }

    public List<IRestaurant> searchForRestaruants(double maxDist, double lowRatng, double highRating,
            String cuisineType) {
        return head.rangeSearch(maxDist, lowRatng, highRating, cuisineType);
    }

    public List<IRestaurant> sortRestaurants(List<IRestaurant> restaurants, 
            String sortCriteria, boolean ascending) {
        Comparator<IRestaurant> comp = null;
        switch (sortCriteria) {
        case "star":
            comp = new starComparator();
            break;
        case "distance":
            comp = new distanceComparator(this.userCoordinates);
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
