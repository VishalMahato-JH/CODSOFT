package CODSOFT;

import java.util.Random; // Import Random class to generate random numbers
import java.util.Scanner; // Import Scanner class for user input

public class NumberGame {
    // Constants for the game
    private static final int MAX_ATTEMPTS = 5; // Limit the number of attempts to 5 per round
    private static final int MIN_RANGE = 1;    // Minimum value for the random number
    private static final int MAX_RANGE = 100;  // Maximum value for the random number
    private static int totalScore = 0;         // Track the user's total score across rounds

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random(); // Create a Random object for number generation
        boolean continuePlaying = true;       // Flag to control multiple rounds

        // Welcome message
        System.out.println("Welcome to the Number Game!");
        System.out.println("You have to guess a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
        System.out.println("You get " + MAX_ATTEMPTS + " attempts per round. Let's begin!\n");

        // Loop for multiple rounds
        while (continuePlaying) {
            playOneRound(inputScanner, randomGenerator); // Start a new round
            System.out.print("Would you like to play another round? (yes/no): ");
            String playAgain = inputScanner.next().trim().toLowerCase();
            continuePlaying = playAgain.startsWith("y"); // Continue if user types 'yes'
        }

        // Display final score and end game
        System.out.println("\nThanks for playing! Your final score is: " + totalScore);
        inputScanner.close(); // Close the scanner to prevent resource leak
    }

    // Method to handle a single round of the game
    private static void playOneRound(Scanner scanner, Random random) {
        // Generate a random number between MIN_RANGE and MAX_RANGE
        int secretNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        int attemptsLeft = MAX_ATTEMPTS; // Track remaining attempts
        boolean guessedCorrectly = false; // Flag to check if user guessed correctly

        System.out.println("\n--- New Round Started ---");
        System.out.println("I have picked a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");

        // Loop until the user guesses correctly or runs out of attempts
        while (attemptsLeft > 0 && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = getValidGuess(scanner); // Get and validate user input
            attemptsLeft--; // Decrease remaining attempts

            // Compare the guess with the secret number and provide feedback
            if (userGuess == secretNumber) {
                guessedCorrectly = true;
                int roundScore = calculateRoundScore(MAX_ATTEMPTS - attemptsLeft);
                totalScore += roundScore;
                System.out.println("Congratulations! You guessed it! The number was " + secretNumber);
                System.out.println("It took you " + (MAX_ATTEMPTS - attemptsLeft) + " attempts.");
                System.out.println("You earned " + roundScore + " points for this round.");
            } else if (userGuess < secretNumber) {
                System.out.println("Too low! " + attemptsLeft + " attempts left.");
            } else {
                System.out.println("Too high! " + attemptsLeft + " attempts left.");
            }

            // Check if user ran out of attempts
            if (attemptsLeft == 0 && !guessedCorrectly) {
                System.out.println("Sorry, you're out of attempts! The number was " + secretNumber);
            }
        }
    }

    // Method to get and validate user input
    private static int getValidGuess(Scanner scanner) {
        int guess;
        while (true) {
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= MIN_RANGE && guess <= MAX_RANGE) {
                    break; // Valid input, exit loop
                } else {
                    System.out.print("Please enter a number between " + MIN_RANGE + " and " + MAX_RANGE + ": ");
                }
            } else {
                System.out.print("Invalid input! Please enter a number between " + MIN_RANGE + " and " + MAX_RANGE + ": ");
                scanner.next(); // Clear invalid input
            }
        }
        return guess;
    }

    // Method to calculate score based on attempts taken
    private static int calculateRoundScore(int attemptsTaken) {
        // Award more points for fewer attempts (max 5 points for 1 attempt, 1 point for 5 attempts)
        return MAX_ATTEMPTS - attemptsTaken + 1;
    }
}
