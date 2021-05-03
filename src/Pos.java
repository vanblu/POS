import java.util.Collection;
import java.util.List;

public class Pos implements IPos {
    
    Collection<IRestaurant> restaurantSet; // load restaurant to this collection
    QuadTree head; // head of the QuadTree storing restaurants 
    

    @Override
    public int loadRestaurantInfo(String filepath, double latitude, double longitude) {
        // TODO Auto-generated method stub
        return 0;
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

}
