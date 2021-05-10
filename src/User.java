import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User {

    /**
     * 
     * @param zip
     * @return
     */
//    public boolean checkZip(String zip) {
//        if (zip.length() != 5) {
//            return false;
//        }
//        if (zip.matches("[0-9]+")) {
//            return true;
//        }
//        // read in valid zip
//        ArrayList<String> validzips = validZip();
//        if (validzips.contains(zip)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 
     * @param city
     * @return
     */
    public boolean checkCity(String city, Pos pos) {
        HashMap<String, List<String>> cities = pos.getStateAndCity(); 
        for(Map.Entry<String, List<String>> entry: cities.entrySet()) {
            for(String i : entry.getValue()) {
                if(city.equals(i)) {
                    return true; 
                }
            }
        }
//        ArrayList<String> validCities = validCities();
//        for (int i = 0; i < validCities.get(0).length(); i++) {
//            System.out.println(validCities.get(0).charAt(i));
//        }
//        if (validCities.contains(city)) {
//            return true;
//        }
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
     * void method to print top cities to search for 
     */
    public void printTopCity() {
        System.out.println("1.\tAustin");
        System.out.println("2.\tPortland");
        System.out.println("3.\tBoston");
        System.out.println("4.\tIrvine");
        System.out.println("5.\tHampton");
    }

    /**
     * method to get user input and call other functions
     */
    public void startPos() {

        Pos pos = new Pos();
        pos.loadRestaurantInfo("yelp_academic_dataset_business.json");

        System.out.println("===================================");
        System.out.println("|| \t Welcome to Yalp \t ||");
        System.out.println("===================================");
        // use a while loop
        System.out.println("+ a tool to find spots near you + ");
        System.out.println("");
        boolean running = true;

        while (running) {
            System.out.println("Please enter the city where you want to search: ");
            System.out.println("\n**If you need help on the valid cities type help "
                    + "to print the top five cities \n or type the state two " + "letter code to get a list of cities **");
            Scanner scanner = new Scanner(System.in);
            String city = scanner.nextLine().trim();
            city = city.toLowerCase();
            
            HashMap<String, List<String>> stateAndCity = pos.getStateAndCity();
            if (!(checkCity(city, pos) || stateAndCity.containsKey(city) ||
                    city.equals("help"))) {
                System.out.println("City name is invalid - please reenter");
                continue; 
            }
            // option to enter a state
            if (stateAndCity.containsKey(city)) {
                List<String> cities = stateAndCity.get(city);
                int j = 1;
                for (String i : cities) {
                    System.out.println(j + ".\t" + i);
                    j++;
                }
                System.out.println("Enter the number associated to the city you want to search for: ");
                int stateCityNum = scanner.nextInt();
                
                city = cities.get(stateCityNum - 1);
            }

            boolean getCity = false;
            if (city.equals("help")) {
                getCity = true;
            }
            while (getCity) {

                printTopCity();
                System.out.println("Enter the number associated to the city you want to search for: ");
                int cityNum = scanner.nextInt();
                getCity = false;
                if (cityNum == 1) {
                    city = "Austin";
                } else if (cityNum == 2) {
                    city = "Portland";
                } else if (cityNum == 3) {
                    city = "Boston";
                } else if (cityNum == 4) {
                    city = "Irvine";
                } else if (cityNum == 5) {
                    city = "Austin";
                } else {
                    System.out.println("Invalid number entered!Try again");
                    getCity = true;
                }

            }

//            
            

            double latitude = 0.0;
            double longitude = 0.0;
            boolean latAndLong = true;

            while (latAndLong) {
                System.out.println("Please enter your latitude and longitude in the format (latitude, longitude)");

                scanner = new Scanner(System.in);
                String[] locationInput = scanner.nextLine().split(", ");
                try {
                    latitude = Double.parseDouble(locationInput[0]);
                    longitude = Double.parseDouble(locationInput[1]);

                    latAndLong = false;
                } catch (Exception e) {
                    System.out.println("Incorrect format entered try again");
                }

//                    if (!checkLatAndLong(latitude, longitude) ) {
//                        System.out.println("Invalid latitude/ longitude - please reenter");
//                        latAndLong = true; 
//                    }
            }

            // set coordinates
            Point userCoordinates = new Point(latitude, longitude);
            pos.setUserCoordinates(userCoordinates);

            // compose tree
            pos.storeRestaurantsInTree(city);

            System.out.println("Please enter the category you want to search for: ");

            String category = scanner.next();

            System.out.println("Please enter the maxDist in meters:  ");
            double maxDist = scanner.nextDouble();
            boolean rating = true;
            double lowRating = 0;
            while (rating) {
                System.out.println("Please enter the lowest rating 1-5:");

                lowRating = scanner.nextDouble();
                rating = false;
                if (lowRating > 5 || lowRating < 1) {
                    System.out.println("Incorrect format entered, please try again");
                    rating = true;
                }
            }

            double highRating = 0;
            rating = true;
            while (rating) {
                System.out.println("Please enter the highest rating 1-5: ");

                highRating = scanner.nextDouble();
                rating = false;
                if (highRating > 5 || highRating < 1) {
                    System.out.println("Incorrect format entered, please try again");
                    rating = true;
                }
                if (highRating < lowRating) {
                    System.out.println("The high rating should be larger then the low rating.");
                    rating = true;
                }
            }

            List<IRestaurant> list = pos.searchForRestaurants(maxDist, lowRating, highRating, category);

            // ask user how many results they want
            System.out.println("How do you want to sort the output list? (Type star, or distance, or name)");

            String sortCriteria = scanner.next();

            System.out.println("Do you want your list to be ascending? (true or false)");
            Boolean ascending = scanner.nextBoolean();

            // user continues to search until they say end

            // to-do: validate cuisine
            pos.sortRestaurants(list, sortCriteria, ascending);
            System.out.println("How many records would you like to see?");
            int num = scanner.nextInt();

            if (num > list.size()) {
                System.out.println("There are not that many options " + "near you, but there are " + list.size()
                        + " options avaliable");
                num = list.size();
            }
            System.out.println(" ");
            System.out.println("Here is your list of restaurants: ");

            System.out.printf("  \t %-50s %-5s %-8s\n", "Name", "Star", "Distance (m)");
            System.out.println("-----------------------------------------");
            int count = 1;
            for (int i = 0; i < num; i++) {
                double distanceToUser = list.get(i).getLocation()
                        .distanceTo(Coordinates.latLongToPoint(userCoordinates.getX(), userCoordinates.getY()));
                System.out.printf("%d.\t %-50s %-5s %.0f\n", count, list.get(i), list.get(i).getStars(),
                        distanceToUser);
                count++;
            }

            System.out.println(" ");
            System.out.println("-----------------------------------------");

            System.out.println("Do you want to do another search? (Y/N)");
            String next = scanner.next();

            if (next.toLowerCase() == "n") {
                running = false;
            }
        }

    }

    /**
     * zip checker 
     * @return
     */
//    public ArrayList<String> validZip() {
//        ArrayList<String> validZip = new ArrayList<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("valid_zip.txt"));
//            String zip;
//            try {
//                zip = reader.readLine();
//                while (zip != null || zip.equals("")) {
//
//                    validZip.add(zip);
//                    zip = reader.readLine();
//                }
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return validZip;
//    }

    /**
     * city checker
     * @return
     */
//    public ArrayList<String> validCities() {
//        ArrayList<String> validCity = new ArrayList<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("city.txt"));
//            String city;
//            try {
//                city = reader.readLine();
//                while (city != null) {
//                    city = city.replaceAll("\\s+", "");
//                    validCity.add(city.trim());
//                    city = reader.readLine();
//
//                }
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return validCity;
//    }
}
