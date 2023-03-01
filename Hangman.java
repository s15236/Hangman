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
        /*
        * Creating object array from a word.
        */
        char[] wordArray = word.toCharArray();
        Character[] wordObjectArray = ArrayUtils.toObject(wordArray);
        Character[] hiddenWord = ArrayUtils.clone(wordObjectArray);
        hideWord(hiddenWord);
        /*
        * Looping until user ends a game
        */
        while (missCount < 7) {
            System.out.println(gallows[missCount]);
            //Loose case
            if (missCount == 6) {
                System.out.println("RIP!\n");
                System.out.println("The word was: " + word);
                break;
            }
            //Win case
            if (Arrays.equals(hiddenWord, wordObjectArray)) {
                System.out.println("GOOD WORK!\n");
                System.out.println("You made " + missCount + " misses!");
                break;
            }
            /*
            * Printing main game
            */
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
    /**
     * Function name – randomWord()
     *
     * @param words (String[])
     * @return words[i] (String)
     *
     * Inside the function
     *   1. Creates random number through a word.
     *   2. Returns random word from an array.
     */
    public static String randomWord(String[] words) {
        int index = new Random().nextInt(words.length);
        return words[index];
    }
    /**
     * Function name – hideWord()
     *
     * @param word (Character[])
     *
     * Inside the function
     *   1. Converts all chars inside an array to '_'.
     */
    public static void hideWord(Character[] word) {
        Arrays.fill(word, '_');
    }
    /**
     * Function name – printHiddenWord()
     *
     * @param word (Character[])
     *
     * Inside the function
     *   1. Prints 'hidden' word with '_' characters.
     */
    public static void printHiddenWord(Character[] word) {
        for (int i = 0; i < word.length; i++) {
            System.out.print(word[i] + " ");
        }
    }
    /**
     * Function name – checkGuess()
     *
     * @param word (Character[])
     * @param guess (char)
     * @return check (boolean)
     *
     * Inside the function
     *   1. Iterate through word array checking whether guess equals any of characters inside.
     *   2. Returns true if the guess was right.
     */
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
    /**
     * Function name – updatePlaceholders()
     *
     * @param word (Character[])
     * @param hiddenWord (Character[])
     * @param guess (char)
     *
     * Inside the function
     *   1. Iterates through a 'hidden word' to convert '_' into a guess character.
     */
    public static void updatePlaceholders(Character[] word, Character[] hiddenWord, char guess) {
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals(guess)) {
                hiddenWord[i] = guess;
            }
        }
    }
}





