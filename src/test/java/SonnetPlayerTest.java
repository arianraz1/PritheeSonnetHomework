import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SonnetPlayerTest {
    private static final String SONNET =
            "Let me not to the marriage of true minds admit impediments, \n" +
            "Where thou";

    // =====================
    // Logic handling tests
    // =====================

    @Test
    void getWord_basicCases() {
        SonnetPlayer sp = new SonnetPlayer(SONNET);

        assertEquals("Let", sp.getWord(0));
        assertEquals("me", sp.getWord(1));
        assertEquals("not", sp.getWord(2));
        assertEquals("impediments", sp.getWord(10));
        assertEquals("Where", sp.getWord(11));
    }

    @Test
    void getSonnetUpToWordIndex_masksCorrectly() {
        SonnetPlayer sp = new SonnetPlayer(SONNET);

        String masked = sp.getSonnetUpToWord(0);
        assertEquals("___", masked);

        masked = sp.getSonnetUpToWord(3);
        assertEquals("Let me not __", masked);

        masked = sp.getSonnetUpToWord(12);
        assertEquals("Let me not to the marriage of true minds admit impediments, \n" + "Where ____", masked);
    }

    @Test
    void allowsApostrophesInsideWords() {
        SonnetPlayer sp = new SonnetPlayer("Love alters not with love's brief hours");

        assertEquals("love's", sp.getWord(4));
    }

    // =====================
    // Exception handling tests
    // =====================

    @Test
    void negativeIndexThrows() {
        SonnetPlayer sp = new SonnetPlayer(SONNET);

        assertThrows(IllegalArgumentException.class,
                () -> sp.getWord(-1));
    }

    @Test
    void indexEqualToLengthThrows() {
        SonnetPlayer sp = new SonnetPlayer(SONNET);

        assertThrows(IllegalArgumentException.class,
                () -> sp.getWord(sp.getSonnetArrayLength()));
    }

    @Test
    void nullSonnetThrows() {
        assertThrows(NullPointerException.class,
                () -> new SonnetPlayer(null));
    }

    @Test
    void emptySonnetThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new SonnetPlayer("   "));
    }
}
