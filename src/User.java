import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
   
   
   /**
    * 
    * @param zip
    * @return
    */
    public boolean checkZip(String zip) {
        if (zip.length() != 5) {
            return false;
        }
        if (zip.matches("[0-9]+")) {
            return true;
        }
        // read in valid zip 
        ArrayList<String> validzips = validZip();
        if(validzips.contains(zip)) {
            return true; 
        }
        return false;
    }

    public boolean checkCity(String city) {
        
        ArrayList<String> validCity = validCity();
        if(validCity.contains(city)) {
            return true; 
        }
        return false;
    }
    
    /**
     * 
     * @param latitude
     * @param longtitude
     * @return
     */
    public boolean checkLatAndLong(double latitude, double longtitude) {
        if (latitude < -90 || latitude > 90) {
            return false;
        }
        if (longtitude < -180 || longtitude > 180) {
            return false;
        }
        return true;
    }
    
   /**
    * method to get user input and call other functions 
    */
    public void startPos(){
        System.out.println("Welcome to Yalp");
        // use a while loop 
        
        System.out.println(
                "Please enter 5-digit zipcode, latitude, and longitude you want to order from (e.g 80302, 40.7, -74.0)");

        Scanner scanner = new Scanner(System.in);
        String[] locationInput = scanner.nextLine().split(", ");
        String city = locationInput[0];
        double latitude = Double.parseDouble(locationInput[1]);
        double longitude = Double.parseDouble(locationInput[2]);
        

        while ( !checkLatAndLong(latitude, longitude) || !checkCity(city)) {

            System.out.println(
                    "Please enter 5-digit zipcode, latitude, and longitude you want to order from (e.g 11111, 40.7, -74.0)");

            scanner = new Scanner(System.in);
            locationInput = scanner.nextLine().split(", ");
            city = locationInput[0];
            latitude = Double.parseDouble(locationInput[1]);
            longitude = Double.parseDouble(locationInput[2]);

        }
        Pos pos = new Pos();
        pos.loadRestauranInfo("yelp_academic_dataset_business.json");
        
        Point userCoordinates = new Point(latitude,longitude);
        pos.setUserCoordinates(userCoordinates);
        pos.storeRestaurantsInTree();
        
        System.out.println(" ");
        // to-do: validate
        System.out.println("Please enter the cuisine you want to order");
        
        
        String cuisineType = scanner.nextLine();
        
        //TODO : update user display 
        System.out.println("Please enter the maxDist you want to order");
        double maxDist = scanner.nextDouble();
        System.out.println("Please enter the maxDist you want to order");
        double lowRatng = scanner.nextDouble();
        System.out.println("Please enter the maxDist you want to order");
        double highRating = scanner.nextDouble();
        
        pos.searchForRestaruants(maxDist, lowRatng, highRating, cuisineType);
        //TODO: ask user how they want to sort the result 
        //TODO: call this in the pos 
        // ask user how many results they want 
        pos.sortRestaurants(restaurants, sortCriteria, ascending);
        
        
        
        
        //user continues to search until they say end 
        
        System.out.println(" ");
        // to-do: validate cuisine
        System.out.println("Please enter the price range (8-30)");
        String priceRange = scanner.nextLine();
        System.out.println(" ");
        // to-do: validate price range
        String lowestPrice = priceRange.split("-")[0];
        String highestPrice = priceRange.split("-")[1];

        System.out.println("filtering by location: " + zipcode + ", cuisine: " + cuisine + ", and price range: from $"
                + lowestPrice + " to $" + highestPrice);
        // getRestaurantsByLocation
        System.out.println(" ");
        ArrayList<String> restaurants = new ArrayList<String>();
        restaurants.add("Shake Shack");
        restaurants.add("McDonalds");
        restaurants.add("kfc");

        System.out.println("Here is your list of restaurants: ");

        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println(restaurants.get(i));
        }

        System.out.println("Select a restaurant to order from");

        String restaurant = scanner.nextLine();
        System.out.println(" ");
        // to-do: keep on asking for restaurants until valid name appears
        if (restaurants.contains(restaurant)) {
            // to-do: getMenu
            ArrayList<String> menuItems = new ArrayList<String>();
            menuItems.add("chicken");
            menuItems.add("burger");
            menuItems.add("noodles");

            System.out.println("Here is the menu for " + restaurant + ": ");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println(menuItems.get(i));
            }
        } else {
            System.out.println("No " + restaurant + " near you");
        }

    }
    
    public ArrayList<String> validZip() {
       ArrayList<String> validZip = new ArrayList<>();
        try {
        BufferedReader reader = new BufferedReader(new FileReader("valid_zip.txt"));
        String zip;
        try {
            zip = reader.readLine();
            while(zip != null || zip.equals("")) {
                
                validZip.add(zip);
                zip = reader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        
        return validZip; 
    }
            
    
    public ArrayList<String> validCity() {
        ArrayList<String> validCity = new ArrayList<>();
         try {
         BufferedReader reader = new BufferedReader(new FileReader("city.txt"));
         String city;
         try {
             city = reader.readLine();
             while(city != null || city.equals("")) {
                 
                 validCity.add(city);
                 city = reader.readLine();
                 
             }
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        
         
     } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
         
         return validCity; 
     }
}
