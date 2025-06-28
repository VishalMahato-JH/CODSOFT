package CODSOFT;

import java.util.Scanner; // Import Scanner class for user input

public class GradeCalculator {
    // Constants for the calculator
    private static final int MAX_MARKS_PER_SUBJECT = 100; // Maximum marks per subject
    private static final int MIN_MARKS = 0;              // Minimum valid marks
    private static final int NUM_SUBJECTS = 5;           // Number of subjects set to 5

    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner inputScanner = new Scanner(System.in);
        int[] marks = new int[NUM_SUBJECTS]; // Array to hold marks for each subject

        // Welcome message and instructions
        System.out.println("Welcome to the Student Grade Calculator! 🌟");
        System.out.println("Please enter marks obtained (out of " + MAX_MARKS_PER_SUBJECT + ") for " + NUM_SUBJECTS + " subjects.\n");

        // Collect marks for each subject
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            marks[i] = getValidMarks(inputScanner, i + 1); // Validate and store marks
        }

        // Process and display the results
        displayResults(marks);
        inputScanner.close(); // Close scanner to prevent resource leak
    }

    // Method to get and validate marks input
    private static int getValidMarks(Scanner scanner, int subjectNumber) {
        int mark;
        while (true) {
            System.out.print("Enter marks for Subject " + subjectNumber + ": ");
            if (scanner.hasNextInt()) {
                mark = scanner.nextInt();
                if (mark >= MIN_MARKS && mark <= MAX_MARKS_PER_SUBJECT) {
                    return mark; // Return valid mark and exit loop
                } else {
                    System.out.println("Error: Marks must be between " + MIN_MARKS + " and " + MAX_MARKS_PER_SUBJECT + ". Try again.");
                }
            } else {
                System.out.println("Error: Invalid input! Please enter a number between " + MIN_MARKS + " and " + MAX_MARKS_PER_SUBJECT + ".");
                scanner.next(); // Discard invalid input
            }
        }
    }

    // Method to calculate and display results
    private static void displayResults(int[] marks) {
        int totalMarks = 0;
        // Sum up all marks to get total
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / NUM_SUBJECTS;

        // Determine grade based on average percentage
        String grade = assignGrade(averagePercentage);

        // Display calculated results
        System.out.println("\n--- Grade Calculation Results ---");
        System.out.println("Total Marks: " + totalMarks + " out of " + (MAX_MARKS_PER_SUBJECT * NUM_SUBJECTS));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }

    // Method to assign grade based on percentage
    private static String assignGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}