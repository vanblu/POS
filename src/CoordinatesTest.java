import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinatesTest {

    @Test
    public void testLatLongToPoint() {
        Point testPoint = Coordinates.latLongToPoint(40.017675845638266, -105.28327300072598);
        assertEquals(-1.172008E7, testPoint.getX(), 1);
        assertEquals(4454747.2979, testPoint.getY(), 1);
    }


}
