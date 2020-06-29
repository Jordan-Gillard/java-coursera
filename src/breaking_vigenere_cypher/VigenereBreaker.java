package breaking_vigenere_cypher;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices) {
            stringBuilder.append(message.charAt(i));
        }
        return stringBuilder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
