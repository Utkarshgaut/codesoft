import java.util.*;

class Number {
    private int randomNumber;
    private int maxAttempts = 5;
    private int attempts = 0;

    public void generateRandomNumber() {
        Random rand = new Random();
        final int UPPER_BOUND = 101;
        randomNumber = rand.nextInt(UPPER_BOUND);
    }

    public boolean playGame(Scanner sc) {
        while (attempts < maxAttempts) {
            System.out.println("Guess a number between 0 and 100: ");
            if (sc.hasNextInt()) {
                int userGuess = sc.nextInt();
                attempts++;
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! Your guess is correct.");
                    sc.nextLine(); // Consume the newline character
                    return true; // User won
                } else if (userGuess < randomNumber) {
                    System.out.println("The guess is too low. You have " + (maxAttempts - attempts) + " attempts left.");
                } else {
                    System.out.println("The guess is too high. You have " + (maxAttempts - attempts) + " attempts left.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Consume the invalid input
            }
        }
        System.out.println("Game Over. Your score: " + (attempts == maxAttempts ? "You ran out of attempts" : attempts + " attempts"));
        return false; // User lost
    }

    public int getRandomNumber() {
        return randomNumber;
    }
}

public class main {
    public static void main(String[] args) {
        System.out.println("Number Game");
        System.out.println("Instructions:");
        System.out.println("1. You have 5 attempts to guess the number.");
        System.out.println("2. Your score will be displayed at the end.");

        Scanner input = new Scanner(System.in);

        while (true) {
            Number game = new Number();
            game.generateRandomNumber();
            boolean userWon = game.playGame(input);

            System.out.println("The correct number was: " + game.getRandomNumber());

            System.out.println("Do you want to play again? (Y/N): ");
            String userChoice = input.nextLine().trim().toUpperCase();

            if (!userChoice.equals("Y")) {
                System.out.println("Goodbye!");
                break;
            } else if (!userWon) {
                // If the user wants to play again and they didn't win the previous game,
                // generate a new random number.
                System.out.println("Generating a new random number for the next game...");
            }
        }

        input.close(); // Close the scanner when done
    }
}
