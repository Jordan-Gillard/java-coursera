package breaking_vigenere_cypher;

import edu.duke.FileResource;
import main.java.breaking_vigenere_cypher.VigenereCipher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VigenereCipherTest {
    @Test
    public void testEncryptAndDecryptMessageWithKeyRome() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("resources/text_files/titus-small.txt");
        String message = fr.asString();
        String encryptedMessage = vc.encrypt(message);
        String expectedBeginning = "Tcmp-pxety mj nikhqv htee mrfhtii tyv";
        assertTrue(encryptedMessage.startsWith(expectedBeginning));
        String decryptedMessage = vc.decrypt(encryptedMessage);
        assertEquals(message, decryptedMessage);
    }
}
