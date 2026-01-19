import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

/**
 * Represents a word-based guessing view over a sonnet.
 * A word is described as the following: a sequence of alphabetic characters, digits, or apostrophes.
 */

public class SonnetPlayer {
    private String sonnet;
    private String[] sonnetArray;

    public SonnetPlayer(String sonnet) {
        setSonnet(sonnet);
    }

    public String getSonnet() {
        return sonnet;
    }

    public String getSonnetUpToWord(int ithWord) {
        validateIndex(ithWord);

        StringBuilder sb = new StringBuilder(sonnet.length());
        for (int i = 0; i < ithWord; i++) {
            sb.append(sonnetArray[i]).append(" ");
        }
        sb.append(maskWord(sonnetArray[ithWord]));

        return sb.toString();
    }

    public int getSonnetArrayLength() {
        return sonnetArray.length;
    }

    public String getWord(int ithWord) {
        validateIndex(ithWord);
        return extractAnswerWord(sonnetArray[ithWord]);
    }

    private String maskWord(String word) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (isWordChar(c)) {
                sb.append("_");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String extractAnswerWord(String word) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (isWordChar(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private boolean isWordChar(char c) {
        return isAlphabetic(c) || isDigit(c) || c == '\'';
    }

    private void validateIndex(int ithWord) {
        int count = sonnetArray.length;
        if (ithWord < 0 || ithWord >= count) {
            throw new IllegalArgumentException(
                    "Word index (" + ithWord + ") mismatches sonnetWordCount (" + count + ")."
            );
        }
    }

    public void setSonnet(String sonnet) {
        if (sonnet == null) throw new NullPointerException("Sonnet is null.");
        if (sonnet.trim().isEmpty()) throw new IllegalArgumentException("Sonnet is empty.");
        this.sonnet = sonnet;
        setSonnetArray(sonnet);
    }

    private void setSonnetArray(String sonnet) {
        sonnetArray = sonnet.trim().split(" +");
    }
}
