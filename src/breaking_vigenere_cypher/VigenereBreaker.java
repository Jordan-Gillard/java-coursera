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
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i=0; i<klength; i++) {
            String slicedString = sliceString(encrypted, i, klength);
            int sliceKey = caesarCracker.getKey(slicedString);
            key[i] = sliceKey;
        }
        return key;
    }

    public String breakVigenere (String encryptedMessage, int klength, char mostCommon) {
        int[] keys = tryKeyLength(encryptedMessage, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(keys);
        return vc.decrypt(encryptedMessage);
    }
}
