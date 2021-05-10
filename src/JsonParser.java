import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonParser {

    /**
     * This method is to parse out the restaurant details from the JSON file
     * 
     * @param JSON object - one entry
     * @return parsed out Restaurant details
     */

    public Restaurant getValues(JSONObject jo) {

        long open = (long) jo.get("is_open");
        String z = (String) jo.get("postal_code");
        String c = (String) jo.get("city");
        Double latitude = (Double) jo.get("latitude");
        Double longitude = (Double) jo.get("longitude");
        String name = (String) jo.get("name");

        double stars = (double) jo.get("stars");
        String category = (String) jo.get("categories");

        // make case insensitive
        c = c.toLowerCase();

        if (latitude == null || longitude == null || c == null || name == null

                || category == null) {
            return null;
        }

        if (open == 1) {
            Restaurant r = new Restaurant();
            r.setName(name);

            Address a = new Address();
            a.setCity(c);
            a.setState((String) jo.get("state"));
            a.setZipCode(z);
            a.setStreet((String) jo.get("address"));
            r.setAddress(a);
            if (latitude != null) {
                r.setLatitude(latitude);
            }
            if (longitude != null) {
                r.setLongtitude(longitude);
            }

            r.setStars(stars);

            String[] elements = category.split(", ");

            List<String> list = Arrays.asList(elements);
            int j = 0;
            for (String i : list) {
                list.set(j, i.toLowerCase());
                j++;
            }

            r.setCategory(list);
          
            return r;
        }
        return null;
    }

    /**
     * This method reads in the yelp source file and return a list of restaurant
     * with in the zip
     * 
     * @param filename
     * @return list of restaurants
     */
    public List<Restaurant> read(String filename) {
        // output file
        List<Restaurant> l = new ArrayList<>();

        // parsing file
        Object obj;
        try {
            File fileObj = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(fileObj));

            String line;
            while ((line = reader.readLine()) != null) {

                obj = new JSONParser().parse(line);
                JSONObject jo = (JSONObject) obj;

                Restaurant r = getValues(jo);
                if (r != null) {

                    l.add(r);
                }

            }
            reader.close();
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return l;
    }
/**
 * takes in a JSON object and find the city tag and return the city 
 * @param jo
 * @return returns the city associated to the location 
 */
    public String getCityValues(JSONObject jo) {

        long open = (long) jo.get("is_open");
        String city = (String) jo.get("city");

        if (open == 1) {

            return city;
        }
        return null;
    }

    public String getStateValues(JSONObject jo) {

        long open = (long) jo.get("is_open");
        String state = (String) jo.get("state");

        if (open == 1) {

            return state;
        }
        return null;
    }

    /**
     * parse out all the city and state combinations 
     * @param filename
     * @return list of state with city 
     */
    public HashMap<String, List<String>> readAllCity(String filename) {
        // output file
        HashMap<String, List<String>> l = new HashMap<>();

        Object obj;
        try {
            File fileObj = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(fileObj));

            String line;
            try {
                while ((line = reader.readLine()) != null) {

                    obj = new JSONParser().parse(line);
                    JSONObject jo = (JSONObject) obj;

                    String city = getCityValues(jo);
                    String state = getStateValues(jo);
                    if (city != null && state != null) {
                        state = state.toLowerCase();
                        city = city.toLowerCase();
                        city.trim();
                        if (l.containsKey(state)) {
                            if (!l.get(state).contains(city)) {
                                l.get(state).add(city);
                            }
                        } else {
                            List<String> cities = new ArrayList<>();

                            l.put(state, cities);
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return l;
    }

    /**
     * write the state and cities and state into a text file 
     * @return
     */
    public HashMap<String, List<String>> writeFileCity() {
        HashMap<String, List<String>> zipList = readAllCity("yelp_academic_dataset_business.json");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("stateAndCity.txt");

            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

            for (Map.Entry<String, List<String>> entry : zipList.entrySet()) {
                for (String i : entry.getValue()) {
                    outStream.writeChars(entry.getKey() + ", " + i + "\n");
                }

            }

            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zipList;
    }
}
