import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        
        ArrayList<String> validCities = validCities();
        for (int i = 0; i < validCities.get(0).length(); i++) {
            System.out.println(validCities.get(0).charAt(i));
        }
        if (validCities.contains(city)) {
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
        
        Pos pos = new Pos();
        pos.loadRestaurantInfo("yelp_academic_dataset_business_TEST.json");
        
        boolean running = true;
        
        while (running) {
            System.out.println(
                    "Please enter your current city");
            Scanner scanner = new Scanner(System.in);
            String city = scanner.nextLine().trim();
            
           
//            
//            if (!checkCity(city)) {
//                System.out.println("City name is invalid - please reenter");
//                continue; 
//            }
                        
            double latitude;
            double longitude;
            
            do {
                System.out.println(
                        "Please enter your latitude and longitude in the format (latitude, longitude)");
                
                scanner = new Scanner(System.in);
                String[] locationInput = scanner.nextLine().split(", ");
                latitude = Double.parseDouble(locationInput[0]);
                longitude = Double.parseDouble(locationInput[1]);
                if (!checkLatAndLong(latitude, longitude)) {
                    System.out.println("Invalid latitude/ longitude - please reenter");
                }
            } while (!checkLatAndLong(latitude, longitude));
          
            
            Point userCoordinates = new Point(latitude,longitude);
            pos.setUserCoordinates(userCoordinates);
            
            pos.storeRestaurantsInTree(city);
            
            System.out.println(" ");
            // to-do: validate
            System.out.println("Please enter the cuisine you want to order");
            
            
            String cuisineType = scanner.nextLine();
            
            //TODO : update user display 
            System.out.println("Please enter the maxDist in meters ");
            double maxDist = scanner.nextDouble();
            System.out.println("Please enter the lowest rating 1-5:");
            double lowRatng = scanner.nextDouble();
            System.out.println("Please enter the highest rating 1-5: ");
            double highRating = scanner.nextDouble();
            
            List<IRestaurant>  list = pos.searchForRestaruants(maxDist, lowRatng, highRating, cuisineType);
            //TODO: ask user how they want to sort the result 
            //TODO: call this in the pos 
            // ask user how many results they want 
            System.out.println("How do you want to sort the output list? (Type star, or distance, or name)");
           
            String sortCriteria = scanner.nextLine();
            
            System.out.println("Do you want your list to be ascending? (Y or N)");
            Boolean ascending = scanner.nextLine().equals("Y");
            pos.sortRestaurants(list, sortCriteria, ascending);
           
            //user continues to search until they say end 
            
            System.out.println(" ");
            // to-do: validate cuisine
           

            System.out.println("Here is your list of restaurants: ");

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
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
            
    
    public ArrayList<String> validCities() {
        ArrayList<String> validCity = new ArrayList<>();
         try {
         BufferedReader reader = new BufferedReader(new FileReader("city.txt"));
         String city;
         try {
             city = reader.readLine();
             while(city != null) {
                 city = city.replaceAll("\\s+","");
                 validCity.add(city.trim());
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
