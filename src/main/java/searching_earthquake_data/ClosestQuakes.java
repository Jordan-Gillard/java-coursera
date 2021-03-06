package searching_earthquake_data;

/*
 * Find N-closest quakes
 *
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.ArrayList;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copiedQuakeData = new ArrayList<QuakeEntry>(quakeData);
        int minIndex = 0;
        for (int i = 0; i < howMany; i++) {
            for (int k = 0; k < copiedQuakeData.size(); k++) {
                QuakeEntry currentQuake = copiedQuakeData.get(k);
                QuakeEntry closestQuake = copiedQuakeData.get(minIndex);
                if (currentQuake.getLocation().distanceTo(current) <
                        closestQuake.getLocation().distanceTo(current)) {
                    minIndex = k;
                }
            }
            answer.add(copiedQuakeData.get(minIndex));
            copiedQuakeData.remove(minIndex);
            minIndex = 0;
            if (copiedQuakeData.size() == 0) {
                break;
            }
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes.");

        Location jakarta = new Location(-6.211, 106.845);
        int howMany = 3;

        ArrayList<QuakeEntry> close = getClosest(list, jakarta, howMany);
        for (int k = 0; k < close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters / 1000, entry);
        }
        System.out.println("number found: " + close.size());
    }
}