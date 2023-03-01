import java.util.Arrays;
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

        char[] wordArray = word.toCharArray();
        Character[] wordObjectArray = ArrayUtils.toObject(wordArray);
        Character[] hiddenWord = ArrayUtils.clone(wordObjectArray);
        hideWord(hiddenWord);

        while (missCount < 7) {
            System.out.println(gallows[missCount]);
            if (missCount == 6) {
                System.out.println("RIP!\n");
                System.out.println("The word was: " + word);
                break;
            }
            if (Arrays.equals(hiddenWord, wordObjectArray)) {
                System.out.println("GOOD WORK!\n");
                System.out.println("You made " + missCount + " misses!");
                break;
            }

            System.out.print("Word: ");
            printHiddenWord(hiddenWord);
            System.out.println("\n\nMisses: " + misses + "\n");
            System.out.print("Guess: ");
            guess = scan.next().charAt(0);
            System.out.println();

            if (checkGuess(wordObjectArray, guess)) {
                updatePlaceholders(wordObjectArray, hiddenWord, guess);
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

    public static void hideWord(Character[] word) {
        Arrays.fill(word, '_');
    }

    public static void printHiddenWord(Character[] word) {
        for (int i = 0; i < word.length; i++) {
            System.out.print(word[i] + " ");
        }
    }

    public static boolean checkGuess(Character[] word, char guess) {
        boolean check = false;
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals(guess)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static void updatePlaceholders(Character[] word, Character[] hiddenWord, char guess) {
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals(guess)) {
                hiddenWord[i] = guess;
            }
        }
    }
}





