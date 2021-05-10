import java.util.Collection;
import java.util.List;

/**
 * @author Henry Hung (henrythhung), Ashley Chang (vanblu), Jihu Kim
 */

public interface IPos {
    
    /**
     * Load Restaurant Information from Yelp dataset, including only those restaurants at a particular 
     * zipcode
     * 
     * @param filePath the path of the data
     * @p4aram zip the specific zipcode requested by the user 
     * @return number of restaurants loaded into the system 
     */
    public int loadRestaurantInfo(String filepath); 
    
    
    /**
     * Obtain a Collection of all restaurants stored in the system
     * @return the Collection containing all restaurants
     */
    public Collection<IRestaurant> getRestaurants();
    
    /**
     * Store restaurants from a particular city in a quadTree data structure 
     * 
     * @param city the user's city 
     * 
     * @return a QuadTree storing info of restaurant from the city 
     */
    public QuadTree storeRestaurantsInTree(String city);
    
    /**
     * Search for Restaurants using three criteria: distance from user, 
     * rating, and type of cuisine. 
     *  
     *  @param minDist minimum distance of the restaurant from the user 
     *  @param maxDist maximum distance of the restaurant from the user 
     *  @param lowRating lower bound of restaurant rating (min = 0)
     *  @param highRating upper bound of restaurant rating (max = 5)
     *  @param cuisineType type of cuisine that the restaurant serves
     *  
     *  @return a list containing all the restaurants fulfilling the criteria 
     */
    public List<IRestaurant> searchForRestaurants(double maxDist,
            double lowRatng, double highRating, String cuisineType); 
    
    /** Given a list of restaurants and a sorting criteria, sort the list of restaurants
     * 
     * @param restaurants list of restaurants to be sorted 
     * @param sortCriteria criteria used for sorting (rating, or distance, or name)
     * @param ascending the order of sorting
     */
    public List<IRestaurant> sortRestaurants(List<IRestaurant> restaurants, 
            String sortCriteria, boolean ascending);
    
    /**
     * @return current user's coordinates 
     */
    public Point getUserCoordinates();
    
    /**
     * Set current user's coordinates (for QuadTree)
     */
    public void setUserCoordinates(Point userCoordinates);
    
}
