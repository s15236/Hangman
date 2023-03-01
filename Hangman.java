import java.util.Random;
import java.util.Scanner;
import org.apache.commons.lang3.ArrayUtils;


public class Hangman {
    static Scanner scan = new Scanner(System.in);

    public static String[] words = {
            "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};

    public static String[] gallows = {"""
        +---+
        |   |
            |
            |
            |
            |
        =========
        """,

            """
        +---+
        |   |
        O   |
            |
            |
            |
        =========
        """,

            """
        +---+
        |   |
        O   |
        |   |
            |
            |
        =========
        """,

            """
         +---+
         |   |
         O   |
        /|   |
             |
             |
         =========
        """,

            """
         +---+
         |   |
         O   |
        /|\\  |
             |
             |
         =========
        """,

            """
         +---+
         |   |
         O   |
        /|\\  |
        /    |
             |
         =========
        """,

            """
         +---+
         |   |
         O   |
        /|\\  |
        / \\  |
             |
         =========
        """};

    public static void main(String[] args) {
        String word = randomWord(words);
        int missCount = 0;
        char guess;
        StringBuilder misses = new StringBuilder();
        while (missCount < 7) {
            System.out.println(gallows[missCount]);
            char[] charArray = word.toCharArray();
            Character[] charObjectArray = ArrayUtils.toObject(charArray);

            System.out.println("Word: " + word);
            System.out.println("Word: " + hideWord(word) + "\n");
            System.out.println("Misses: " + misses + "\n");
            System.out.print("Guess: ");
            guess = scan.next().charAt(0);
            System.out.println();
            if (checkGuess(charObjectArray, guess)) {

            } else {
                misses.append(guess);
                missCount++;
            }
        }
    }

    public static String randomWord(String[] words) {
        int index = new Random().nextInt(words.length);
        return words[index];
    }

    public static String hideWord(String word) {
        int length = word.length();
        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("_ ");
        }
        return hiddenWord.toString();
    }
    public static boolean checkGuess(Character[] word, char guess) {
        boolean check = false;
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals(guess)) check = true;
            else check = false;
        }
        return check;
    }

    /*
    checkGuess(): returns true if the user guessed a letter from the word correctly.
    updatePlaceholders(): updates the placeholders when the user makes a correct guess.
    printPlaceholders(): prints the placeholders.
    printMissedGuesses(): prints guesses that the user missed.
    */

}





