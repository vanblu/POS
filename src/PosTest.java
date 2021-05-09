import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class PosTest {
    private Pos pos;
    
    @Before
    public void setUp() {
        pos = new Pos();
    }

    @Test
    public void testLoadRestaurantInfo() {
        int output = pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        assertEquals(23, output);
        
       int output2 = pos.loadRestaurantInfo("yelp_academic_dataset_business.json");

      assertEquals(123248, output2);
        
    }

    @Test
    public void testStoreRestaurantsInTree() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(10.0,10.0));
        QuadTree tree = pos.storeRestaurantsInTree("Boulder");
        //TODO: need to fix QuadTree: should calculate the correct coordinates(botRight, topLeft)
       // assertEquals(tree.getBotRight(), new Point())
        
    }

    @Test
    public void testSearchForRestaruants() {
        fail("Not yet implemented");
    }

    @Test
    public void testSortRestaurantsByStarAscend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "star", true);
        
        assertEquals(sortedRes.get(0).getName(),"Flying Elephants at PDX");
        assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
        assertEquals(sortedRes.get(2).getName(),"The Reclaimory");
    }

    
    @Test
    public void testSortRestaurantsByStarDescend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "star", false);
        
        assertEquals(sortedRes.get(0).getName(),"The Reclaimory");
        assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
        assertEquals(sortedRes.get(2).getName(),"Flying Elephants at PDX");
    }

    
    @Test
    public void testSortRestaurantsByDistAscend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "distance", true);
        //TODO: fix assertEquals with proper distance values
       // assertEquals(sortedRes.get(0).getName(),"The Reclaimory");
       // assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
       // assertEquals(sortedRes.get(2).getName(),"Flying Elephants at PDX");
    }
    
    @Test
    public void testSortRestaurantsByDistDiscend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "distance", false);
        //TODO: fix assertEquals with proper distance values
       // assertEquals(sortedRes.get(0).getName(),"The Reclaimory");
       // assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
       // assertEquals(sortedRes.get(2).getName(),"Flying Elephants at PDX");
    }
    
    @Test
    public void testSortRestaurantsByNameAscend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "name", true);
        
       assertEquals(sortedRes.get(0).getName(),"Flying Elephants at PDX");
       assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
       assertEquals(sortedRes.get(2).getName(),"The Reclaimory");
    }

    @Test
    public void testSortRestaurantsByNameDescend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "name", false);
        
       assertEquals(sortedRes.get(0).getName(),"The Reclaimory");
       assertEquals(sortedRes.get(1).getName(),"Oskar Blues Taproom");
       assertEquals(sortedRes.get(2).getName(),"Flying Elephants at PDX");
    }



    @Test
    public void testGetRestaurants() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
  
        assertEquals( restaurants.get(0).getName(), "Oskar Blues Taproom");
       // assertEquals( restaurants.get(0).getCusineType(), "Oskar Blues Taproom"); //TODO: there is no cuisine in the JSON
        assertEquals( restaurants.get(0).getAddress().getState(), "CO");
        assertEquals( restaurants.get(0).getAddress().getCity(), "boulder");
        assertEquals( restaurants.get(0).getAddress().getStreet(), "921 Pearl St");
        assertEquals( restaurants.get(0).getAddress().getZipCode(), "80302");
       // assertEquals( restaurants.get(0).getCategory(), "Oskar Blues Taproom"); //TODO: create getCategories method and parse categories correctly
        assertEquals( restaurants.get(0).getLatitude(), 40.0175444, 0.001);
        assertEquals( restaurants.get(0).getLocation(), Coordinates.latLongToPoint(40.0175444, -105.2833481)); 
        assertEquals( restaurants.get(0).getLongitude(), -105.2833481, 0.001);
        assertEquals( restaurants.get(0).getStars(), 4.0, 0.001);
        
        
    }

    
    @Test
    public void getCoordinates() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(10.0,10.0));
        
        assertEquals(pos.getUserCoordinates(), new Point(10.0, 10.0));
    }
}
