import java.util.Collection;

/**
 * @author Henry Hung (henrythhung), Ashley Chang (vanblu), Jihu Kim
 */

public interface IPos {
    
    /**
     * Initialize our system with a specific number of shopping carts 
     * 
     * @param numUsers number of users that will be using the system
     * @return number of users registered in the system 
     */
    public int initializePos(int numUsers);
    
    
    /**
     * Load Restaurant Information from ________
     * 
     * @param filePath the path of the data
     * @return number of restaurants included 
     */
    public int loadRestaurantInfo(String filepath);
    
    /**
     * Given a collection of information (e.g. restaurants, menu items), print all sequentially
     * 
     * @param infoset the Collection containing the information to be printed 
     */
    public void printInfo(Collection<?> infoset);
    
    /**
     * Obtain a list of all restaurants stored in the system
     * 
     * @return the Collection containing all restaurants
     * 
     */
    public Collection<IRestaurant> getRestaurants();
    
    
    /**
     * Obtain a list of all items in a menu
     * 
     * @param restaurant the target restaurant 
     * @return the Collection containing all items in a menu
     * 
     */
    public Collection<IMenuItem> getMenu(IRestaurant restaurant);
    
    /**
     * Get a list of items in a menu in a given price range
     * 
     * @param restaurant the target restaurant 
     * @param low the lower bound of price (inclusive)
     * @param high the upper bound of price (inclusive)
     * @return the Collection containing all items in a menu within the price range
     * 
     */
    public Collection<IMenuItem> getItemsInPriceRange(
            IRestaurant restaurant, double low, double high);
    
    /**
     * Add an item to a user's shopping cart
     * 
     * @param item the item to be added
     * @param cart the cart that should be updated
     */
    public void addItemInCart(IMenuItem item, IShoppingCart cart);
    
    /**
     * Remove the last added item from a user's shopping cart
     * 
     * @param cart the cart that should be updated
     * @return the item deleted
     */
    public IMenuItem removeLastItemInCart(IShoppingCart cart);
    
    /**
     * Calculate the total price of items in a shopping cart 
     * 
     * @param cart the target cart
     * @return the sum of prices of items in the cart
     */
    public double calculateTotalCartPrice(IShoppingCart cart);
    
    /**
     * Made an order consisting of all items in a cart, 
     * the system will print out a list of items purchased and the total price
     * 
     * @param cart the target cart
     */
    public void checkout(IShoppingCart cart);
    
    /**
     * This function is supposed to be called after all users checked out
     * Our imaginary restaurants will receive the aggregated orders, "cook" them, and 
     * deliver them to the users (delivery will be represented by messages) 
     * 
     */
    public void cookAndDeliver();
}
