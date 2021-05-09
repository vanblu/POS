import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

      assertEquals(123148, output2);
        
    }

    @Test
    public void testStoreRestaurantsInTree() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(10.0,10.0));
        QuadTree tree = pos.storeRestaurantsInTree("Boulder");
       
        assertEquals(tree.getBotRight(), new Point(1121194.9079327357, 1105194.9079327357));
        assertEquals(tree.getTopLeft(), new Point(1105194.9079327357, 1121194.9079327357));
    }

    @Test
    public void testSearchForRestaruants() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(-105.2833481,40.0175444));
        pos.storeRestaurantsInTree("Boulder");
        
        List<IRestaurant> restaurants = pos.searchForRestaurants(100, 0, 5, "gastropubs");
        assertEquals(restaurants.get(0).getName(), "Oskar Blues Taproom");
        
    }
    
    @Test
    public void testSearchForRestaruantsFiltersByLocation() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(10.0,40.0175444));
        pos.storeRestaurantsInTree("Boulder");
        
        List<IRestaurant> restaurants = pos.searchForRestaurants(100, 0, 5, "gastropubs");
        assertEquals(restaurants.size(), 0);
        
        
    }
    
    @Test
    public void testSearchForRestaruantsFiltersByStars() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(-105.2833481,40.0175444));
        pos.storeRestaurantsInTree("Boulder");
        
        List<IRestaurant> restaurants = pos.searchForRestaurants(100, 3.8, 3.9, "gastropubs");
        assertEquals(restaurants.size(), 0);
        
    }
    
    //TODO: filtering by category is not working
    @Test
    public void testSearchForRestaruantsFilterByCategory() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        pos.setUserCoordinates(new Point(-105.2833481,40.0175444));
        pos.storeRestaurantsInTree("Boulder");
        
        List<IRestaurant> restaurants = pos.searchForRestaurants(100, 0, 5, "italian");
        assertEquals(restaurants.size(),0);
        
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
        pos.setUserCoordinates(new Point(-105.2833481,40.0175444));
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "distance", true);
       
        assertEquals(sortedRes.get(0).getName(),"Oskar Blues Taproom");
        assertEquals(sortedRes.get(1).getName(),"The Reclaimory");
        assertEquals(sortedRes.get(2).getName(),"Flying Elephants at PDX");
    }
    
    @Test
    public void testSortRestaurantsByDistDiscend() {
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        LinkedList<IRestaurant> restaurants = (LinkedList<IRestaurant>) pos.getRestaurants();
        Restaurant res1 = (Restaurant) restaurants.get(0);
        Restaurant res2 = (Restaurant) restaurants.get(1);
        Restaurant res3 = (Restaurant) restaurants.get(2);
        ArrayList<IRestaurant> listofRes = new ArrayList<IRestaurant>();
        pos.setUserCoordinates(new Point(-105.2833481,40.0175444));
        
        listofRes.add(res1);
        listofRes.add(res2);
        listofRes.add(res3);
        
        ArrayList<IRestaurant> sortedRes = (ArrayList<IRestaurant>) pos.sortRestaurants(listofRes, "distance", false);
        
       assertEquals(sortedRes.get(0).getName(),"Flying Elephants at PDX");
       assertEquals(sortedRes.get(1).getName(),"The Reclaimory");
       assertEquals(sortedRes.get(2).getName(),"Oskar Blues Taproom");
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
        List<String> categoryList = new ArrayList<String>();
        categoryList.add("gastropubs");
        categoryList.add("food");
        categoryList.add("beer gardens");
        categoryList.add("restaurants");
        categoryList.add("bars");
        categoryList.add("american (traditional)");
        categoryList.add("beer bar");
        categoryList.add("nightlife");
        categoryList.add("breweries");
        
        assertEquals( restaurants.get(0).getName(), "Oskar Blues Taproom");
        assertEquals( restaurants.get(0).getAddress().getState(), "CO");
        assertEquals( restaurants.get(0).getAddress().getCity(), "boulder");
        assertEquals( restaurants.get(0).getAddress().getStreet(), "921 Pearl St");
        assertEquals( restaurants.get(0).getAddress().getZipCode(), "80302");
        
        
        assertEquals( restaurants.get(0).getCategory(), categoryList); //TODO: create getCategories method and parse categories correctly
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
