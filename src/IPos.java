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
    public List<IRestaurant> searchForRestaruants(double maxDist,
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
    
    
    /* The lines below contain unused interface methods (leftovers from initial design) */
    
    /**
     * Initialize our system with a specific number of shopping carts 
     * 
     * @param numUsers number of users that will be using the system
     * @return number of users registered in the system 
     */
    //public int initializePos(int numUsers);
    
    
    /**
     * Given a collection of information (e.g. restaurants, menu items), print all sequentially
     * 
     * @param infoset the Collection containing the information to be printed 
     */
    //public void printInfo(Collection<?> infoset);

    
    
    /**
     * Obtain a list of all items in a menu
     * 
     * @param restaurant the target restaurant 
     * @return the Collection containing all items in a menu
     * 
     */
    //public Collection<IMenuItem> getMenu(IRestaurant restaurant);
    
    /**
     * Get a list of items in a menu in a given price range
     * 
     * @param restaurant the target restaurant 
     * @param low the lower bound of price (inclusive)
     * @param high the upper bound of price (inclusive)
     * @return the Collection containing all items in a menu within the price range
     * 
     */
    //public Collection<IMenuItem> getItemsInPriceRange(
      //      IRestaurant restaurant, double low, double high);
    
    /**
     * Add an item to a user's shopping cart
     * 
     * @param item the item to be added
     * @param cart the cart that should be updated
     */
    //public void addItemInCart(IMenuItem item, IShoppingCart cart);
    
    /**
     * Remove the last added item from a user's shopping cart
     * 
     * @param cart the cart that should be updated
     * @return the item deleted
     */
    //public IMenuItem removeLastItemInCart(IShoppingCart cart);
    
    /**
     * Calculate the total price of items in a shopping cart 
     * 
     * @param cart the target cart
     * @return the sum of prices of items in the cart
     */
    //public double calculateTotalCartPrice(IShoppingCart cart);
    
    /**
     * Made an order consisting of all items in a cart, 
     * the system will print out a list of items purchased and the total price
     * 
     * @param cart the target cart
     */
   // public void checkout(IShoppingCart cart);
    
    /**
     * This function is supposed to be called after all users checked out
     * Our imaginary restaurants will receive the aggregated orders, "cook" them, and 
     * deliver them to the users (delivery will be represented by messages) 
     * 
     */
    //public void cookAndDeliver();
}
