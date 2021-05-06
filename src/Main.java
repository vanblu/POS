import java.util.ArrayList;
import java.util.Scanner;

public class Main {
public static void main(String [] args) {
    System.out.println("Welcome to Yalp");
    System.out.println("Please enter 5-digit zipcode, latitude, and longitude you want to order from (e.g 11111, 40.7, -74.0)");
    
    
    Scanner scanner = new Scanner(System.in);
    String[] locationInput = scanner.nextLine().split(", ");
    String zipcode = locationInput[0];
    double latitude = Double.parseDouble(locationInput[1]);
    double longitude = Double.parseDouble(locationInput[2]);
    
    System.out.println(" ");
    //to-do: validate 
    System.out.println("Please enter the cuisine you want to order");
    
    String cuisine = scanner.nextLine();
    System.out.println(" ");
    //to-do: validate cuisine
    System.out.println("Please enter the price range (8-30)");
    String priceRange = scanner.nextLine();
    System.out.println(" ");
    //to-do: validate price range 
    String lowestPrice = priceRange.split("-")[0];
    String highestPrice = priceRange.split("-")[1];
    
    System.out.println("filtering by location: " + zipcode + ", cuisine: " + cuisine + ", and price range: from $" + lowestPrice + " to $" + highestPrice);
    //getRestaurantsByLocation
    System.out.println(" ");
    ArrayList<String> restaurants = new ArrayList<String>();
    restaurants.add("Shake Shack");
    restaurants.add("McDonalds");
    restaurants.add("kfc");
    
    System.out.println("Here is your list of restaurants: ");
    
    for (int  i= 0;  i < restaurants.size(); i++) {
        System.out.println(restaurants.get(i));
    }
    
    System.out.println("Select a restaurant to order from");

    String restaurant = scanner.nextLine();
    System.out.println(" ");
    //to-do: keep on asking for restaurants until valid name appears
    if (restaurants.contains(restaurant)) {
        //to-do: getMenu
        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("chicken");
        menuItems.add("burger");
        menuItems.add("noodles");
        
        System.out.println("Here is the menu for "+ restaurant + ": ");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(menuItems.get(i));
        }
    }else {
        System.out.println("No "+ restaurant+ " near you");
    }
    
    
}
}
