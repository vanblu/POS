import static org.junit.Assert.*;

import org.junit.Test;

public class PosTest {

    @Test
    public void testLoadRestaurantInfo() {
        Pos test = new Pos();
        int output = test.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        assertEquals(23, output);
        
        int output2 = test.loadRestaurantInfo("yelp_academic_dataset_business.json");

        assertEquals(123248, output2);
        
    }

    @Test
    public void testStoreRestaurantsInTree() {
        fail("Not yet implemented");
    }

    @Test
    public void testSearchForRestaruants() {
        fail("Not yet implemented");
    }

    @Test
    public void testSortRestaurants() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetRestaurants() {
        fail("Not yet implemented");
    }

}
