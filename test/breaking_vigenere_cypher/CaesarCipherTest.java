package breaking_vigenere_cypher;

import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}