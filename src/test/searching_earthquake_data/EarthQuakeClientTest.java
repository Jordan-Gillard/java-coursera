package searching_earthquake_data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EarthQuakeClientTest {
    @Test
    public void testFilterByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/test/resources/quake_data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        EarthQuakeClient eqc = new EarthQuakeClient();
        ArrayList<QuakeEntry> overFiveMag = eqc.filterByMagnitude(list, 5.0);
        System.out.println(overFiveMag);
        assertEquals(3, overFiveMag.size());
    }
}