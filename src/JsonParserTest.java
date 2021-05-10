import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class JsonParserTest {

    @Test
    public void testRead() {
        JsonParser test = new JsonParser();
        List<Restaurant> output = test.read("yelp_academic_dataset_business_TEST.json");

        assertEquals(23, output.size());

    }

    @Test
    public void testReadLargeData() {
        JsonParser test = new JsonParser();

        List<Restaurant> output2 = test.read("yelp_academic_dataset_business.json");

        
        assertEquals(123148, output2.size());

    }
/*
    @Test
    public void testWriteZip() {
        JsonParser test = new JsonParser();

        List<String> file = test.writeFile();

        assertEquals(110499, file.size());

    }
    */

    @Test
    public void testWriteCity() {
        JsonParser test = new JsonParser();

        HashMap<String, List<String>> file = test.writeFileCity();

        assertEquals(29, file.size());

    }

}
