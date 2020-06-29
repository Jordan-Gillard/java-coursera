package breaking_vigenere_cypher;

import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCrackerTest {
    @Test
    public void testDecryptEnglish() {
        FileResource fr = new FileResource("breaking_vigenere_cypher/text_files/titus-small_key5.txt");
        String encryptedText = fr.asString();
        CaesarCracker caesarCracker = new CaesarCracker();
        String decryptedText = caesarCracker.decrypt(encryptedText);
        String expected = new FileResource("breaking_vigenere_cypher/text_files/titus-small.txt").asString();
        assertEquals(expected, decryptedText);
    }

    @Test
    public void testDecryptPortuguese() {
        FileResource fr = new FileResource("breaking_vigenere_cypher/text_files/oslusiadas_key17.txt");
        String encryptedText = fr.asString();
        CaesarCracker caesarCracker = new CaesarCracker('a');
        String decryptedText = caesarCracker.decrypt(encryptedText);
        FileResource frExpected = new FileResource("breaking_vigenere_cypher/text_files/oslusiadas.txt");
        String expected = frExpected.asString();
        assertEquals(expected, decryptedText);
    }
}