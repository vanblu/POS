import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonParser {

    public Restaurant getValues(JSONObject jo, String zip) {
        long open = (long) jo.get("is_open");
        String z = (String) jo.get("postal_code");
        
        

        if (z.equals(zip) && open == 1) {
            Restaurant r = new Restaurant();
            r.setName((String) jo.get("name"));
            r.setCategory((String) jo.get("categories"));
            Address a = new Address();
            a.setCity((String) jo.get("city"));
            a.setState((String) jo.get("state"));
            a.setZipCode(z);
            a.setStreet((String) jo.get("address"));
            r.setAddress(a);

            return r;
        }
        return null;
    }

    public List<Restaurant> read(String filename, String zip) {
        // output file
        List<Restaurant> l = new ArrayList<>();

        // parsing file
        Object obj;
        try {
            File fileObj = new File(filename);
            Scanner reader = new Scanner(fileObj);

            while (reader.hasNextLine()) {
                String jsonLine = reader.nextLine();
                obj = new JSONParser().parse(jsonLine);
                JSONObject jo = (JSONObject) obj;

                Restaurant r = getValues(jo, zip);
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

        System.out.println(l);
        return l;
    }

}
