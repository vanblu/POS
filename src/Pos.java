import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class Pos implements IPos {
    
    List<Restaurant> restaurantSet; // load restaurant to this collection
    QuadTree head; // head of the QuadTree storing restaurants 
    Point userCoordinates;
    

    public int loadRestaurantInfo(String filepath, String zip) {
        JsonParser parse = new JsonParser();
        List<Restaurant> output = parse.read(filepath, zip);
        restaurantSet = output; 
        return restaurantSet.size();
    }

    public QuadTree storeRestaurantsInTree() {
        // create a quadTree centered at user's location
        head = new QuadTree(userCoordinates); 
        for (IRestaurant r : restaurantSet) {
            head.insert(r);
        }
        return head;
    }

    public List<IRestaurant> searchForRestaruants(double maxDist, double lowRatng, double highRating,
            String cuisineType) {
        return head.rangeSearch(maxDist, lowRatng, highRating, cuisineType);
    }

    public List<IRestaurant> sortRestaurants(List<IRestaurant> restaurants, 
            int sortCriteria, boolean ascending) {
        // TODO Auto-generated method stub
        return null;
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
