package main.java.breaking_vigenere_cypher;

import edu.duke.FileResource;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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
        HashMap<String, HashSet<String>> languages = getAllDictionaries("test/breaking_vigenere_cypher/dictionaries");
        String[] languageMessage = breakForAllLangs(encryptedMessage, languages);
        String language = languageMessage[0];
        String message = languageMessage[1];
        System.out.println("Message written in " + language + ". Message is:");
        System.out.println(message);
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
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int key = 1; key <= numberOfDifferentKeysToTry; key++) {
            int[] keys = tryKeyLength(message, key, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String attemptedDecryptedMessage = vc.decrypt(message);
            int wordCount = countWords(attemptedDecryptedMessage, dictionary);
            if (wordCount > bestWordCount) {
                bestWordCount = wordCount;
                bestKey = key;
                decryptedMessage = attemptedDecryptedMessage;
            }
        }
        return decryptedMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                charCounts.put(letter, charCounts.getOrDefault(letter, 0) + 1);
            }
        }
        char maxChar = 'a';
        int maxVal = 0;
        for (char key : charCounts.keySet()) {
            if (charCounts.get(key) > maxVal) {
                maxVal = charCounts.get(key);
                maxChar = key;
            }
        }
        return maxChar;
    }

    public String[] breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String bestDecryptedMessage = "";
        int bestWordCount = -1;
        String bestLanguage = "";
        for (String language : languages.keySet()) {
            String decryptedMessage = breakForLanguage(encrypted, languages.get(language));
            int wordCount = countWords(decryptedMessage, languages.get(language));
            if (wordCount > bestWordCount) {
                bestWordCount = wordCount;
                bestDecryptedMessage = decryptedMessage;
                bestLanguage = language;
            }
        }
        return new String[]{bestLanguage, bestDecryptedMessage};
    }

    public HashMap<String, HashSet<String>> getAllDictionaries(String filepath) {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        File languageDir = new File(filepath);
        for (File file : Objects.requireNonNull(languageDir.listFiles())) {
            String languageName = file.getName();
            FileResource fr = new FileResource(file);
            HashSet<String> words = readDictionary(fr);
            languages.put(languageName, words);
        }
        return languages;
    }
}
