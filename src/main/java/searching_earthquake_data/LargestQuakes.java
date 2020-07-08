package searching_earthquake_data;

import java.util.ArrayList;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.println("read data for " + quakes.size() + " quakes.");
        // System.out.println(quakes);
        // int indexLargest = indexOfLargest(quakes);
        // System.out.println("Index of largest quake is " + indexLargest);
        // System.out.println(quakes.get(indexLargest));
        ArrayList<QuakeEntry> largestQuakes = getLargest(quakes, 5);
        System.out.println(largestQuakes);
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexLargest = 0;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > data.get(indexLargest).getMagnitude()) {
                indexLargest = i;
            }
        }
        return indexLargest;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < Math.min(quakeData.size(), howMany); i++) {
            int largestIndex = indexOfLargest(quakeDataCopy);
            answer.add(quakeDataCopy.get(largestIndex));
            quakeDataCopy.remove(largestIndex);
        }
        return answer;
    }
}
