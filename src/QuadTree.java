import java.util.Collection;
import java.util.List;

public class QuadTree implements IQuadTree {

    private Point botRight;
    private Point topLeft; 
    private int depth;
    private QuadTree parent;
    private QuadTree ne;
    private QuadTree nw;
    private QuadTree se;
    private QuadTree sw;
    private Collection<IRestaurant> restaurants;
    
    public QuadTree(Point userCoordinates) {
        
    }
    
    private void split(QuadTree head) {
        return;
    }
    
    private QuadTree chooseLeaf(QuadTree head, Point p) {
        return null;
    }
    
    // insert a Restaurant in the quadtree based on its location info
    public void insert(IQuadTree head, IRestaurant rest) {
        
    }
    
    public List<IRestaurant> rangeSearch(double minDist, double maxDist,
            double lowRatng, double highRating, String cuisineType) {
        return null;
    }

    @Override
    public int depth() {
        return depth;
    }

    @Override
    public boolean isLeaf() {
        return (ne == null);
    }
    
    
    
}
