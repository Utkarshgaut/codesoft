import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Take student name and class with exception handling
        String studentName = "";
        int studentClass ;

        try {
            System.out.println("Student Grade System");
            System.out.print("Enter student name: ");
            studentName = scanner.nextLine();

            System.out.print("Enter student class: ");
            studentClass = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: An exception occurred while entering student details.");
            return;
        }

        // Input: Take marks obtained (out of 100) in each subject.
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        int totalMarks = 0;

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();

            // Validate marks to be within the valid range (0-100).
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid input. Marks should be between 0 and 100.");
                return;
            }

            totalMarks += marks;
        }

        // Calculate Total Marks
        System.out.println("\nTotal Marks: " + totalMarks);

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;
        System.out.println("Average Percentage: " + averagePercentage + "%");

        // Grade Calculation: Assign grades based on the average percentage achieved.
        String grade = calculateGrade(averagePercentage);

        // Display Results including student name and class
        System.out.println("\nStudent Name: " + studentName);
        System.out.println("Student Class: " + studentClass);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    // Function to calculate the grade based on the average percentage.
    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
