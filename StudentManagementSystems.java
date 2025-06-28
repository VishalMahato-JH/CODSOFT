package CODSOFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // To get input from user
import java.io.*; // To read and write files

// Class to hold student details
class Student {
    String name;      // Student's name
    String rollNumber; // Unique roll number
    String grade;     // Student's grade (e.g., A, B, C)
    int age;          // Student's age

    // Set up student with details
    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Show student details
    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade + ", Age: " + age;
    }
}

// Class to manage all students
class StudentManagementSystem {
    List<Student> students; // List to keep all students
    String fileName = "students.txt"; // File to save data

    // Start the system and load old data
    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadDataFromFile(); // Get data from file when starting
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
        saveDataToFile(); // Save to file after adding
        System.out.println("Student added!");
    }

    // Remove a student by roll number
    public boolean removeStudent(String rollNumber) {
        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                students.remove(student);
                saveDataToFile(); // Save after removing
                System.out.println("Student removed!");
                return true;
            }
        }
        System.out.println("Student not found.");
        return false;
    }

    // Find a student by roll number
    public Student findStudent(String rollNumber) {
        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                System.out.println("Found: " + student);
                return student;
            }
        }
        System.out.println("No student with that roll number.");
        return null;
    }

    // Show all students
    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students yet.");
        } else {
            System.out.println("\n=== All Students ===");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Save students to file
    private void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.println(student.rollNumber + "," + student.name + "," + 
                               student.grade + "," + student.age);
            }
            System.out.println("Data saved to file.");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load students from file
    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    students.add(new Student(parts[1], parts[0], parts[2], Integer.parseInt(parts[3])));
                }
            }
            System.out.println("Data loaded from file.");
        } catch (Exception e) {
            System.out.println("No file found, starting new.");
        }
    }

    // Update student details
    public boolean updateStudent(String rollNumber, Student newStudent) {
        Student student = findStudent(rollNumber);
        if (student != null) {
            student.name = newStudent.name;
            student.grade = newStudent.grade;
            student.age = newStudent.age;
            saveDataToFile();
            System.out.println("Student updated!");
            return true;
        }
        return false;
    }
}

// Main class to run the program
public class StudentManagementSystems {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        // Welcome message with current date and time
        System.out.println("Welcome to Student Management System! 📖");
        System.out.println("Started at: 08:43 PM IST, Saturday, June 28, 2025\n");

        // Keep asking for actions until user quits
        int choice;
        do {
            showMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear extra input
                if (choice == 1) {
                    addNewStudent(sms, scanner);
                } else if (choice == 2) {
                    editStudent(sms, scanner);
                } else if (choice == 3) {
                    searchStudent(sms, scanner);
                } else if (choice == 4) {
                    sms.showAllStudents();
                } else if (choice == 5) {
                    System.out.println("Goodbye! 👋");
                } else {
                    System.out.println("Pick a number from 1 to 5.");
                }
            } else {
                System.out.println("Please enter a number.");
                scanner.next(); // Clear wrong input
                choice = 0; // Keep going
            }
        } while (choice != 5);

        scanner.close(); // Close the input tool
    }

    // Show the menu options
    private static void showMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Search Student");
        System.out.println("4. Show All Students");
        System.out.println("5. Quit");
        System.out.print("What do you want to do? (1-5): ");
    }

    // Add a new student with checks
    private static void addNewStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine().trim();
        if (rollNumber.isEmpty()) {
            System.out.println("Roll number can't be empty.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name can't be empty.");
            return;
        }

        System.out.print("Enter grade (e.g., A, B, C): ");
        String grade = scanner.nextLine().trim();
        if (grade.isEmpty()) {
            System.out.println("Grade can't be empty.");
            return;
        }

        System.out.print("Enter age: ");
        if (scanner.hasNextInt()) {
            int age = scanner.nextInt();
            if (age <= 0 || age > 100) {
                System.out.println("Age must be between 1 and 100.");
                scanner.nextLine(); // Clear extra
                return;
            }
            scanner.nextLine(); // Clear extra
            Student student = new Student(name, rollNumber, grade, age);
            sms.addStudent(student);
        } else {
            System.out.println("Age must be a number.");
            scanner.next(); // Clear wrong input
        }
    }

    // Edit an existing student
    private static void editStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter roll number to edit: ");
        String rollNumber = scanner.nextLine().trim();
        if (rollNumber.isEmpty()) {
            System.out.println("Roll number can't be empty.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name can't be empty.");
            return;
        }

        System.out.print("Enter new grade (e.g., A, B, C): ");
        String grade = scanner.nextLine().trim();
        if (grade.isEmpty()) {
            System.out.println("Grade can't be empty.");
            return;
        }

        System.out.print("Enter new age: ");
        if (scanner.hasNextInt()) {
            int age = scanner.nextInt();
            if (age <= 0 || age > 100) {
                System.out.println("Age must be between 1 and 100.");
                scanner.nextLine(); // Clear extra
                return;
            }
            scanner.nextLine(); // Clear extra
            Student student = new Student(name, rollNumber, grade, age);
            sms.updateStudent(rollNumber, student);
        } else {
            System.out.println("Age must be a number.");
            scanner.next(); // Clear wrong input
        }
    }

    // Search for a student
    private static void searchStudent(StudentManagementSystem sms, Scanner scanner) {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine().trim();
        if (rollNumber.isEmpty()) {
            System.out.println("Roll number can't be empty.");
            return;
        }
        sms.findStudent(rollNumber);
    }
}
