import java.util.List;

public interface IQuadTree {
    
    
    public int depth();
    
    public boolean isLeaf();
    
    public void insert(IQuadTree head, IRestaurant rest);
    
    public List<IRestaurant> rangeSearch(double minDist, double maxDist,
            double lowRatng, double highRating, String cuisineType);
    
    
}
