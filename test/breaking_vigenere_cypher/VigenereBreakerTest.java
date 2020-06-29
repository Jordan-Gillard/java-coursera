package breaking_vigenere_cypher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VigenereBreakerTest {
    @Test
    void sliceString() {
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
}