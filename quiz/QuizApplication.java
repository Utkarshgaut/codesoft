import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctOptionIndex;

    public Question(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public boolean isCorrect(int userChoice) {
        return userChoice == correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApplication {
    private static ArrayList<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static boolean timeout = false;

    public static void main(String[] args) {
        initializeQuestions();
        runQuiz();
        displayResult();
    }

        private static void initializeQuestions() {
        questions.add(new Question("What is the capital of France?",
                new String[]{"London", "Berlin", "Paris", "Madrid"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1));
        questions.add(new Question("What is the largest mammal?",
                new String[]{"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}, 1));
        questions.add(new Question("Which gas do plants absorb from the atmosphere?",
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"}, 1));
        questions.add(new Question("What is the chemical symbol for gold?",
                new String[]{"Go", "Au", "Ag", "Fe"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1));
        questions.add(new Question("How many continents are there?",
                new String[]{"4", "6", "7", "5"}, 2));
        questions.add(new Question("What is the largest planet in our solar system?",
                new String[]{"Earth", "Saturn", "Mars", "Jupiter"}, 3));
        questions.add(new Question("Who wrote the play 'Romeo and Juliet'?",
                new String[]{"William Shakespeare", "Jane Austen", "Charles Dickens", "Leo Tolstoy"}, 0));
        questions.add(new Question("What is the chemical symbol for water?",
                new String[]{"H2O", "CO2", "O2", "H2SO4"}, 0));
        questions.add(new Question("Which gas is most abundant in Earth's atmosphere?",
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Argon"}, 2));
        questions.add(new Question("What is the largest planet in our solar system?",
                new String[]{"Earth", "Saturn", "Mars", "Jupiter"}, 3));
        questions.add(new Question("Which gas do humans exhale when they breathe?",
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Argon"}, 1));
        questions.add(new Question("What is the smallest prime number?",
                new String[]{"1", "2", "3", "4"}, 1));
        questions.add(new Question("How many sides does a triangle have?",
                new String[]{"3", "4", "5", "6"}, 0));
        questions.add(new Question("Which gas is known as the 'silent killer'?",
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Argon"}, 1));
        questions.add(new Question("What is the chemical symbol for oxygen?",
                new String[]{"O2", "CO2", "N2", "H2O"}, 0));
        questions.add(new Question("Which planet is known as the 'Morning Star'?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 0));
        questions.add(new Question("What is the chemical symbol for iron?",
                new String[]{"Go", "Au", "Ag", "Fe"}, 3));
    }

    private static void runQuiz() {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);

            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());
            String[] options = currentQuestion.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            int userChoice = -1;
            timeout = false;

            // Set up a timer task to interrupt after 15 seconds
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! You didn't answer this question.");
                    timeout = true;
                }
            };
            timer.schedule(task, 15000); // 15 seconds

            try {
                System.out.print("Enter your choice (1-" + (options.length + 1) + " or 5 to quit): ");
                userChoice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }

            // Cancel the timer task if the user answered before the timeout
            task.cancel();

            if (timeout) {
                System.out.println("Skipping this question due to timeout.");
            } else if (userChoice >= 1 && userChoice <= options.length) {
                if (currentQuestion.isCorrect(userChoice - 1)) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was option " + (currentQuestion.getCorrectOptionIndex() + 1));
                }
            } else if (userChoice == 5) {
                System.out.println("You chose to quit the quiz.");
                break; // Exit the loop and end the quiz
            } else {
                System.out.println("Invalid choice. Skipping this question.");
            }

            System.out.println();
        }

        // Cancel the timer and close the scanner when done
        timer.cancel();
        scanner.close();
    }

    private static void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}






