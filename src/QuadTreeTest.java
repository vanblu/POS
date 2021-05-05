import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class QuadTreeTest {
    
    QuadTree q;
    IRestaurant rest;
    
    
    @Before
    public void init() {
        // Boulder public library 
        Point userCoord = Coordinates.latLongToPoint(40.01418233272989, -105.28175025156587);
        q = new QuadTree(userCoord);
        // Oskar Blues Taproom
        rest = new Restaurant();
        rest.setLatitude("40.01774979542888");
        rest.setLongtitude("-105.28321935607681");
        q.insert(rest);
    }
 
    @Test
    public void testInitialization() {
        Collection<IRestaurant> rests = q.getRestaurantsAtNode();
        assertEquals(1, rests.size());
        assertTrue(rests.contains(rest));
        assertEquals(0, q.depth());
        assertTrue(q.isLeaf());
    }
    
    @Test
    public void testInsertMoreThanFive() {
         
        IRestaurant rest1 = new Restaurant();
        rest1.setLatitude("40.017317315348315");
        rest1.setLongtitude("-105.282133521546");
        q.insert(rest1);
        
        IRestaurant rest2 = new Restaurant();
        rest2.setLatitude("40.017010019627726");
        rest2.setLongtitude("-105.28075144862171");
        q.insert(rest2);
        
        IRestaurant rest3 = new Restaurant();
        rest3.setLatitude("40.01796604621418");
        rest3.setLongtitude("-105.28078488586988");
        q.insert(rest3);
        
        IRestaurant rest4 = new Restaurant();
        rest4.setLatitude("40.016241774272196");
        rest4.setLongtitude("-105.2844295459202");
        q.insert(rest4);
        
        IRestaurant rest5 = new Restaurant();
        rest5.setLatitude("40.01803433331497");
        rest5.setLongtitude("-105.27833282100421");
        q.insert(rest5);
        
        Collection<IRestaurant> rests = q.getRestaurantsAtNode();
        assertEquals(5, rests.size());
        assertFalse(q.isLeaf());
    }

}
