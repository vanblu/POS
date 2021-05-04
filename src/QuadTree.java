import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

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
    
    // only load restaurants that are at most 8km away from user
    final static double MAX_HALFLENGTH = 5656; 
    // if two restaurants are less than 500m away from each other, put them in the same block
    final static double MIN_HALFLENGTH = 353.5;
    // this gives us maxDepth = 4
    
    /**
     * initialize a QuadTree given a user's location 
     * 
     * @param userCoordinates Coordinates that indicate the user's location,
     *        will be the center point of the QuadTree 
     * 
     * @return a QuadTree centered at userCoordinates, with "radius" of the largest block being 8km
     */
    
    public QuadTree(Point userCoordinates) {
        this.botRight = new Point(userCoordinates.getX() + MAX_HALFLENGTH, 
                userCoordinates.getY() + MAX_HALFLENGTH);
        this.topLeft = new Point(userCoordinates.getX() - MAX_HALFLENGTH, 
                userCoordinates.getY() - MAX_HALFLENGTH);
        this.depth = 0; 
        this.restaurants = new TreeSet<IRestaurant>();

    }
    
    private void split(QuadTree head) {
        return;
    }
    
    private QuadTree chooseLeaf(QuadTree head, Point p) {
        return null;
    }
    
    private void addRestaurantToNode(QuadTree node, IRestaurant rest) {
        node.restaurants.add(rest);
        return;
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
    
    /************* Getters and Setters **********************/
    
    /**
     * @return top left point
     */
    public Point getTopLeft() {
        return this.topLeft;
    }
    
    // setter for topLeft
    public void setTopLeft(Point pt) {
        this.topLeft = pt;
    }

    /**
     * @return Bottom right point
     */
    public Point getBotRight() {
        return this.botRight;
    }

    // setter for botRight
    public void setBotRight(Point pt) {
        this.botRight = pt;
    }
    

    public QuadTree getNWTree() {
        return this.nw;
    }

    
    public QuadTree getNETree() {
        return this.ne;
    }


    public QuadTree getSWTree() {
        return this.sw;
    }

    public QuadTree getSETree() {
        return this.se;
    }
    
    public void setParent(QuadTree tree) {
        this.parent = tree;
    }
    
    public QuadTree getParent() {
        return this.parent;
    }
    
    public void setNETree(QuadTree tree) {
        this.ne = tree;
    }
    
    public void setNWTree(QuadTree tree) {
        this.nw = tree;
    }
    
    public void setSETree(QuadTree tree) {
        this.se = tree;
    }
    
    public void setSWTree(QuadTree tree) {
        this.sw = tree;
    }
    
    
    
}
