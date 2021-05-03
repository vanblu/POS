import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class JsonParserTest {



    @Test
    public void testRead() {
        JsonParser test = new JsonParser();
        List<Restaurant> output = test.read("yelp_academic_dataset_business_TEST.json", "80302");
        
        assertEquals(2, output.size());
    }

}
