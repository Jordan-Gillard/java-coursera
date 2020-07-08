package searching_earthquake_data;

import java.util.ArrayList;


public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double dist = from.distanceTo(qe.getLocation());  // answer in meters
            double distanceInKm = dist / 1000;
            if (distanceInKm < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "quake_data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        ArrayList<QuakeEntry> overFiveMag = filterByMagnitude(list, 5.0);
        System.out.println(overFiveMag);
    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        ArrayList<QuakeEntry> filteredEarthQuakes = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : filteredEarthQuakes) {
            double distanceTo = city.distanceTo(qe.getLocation());
            System.out.println("Distance from city to location: " + distanceTo);
            System.out.println(qe.getInfo());
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (minDepth > qe.getDepth() && qe.getDepth() > maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth() {
        double minDepth = -5000.0;
        double maxDepth = -10000.0;
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> allQuakes = parser.read(source);
        System.out.println("Read data for " + allQuakes.size() + " quakes.");
        System.out.println("Finding quakes between depth " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> filteredQuakes = filterByDepth(allQuakes, minDepth, maxDepth);
        System.out.println(filteredQuakes);
        System.out.println("Found " + filteredQuakes.size() + " that match the criteria.");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String name = qe.getInfo();
            if (where.equals("start")) {
                if (name.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else if (where.equals("end")) {
                if (name.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            else {
                if (name.contains(phrase)) {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> allQuakes = parser.read(source);
        System.out.println("Read data for " + allQuakes.size() + " quakes.");
        String phrase = "California";
        String where = "end";
        ArrayList<QuakeEntry> filteredQuakes = filterByPhrase(allQuakes, where, phrase);
        System.out.println(filteredQuakes);
        System.out.println("Found " + filteredQuakes.size() + " that match the criteria.");
        phrase = "Can";
        where = "any";
        System.out.println("Searching using keyword 'can' and where being 'any'.");
        filteredQuakes = filterByPhrase(allQuakes, where, phrase);
        System.out.println(filteredQuakes);
        System.out.println("Found " + filteredQuakes.size() + " that match the criteria.");
    }
}
