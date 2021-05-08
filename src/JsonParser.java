import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonParser {


    
    
    /** This method is to parse out the restaurant details from the JSON
     * file 
     * @param JSON object - one entry 
     * @return parsed out Restaurant details 
     */
    
    public Restaurant getValues(JSONObject jo) {
        
        long open = (long) jo.get("is_open");
        String z = (String) jo.get("postal_code");
        String c = (String) jo.get("city");
        Double latitude = (Double)jo.get("latitude");
        Double longtitude = (Double)jo.get("longtitude");
        String name = (String) jo.get("name");
        String cusineType  = (String) jo.get("cusineType");  
        double stars = (double) jo.get("stars") ; 
        String category = (String) jo.get("categories") ; 
        
        // make case insensitive 
        c = c.toLowerCase();

//        
//       if(latitude == null || longtitude == null) {
//           return null; 
//       }
//        

        if (open == 1 ) {
            Restaurant r = new Restaurant();
            r.setName(name);
            r.setCategory((String) jo.get("categories"));
            Address a = new Address();
            a.setCity(c);
            a.setState((String) jo.get("state"));
            a.setZipCode(z);
            a.setStreet((String) jo.get("address"));
            r.setAddress(a);
            if(latitude != null) {
                r.setLatitude(latitude);
            }
            if(longtitude != null) {
                r.setLongtitude(longtitude);
            }
            
            r.setStars(stars);
            r.setCusineType(cusineType);
            r.setCategory(category);
           
            return r;
        }
        return null;
    }

    /**
     * This method reads in the yelp source file and return a list of restaurant 
     * with in the zip 
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return l;
    }
    
    
 public String getValuesZip(JSONObject jo) {
        
        long open = (long) jo.get("is_open");
        String z = (String) jo.get("postal_code");
        
        

        if ( open == 1 && checkZip(z)) {
            
            return z;
        }
        return null;
    }
    
    public List<String> readAllZip(String filename) {
        // output file
        List<String> l = new ArrayList<>();

        // parsing file
        Object obj;
        try {
            File fileObj = new File(filename);
            Scanner reader = new Scanner(fileObj);

            while (reader.hasNextLine()) {
                String jsonLine = reader.nextLine();
                obj = new JSONParser().parse(jsonLine);
                JSONObject jo = (JSONObject) obj;

                String zip = getValuesZip(jo);
                if (zip != null || !l.contains(zip)) {

                    l.add(zip);
                }

            }
            reader.close();
        } catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return l;
    }

    public boolean checkZip(String zip) {
        if (zip.length() != 5) {
            return false;
        }
        if (zip.matches("[0-9]+")) {
            return true;
        }
        
        return false;
    }

    public List<String> writeFile() {
        List<String> zipList = readAllZip("yelp_academic_dataset_business.json");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("zip_codes_c.txt");
            
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            
            for(String i : zipList) {
                outStream.writeChars("\""+i +"\", ");
            }
            
            outStream.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return zipList; 
    }
    
 public String getCityValues(JSONObject jo) {
        
        long open = (long) jo.get("is_open");
        String city = (String) jo.get("city");
        
        

        if ( open == 1 ) {
            
            return city;
        }
        return null;
    }
    
    public List<String> readAllCity(String filename) {
        // output file
        List<String> l = new ArrayList<>();

        // parsing file
        Object obj;
        try {
            File fileObj = new File(filename);
            Scanner reader = new Scanner(fileObj);

            while (reader.hasNextLine()) {
                String jsonLine = reader.nextLine();
                obj = new JSONParser().parse(jsonLine);
                JSONObject jo = (JSONObject) obj;

                String city = getCityValues(jo);
            
                if (city != null && !l.contains(city)) {
                    city = city.toLowerCase();
                    city.trim();
                    if(!l.contains(city)) {
                        l.add(city);
                    }
                    
                }

            }
            reader.close();
        } catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return l;
    }
    
    public List<String> writeFileCity() {
        List<String> zipList = readAllCity("yelp_academic_dataset_business.json");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("city.txt");
            
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            
            for(String i : zipList) {
                outStream.writeChars(i + "\n");
            }
            
            outStream.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return zipList; 
    }
}
