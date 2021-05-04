import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class QuadTree implements IQuadTree {

    
    // maximum depth of quadtree 
    final static int MAX_DEPTH = 16;
    // Half length of the biggest QuadTree block - 8km
    final static double MAX_HALFLENGTH = 8000; 
    // Half length of the smallest QuadTree block - 500m
    final static double MIN_HALFLENGTH = MAX_HALFLENGTH / MAX_DEPTH;
    // maximum number of restaurants stored in a node, before we go to a deeper one 
    final static int MAX_REST_IN_NODE = 5; 
    
    private Point botRight;
    private Point topLeft; 
    private int depth;
    private QuadTree parent;
    private QuadTree ne;
    private QuadTree nw;
    private QuadTree se;
    private QuadTree sw;
    private Collection<IRestaurant> restaurants;

        
    /**
     * Constructor - initialize a QuadTree given a user's location 
     * 
     * @param userCoordinates Coordinates that indicate the user's location,
     *        will be the center point of the QuadTree 
     * 
     */
    public QuadTree(Point userCoordinates) {
        this.botRight = new Point(userCoordinates.getX() + MAX_HALFLENGTH, 
                userCoordinates.getY() + MAX_HALFLENGTH);
        this.topLeft = new Point(userCoordinates.getX() - MAX_HALFLENGTH, 
                userCoordinates.getY() - MAX_HALFLENGTH);
        this.depth = 0; 
        this.restaurants = new TreeSet<IRestaurant>();
    }
    
    /**
     * Constructor - initialize a child quadTree given topLeft and botRight coordinates, 
     * and parent quadTree
     * 
     * @param topLeft topLeft Point of the quadTree
     * @param botRight botRight Point of the quadTree
     * @param parent parent Tree 
     * 
     */
    public QuadTree(Point topLeft, Point botRight, QuadTree parent) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        this.parent = parent;
        this.depth = parent.depth() + 1;
        if (parent != null) {
            if (parent.getTopLeft().equals(topLeft)) {
                parent.setNWTree(this);
            } else if (parent.getBotRight().equals(botRight)) {
                parent.setSETree(this);
            } else if (parent.getBotRight().getX() == botRight.getX()) {
                parent.setNETree(this);
            } else if (parent.getTopLeft().getX() == topLeft.getX()) {
                parent.setSWTree(this);
            }
        }
        this.restaurants = new TreeSet<IRestaurant>();
    }
    
    // helper method, split a block into four 
    private void split() {
        double midX = (this.getTopLeft().getX() + this.getBotRight().getX()) / 2.0;
        double midY = (this.getTopLeft().getY() + this.getBotRight().getY()) / 2.0;

        new QuadTree(this.getTopLeft(), new Point(midX, midY), this);
        new QuadTree(new Point(midX, this.getTopLeft().getY()), 
                new Point(this.getBotRight().getX(), midY), this);
        new QuadTree(new Point(this.getTopLeft().getX(), midY), 
                new Point(midX, this.getBotRight().getY()), this);
        new QuadTree(new Point(midX, midY), this.getBotRight(), this);
        
        return;
    }
    
    // helper method, return the sub-QuadTree that the point belongs to 
    private QuadTree chooseLeaf(Point p) {
        double midX = (this.getTopLeft().getX() + this.getBotRight().getX()) / 2.0;
        double midY = (this.getTopLeft().getY() + this.getBotRight().getY()) / 2.0;
        
        if (p.getX() < midX && p.getY() < midY) {
            return this.getSWTree();
        } else if (p.getX() < midX) {
            return this.getNWTree();
        } else if (p.getY() < midY) {
            return this.getSETree();
        } else {
            return this.getNETree();
        }
    }
    
    // insert a Restaurant in the QuadTree based on its location info
    public void insert(IQuadTree head, IRestaurant rest) {
        double latitude = Double.parseDouble(rest.getLatitude());
        double longitude = Double.parseDouble(rest.getLongitude());
        
        Point restLocation = Coordinates.latLongToPoint(latitude, longitude);

        QuadTree headNode = (QuadTree) head;
        
        // if Restaurant location is outside of the quadTree, just return
        if (headNode.getBotRight().getY() < restLocation.getY() || 
                headNode.getBotRight().getX() < restLocation.getX() || 
                headNode.getTopLeft().getY() > restLocation.getY() || 
                headNode.getTopLeft().getX() > restLocation.getX()) {
            return; 
        }
        
        // if Block is already smallest, just add it 
        if (headNode.depth() == MAX_DEPTH) { 
            headNode.restaurants.add(rest);
        // if max number of restaurants has already been added to Node
        } else if (headNode.restaurants.size() == MAX_REST_IN_NODE) {
            if (headNode.isLeaf()) {
                headNode.split();
            }
            QuadTree nextNode = headNode.chooseLeaf(restLocation);
            insert(nextNode, rest);
        // if max number of restaurants have not been added yet 
        } else {
            headNode.restaurants.add(rest);
        }
        
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
        return this.children().size() == 0;
    }
    
    
    public List<IQuadTree> children() {
        List<IQuadTree> ret = new LinkedList<IQuadTree>();
        if (this.getNWTree() != null) {
            ret.add(this.getNWTree());
        }
        if (this.getNETree() != null) {
            ret.add(this.getNETree());
        }
        if (this.getSETree() != null) {
            ret.add(this.getSETree());
        }
        if (this.getSWTree() != null) {
            ret.add(this.getSWTree());
        }
        return ret;
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
