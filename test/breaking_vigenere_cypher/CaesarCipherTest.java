package breaking_vigenere_cypher;

import edu.duke.FileResource;
import main.java.breaking_vigenere_cypher.CaesarCipher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarCipherTest {
    @Test
    public void testCaesarCipher() {
        FileResource fr = new FileResource("test/breaking_vigenere_cypher/text_files/titus-small.txt");
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(10);
        String encryptedMessage = cc.encrypt(message);
        String decryptedMessage = cc.decrypt(encryptedMessage);
        assertEquals(decryptedMessage, message);
    }

    @Test
    public void testEncryptLetter() {
        char letter = 'a';
        char expectedLetter = 'c';
        CaesarCipher cc = new CaesarCipher(2);
        char encryptedLetter = cc.encryptLetter(letter);
        assertEquals(expectedLetter, encryptedLetter);
    }

    @Test
    public void testDecryptLetter() {
        char letter = 'c';
        char expectedLetter = 'a';
        CaesarCipher cc = new CaesarCipher(2);
        char decryptedLetter = cc.decryptLetter(letter);
        assertEquals(expectedLetter, decryptedLetter);
    }
}
