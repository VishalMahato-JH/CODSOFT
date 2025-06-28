package CODSOFT;

import java.util.Scanner; // Used to get input from the user

// Class to handle the bank account
class BankAccount {
    private double balance; // Stores the amount of money in the account

    // Set up the account with an initial balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance; // Use the initial balance if valid
        } else {
            this.balance = 0.0; // Default to 0 if negative
            System.out.println("Warning: Invalid initial balance. Set to $0.00.");
        }
    }

    // Check the available balance
    public double getBalance() {
        return balance;
    }

    // Add money to the account (deposit)
    public boolean deposit(double amount) {
        if (amount > 0) { // Ensure amount is positive
            balance += amount;
            return true; // Deposit successful
        }
        return false; // Deposit failed
    }

    // Remove money from the account (withdraw)
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // Check for positive amount and sufficient funds
            balance -= amount;
            return true; // Withdrawal successful
        }
        return false; // Withdrawal failed
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account; // The bank account linked to this ATM

    // Connect the ATM to a bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Display the transaction menu
    public void showTransactionMenu() {
        System.out.println("\n=== ATM Transaction Menu ===");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. End Transaction");
        System.out.print("Please select a transaction (1-4): ");
    }

    // Display the current account balance
    public void checkBalance() {
        System.out.println("Your available balance is: $" + account.getBalance());
    }

    // Process a cash deposit
    public void depositCash(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        if (account.deposit(amount)) {
            System.out.println("Deposit of $" + amount + " processed successfully.");
            System.out.println("New balance: $" + account.getBalance());
        } else {
            System.out.println("Error: Please enter a positive amount.");
        }
    }

    // Process a cash withdrawal
    public void withdrawCash(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal of $" + amount + " processed successfully.");
            System.out.println("New balance: $" + account.getBalance());
        } else {
            System.out.println("Error: Insufficient funds or invalid amount. Please try again.");
        }
    }
}

// Main class to run the ATM application
public class ATMInterface {
    public static void main(String[] args) {
        // Set up input from the user
        Scanner scanner = new Scanner(System.in);

        // Authenticate user with a simple PIN (real ATMs use secure PINs)
        System.out.print("Please enter your 4-digit PIN: ");
        int pin = scanner.nextInt();
        if (pin != 1234) { // Simple PIN check for demo
            System.out.println("Invalid PIN. Transaction terminated.");
            scanner.close();
            return;
        }

        // Ask for initial balance and create account
        System.out.print("Enter initial account balance: $");
        double initialBalance = scanner.nextDouble();
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atmMachine = new ATM(userAccount); // Link ATM to account

        // Welcome message with current date and time
        System.out.println("\nWelcome to Your ATM! 🌐");
        System.out.println("Transaction started at: 08:05 PM IST, Saturday, June 28, 2025");
        System.out.println("Initial balance: $" + userAccount.getBalance() + "\n");

        // Loop for transactions until user chooses to end
        int choice;
        do {
            atmMachine.showTransactionMenu();
            choice = scanner.nextInt();
            if (choice == 1) {
                atmMachine.checkBalance();
            } else if (choice == 2) {
                atmMachine.depositCash(scanner);
            } else if (choice == 3) {
                atmMachine.withdrawCash(scanner);
            } else if (choice == 4) {
                System.out.println("Transaction completed. Thank you for banking with us! 👋");
            } else {
                System.out.println("Invalid selection. Please choose 1, 2, 3, or 4.");
            }
        } while (choice != 4);

        scanner.close(); // Close the scanner to free resources
    }
}
