package breaking_vigenere_cypher;

import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class VigenereBreakerTest {
    @Test
    void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String message = "abcdefghijklm";
        assertEquals(vb.sliceString(message, 0, 3), "adgjm");
        assertEquals(vb.sliceString(message, 1, 3), "behk");
        assertEquals(vb.sliceString(message, 2, 3), "cfil");
        assertEquals(vb.sliceString(message, 0, 4), "aeim");
        assertEquals(vb.sliceString(message, 1, 4), "bfj");
        assertEquals(vb.sliceString(message, 2, 4), "cgk");
        assertEquals(vb.sliceString(message, 3, 4), "dhl");
        assertEquals(vb.sliceString(message, 0, 5), "afk");
        assertEquals(vb.sliceString(message, 2, 5), "chm");
        assertEquals(vb.sliceString(message, 3, 5), "di");
        assertEquals(vb.sliceString(message, 4, 5), "ej");
    }

    @Test
    void testTryKeyLength() {
        int[] expected = {5, 11, 20, 19, 4};
        FileResource fr = new FileResource("text_files/athens_keyflute.txt");
        String encryptedString = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(encryptedString, 5, 'e');
        assertArrayEquals(keys, expected);
    }

    @Test
    void testReadDictionary() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> englishDictionary = vb.readDictionary(fr);
        assertEquals(72053, englishDictionary.size());
        assertTrue(englishDictionary.contains("undermining"));
        assertTrue(englishDictionary.contains("abrupt"));
    }

    @Test
    void testCountWords() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> englishDictionary = vb.readDictionary(fr);
        String message = new FileResource("text_files/titus-small.txt").asString();
        int wordCount = vb.countWords(message, englishDictionary);
        assertEquals(39, wordCount);
    }

    @Test
    void testBreakForLanguage() {
        String encryptedMessage = new FileResource("text_files/athens_keyflute.txt").asString();
        FileResource fr = new FileResource("dictionaries/English");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> englishDictionary = vb.readDictionary(fr);
        String unencryptedMessage = vb.breakForLanguage(encryptedMessage, englishDictionary);
        String expected = new FileResource("text_files/athens.txt").asString();
        assertEquals(expected, unencryptedMessage);
    }

    @Test
    void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("dictionaries/English");
        HashSet<String> englishDictionary = vb.readDictionary(fr);
        char mostCommonChar = vb.mostCommonCharIn(englishDictionary);
        assertEquals('e', mostCommonChar);

        fr = new FileResource("dictionaries/Portuguese");
        HashSet<String> portugueseDictionary = vb.readDictionary(fr);
        mostCommonChar = vb.mostCommonCharIn(portugueseDictionary);
        assertEquals('a', mostCommonChar);
    }

    @Test
    void testBreakForAllLangs() {
        VigenereBreaker vb = new VigenereBreaker();
        File languageDir = new File("src/test/resources/dictionaries");
        HashMap<String, HashSet<String>> languages = vb.getAllDictionaries(languageDir);
        String encryptedMessage = new FileResource("text_files/athens_keyflute.txt").asString();
        String[] detectedLanguageAndMessage = vb.breakForAllLangs(encryptedMessage, languages);
        String expected = new FileResource("text_files/athens.txt").asString();
        assertArrayEquals(detectedLanguageAndMessage, new String[]{"English", expected});
    }
}
