import java.util.Collection;
import java.util.List;


public class Pos implements IPos {
    
    List<Restaurant> restaurantSet; // load restaurant to this collection
    QuadTree head; // head of the QuadTree storing restaurants 
    

    @Override
    public int loadRestauranInfo(String filepath, String zip) {
        JsonParser parse = new JsonParser();
        List<Restaurant> output = parse.read(filepath, zip);
        restaurantSet = output; 
        return restaurantSet.size();
    }

    @Override
    public QuadTree storeRestaurantsInTree() {
        //TODO
        return null;
    }

    @Override
    public List<IRestaurant> searchForRestaruants(double minDist, double maxDist, double lowRatng, double highRating,
            String cuisineType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IRestaurant> sortRestaurants(List<IRestaurant> restaurants, int sortCriteria, boolean ascending) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<IRestaurant> getRestaurants() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int loadRestaurantInfo(String filepath, String zip) {
        // TODO Auto-generated method stub
        return 0;
    }

}
