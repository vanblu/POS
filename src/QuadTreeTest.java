import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

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
        List<IQuadTree> children = q.children();
        assertEquals(4, children.size());
        
        
        // fifth point is at the NE of origin
        QuadTree neTree = (QuadTree) children.get(1);
        assertFalse(neTree.getRestaurantsAtNode().isEmpty());
        assertTrue(neTree.getRestaurantsAtNode().contains(rest5));
    }
    
    @Test
    public void insertOutOfRange() {
        assertEquals(1, q.getRestaurantsAtNode().size());
        IRestaurant restOutOfRange = new Restaurant();
        restOutOfRange.setLatitude("10");
        restOutOfRange.setLongtitude("10");
        q.insert(restOutOfRange);
        assertEquals(1, q.getRestaurantsAtNode().size());
    }
    
    @Test
    public void testRangeSearchInitial() {
        rest.setName("Oskar Blues");
        rest.setStars(3.0);
        rest.setCusineType("bar");
        List<IRestaurant> searchResult = q.rangeSearch(1000, 2, 4, "bar");
        assertTrue(searchResult.contains(rest));
    }
    
    @Test
    public void testRangeSearch() {
        // Boulder Dushanbe Teahouse
        IRestaurant rest1 = new Restaurant();
        rest1.setLatitude("40.01567942652367");
        rest1.setLongtitude("-105.27733382047006");
        rest1.setStars(3.0);
        rest1.setCusineType("restaurant");
        q.insert(rest1);
        
        // Avanti Food & Beverage 
        IRestaurant rest2 = new Restaurant();
        rest2.setLatitude("40.01876318787325");
        rest2.setLongtitude("-105.2770302292496");
        rest2.setCusineType("restaurant");
        rest2.setStars(5.0);
        q.insert(rest2);
        
        // village coffee shop: >1km away 
        IRestaurant rest3 = new Restaurant();
        rest3.setLatitude("40.015692144987135");
        rest3.setLongtitude("-105.26344802242745");
        rest3.setCusineType("restaurant");
        rest3.setStars(3.5);
        q.insert(rest3);
        
        // Rincon Argentino: >1km away 
        IRestaurant rest4 = new Restaurant();
        rest4.setLatitude("40.01505702182746");
        rest4.setLongtitude("-105.26274632294049");
        rest4.setCusineType("restaurant");
        rest4.setStars(2.0);
        q.insert(rest4);
        
        // sherpa 
        IRestaurant rest5 = new Restaurant();
        rest5.setLatitude("40.01634955889032");
        rest5.setLongtitude("-105.28441868849664");
        rest5.setCusineType("restaurant");
        rest5.setStars(3.5);
        q.insert(rest5);
        
        List<IRestaurant> searchResult = q.rangeSearch(1000, 2.0, 4.0, "restaurant");
        assertTrue(searchResult.contains(rest1));
        assertTrue(searchResult.contains(rest5));
    }
    
    @Test
    public void testInsert100Businesses() {
        Double latitude = 40.01567942652367;
        Double longitude = -105.27733382047006;
        
        // insert 100 similar restaurants at almost the same location 
        for (int i = 0; i < 100; i++) {
            IRestaurant rest1 = new Restaurant();
            rest1.setLatitude("" + latitude);
            rest1.setLongtitude("" + longitude);
            latitude += 0.0000000001;
            longitude += 0.000000001;
            rest1.setStars(3.0);
            rest1.setCusineType("restaurant");
            q.insert(rest1);
        }
        
        List<IRestaurant> searchResult = q.rangeSearch(1000, 2.0, 4.0, "restaurant");
        assertEquals(100, searchResult.size());
        

    }

}
