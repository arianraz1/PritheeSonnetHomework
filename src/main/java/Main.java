import java.util.Scanner;
import java.util.Random;

public class Main {
    static final int MAX_ATTEMPTS = 3;
    static String sonnet =
            "Two households, both alike in dignity, \n" +
            "In fair Verona, where we lay our scene, \n" +
            "From ancient grudge break to new mutiny, \n" +
            "Where civil blood makes civil hands unclean. \n" +
            "From forth the fatal loins of these two foes \n" +
            "A pair of star-cross’d lovers take their life; \n" +
            "Whose misadventured piteous overthrows \n" +
            "Do with their death bury their parents’ strife. \n" +
            "The fearful passage of their death-mark’d love, \n" +
            "And the continuance of their parents’ rage, \n" +
            "Which, but their children’s end, nought could remove, \n" +
            "Is now the two hours’ traffic of our stage; \n" +
            "The which if you with patient ears attend, \n" +
            "What here shall miss, our toil shall strive to mend. \n";

    public static void main(String[] args) {
        int numCorrect = 0;
        int numIncorrect = 0;

        SonnetPlayer player = new SonnetPlayer(sonnet);
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        int lastIndex = -1;
        int length = player.getSonnetArrayLength();

        while (numCorrect < MAX_ATTEMPTS && numIncorrect < MAX_ATTEMPTS) {
            int wordIndex;

            if (length == 1) {
                wordIndex = 0;
            } else {
                // Ensure the same word is not chosen twice in a row
                do {
                    wordIndex = rand.nextInt(length);
                } while (wordIndex == lastIndex);
            }

            lastIndex = wordIndex;

            if (playRound(player, scanner, wordIndex)) {
                numCorrect++;
            } else {
                numIncorrect++;
            }
        }

        if (numCorrect >= MAX_ATTEMPTS) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
        System.out.println("Score: " + numCorrect + " correct, " + numIncorrect + " incorrect");

        scanner.close();
    }

    private static boolean playRound(
            SonnetPlayer player,
            Scanner scanner,
            int wordIndex
    ) {
        System.out.println(player.getSonnetUpToWord(wordIndex));
        String correctWord = player.getWord(wordIndex);

        System.out.print("Enter the missing word: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase(correctWord)) {
            System.out.println("You are correct!");
            return true;
        } else {
            System.out.println("Nice try. You are incorrect!");
            System.out.println("The missing blank is: " + correctWord);
            return false;
        }
    }
}

