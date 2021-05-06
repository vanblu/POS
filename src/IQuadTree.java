import java.util.List;

public interface IQuadTree {
    
    /**
     * @return depth of the QuadTree 
     */
    public int depth();
    
    /**
     * @return a boolean indicating whether the node is a leaf
     */
    public boolean isLeaf();
    
    /**
     * @return all four children of the QuadTree, or empty list if none 
     */
    public List<IQuadTree> children();
    
    /** 
     * insert an IRestaurant object in a QuadTree
     * 
     * @param head the QuadTree to insert into 
     * @param rest the IRestaurant object to be stored in QuadTree 
     */
    public void insert(IRestaurant rest);
    
    public List<IRestaurant> rangeSearch(double minDist, double maxDist,
            double lowRatng, double highRating, String cuisineType);
    
    
}
