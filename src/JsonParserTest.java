import static org.junit.Assert.*;

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

//        System.out.println(output2.size());
        
        assertEquals(123248, output2.size());

    }

    @Test
    public void testWriteZip() {
        JsonParser test = new JsonParser();

        List<String> file = test.writeFile();

        assertEquals(110498, file.size());

    }

    @Test
    public void testWriteCity() {
        JsonParser test = new JsonParser();

        List<String> file = test.writeFileCity();

        assertEquals(672, file.size());

    }

}
