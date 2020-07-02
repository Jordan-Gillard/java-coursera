package breaking_vigenere_cypher;

import edu.duke.FileResource;

import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            stringBuilder.append(message.charAt(i));
        }
        return stringBuilder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slicedString = sliceString(encrypted, i, klength);
            int sliceKey = caesarCracker.getKey(slicedString);
            key[i] = sliceKey;
        }
        return key;
    }

    public void breakVigenere() {
        String encryptedMessage = new FileResource().asString();
        FileResource dictionaryFileResource = new FileResource();
        HashSet<String> dictionary = readDictionary(dictionaryFileResource);
        String decryptedMessage = breakForLanguage(encryptedMessage, dictionary);
        System.out.println(decryptedMessage);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> wordsInDictionary = new HashSet<String>();
        for (String word : fr.lines()) {  // each line is exactly one word
            String lowercaseWord = word.toLowerCase();
            wordsInDictionary.add(lowercaseWord);
        }
        return wordsInDictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int wordCount = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                wordCount += 1;
            }
        }
        return wordCount;
    }

    public String breakForLanguage(String message, HashSet<String> dictionary) {
        int numberOfDifferentKeysToTry = 100;
        int bestWordCount = -1;
        int bestKey = -1;
        String decryptedMessage = "";
        for (int key=1; key<=numberOfDifferentKeysToTry; key++) {
            int[] keys = tryKeyLength(message, key, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String attemptedDecryptedMessage = vc.decrypt(message);
            int wordCount = countWords(attemptedDecryptedMessage, dictionary);
            if (wordCount > bestWordCount) {
                bestWordCount = wordCount;
                bestKey = key;
                decryptedMessage = attemptedDecryptedMessage;
            }
        }
        System.out.println("Word Count: " + bestWordCount);
        System.out.println("Key length: " + bestKey);
        return decryptedMessage;
    }
}
