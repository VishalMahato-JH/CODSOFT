package CODSOFT;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; // Import Scanner to read user input

public class CurrencyConverter {
    // Static map to store exchange rates (simulated, as real API requires internet and libraries)
    private static Map<String, Double> exchangeRates = new HashMap<>();

    // Static map to store currency symbols for display
    private static Map<String, String> currencySymbols = new HashMap<>();

    // Initialize exchange rates and symbols when the program starts
    static {
        // Approximate exchange rates with USD as base currency
        exchangeRates.put("USD", 1.0);      // 1 USD = 1 USD
        exchangeRates.put("EUR", 0.85);     // 1 USD = 0.85 EUR
        exchangeRates.put("INR", 83.50);    // 1 USD = 83.50 INR
        exchangeRates.put("GBP", 0.75);     // 1 USD = 0.75 GBP
        exchangeRates.put("JPY", 150.00);   // 1 USD = 150.00 JPY

        // Currency symbols for better output
        currencySymbols.put("USD", "$");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("INR", "₹");
        currencySymbols.put("GBP", "£");
        currencySymbols.put("JPY", "¥");
    }

    public static void main(String[] args) {
        // Create Scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);

        // Welcome message with current date and time
        System.out.println("Welcome to the Currency Converter! 🌍");
        System.out.println("Started on: 08:31 PM IST, Saturday, June 28, 2025\n");

        // Step 1: Get base currency from user
        String baseCurrency = selectCurrency(scanner, "base");
        // Step 1: Get target currency from user
        String targetCurrency = selectCurrency(scanner, "target");

        // Step 3: Get amount to convert from user
        double amount = getAmountInput(scanner);

        // Step 4: Perform the currency conversion
        double convertedAmount = performConversion(baseCurrency, targetCurrency, amount);

        // Step 5: Display the conversion result
        showConversionResult(baseCurrency, targetCurrency, amount, convertedAmount);

        // Clean up resources
        scanner.close();
    }

    // Method to let user select a currency (Requirement 1)
    private static String selectCurrency(Scanner scanner, String currencyType) {
        String[] availableCurrencies = {"USD", "EUR", "INR", "GBP", "JPY"};
        while (true) {
            System.out.println("\nAvailable " + currencyType + " currencies: USD, EUR, INR, GBP, JPY");
            System.out.print("Please choose your " + currencyType + " currency: ");
            String choice = scanner.next().toUpperCase();
            for (String currency : availableCurrencies) {
                if (currency.equals(choice)) {
                    return choice; // Return the valid currency
                }
            }
            System.out.println("Oops! That currency isn't available. Please try again.");
        }
    }

    // Method to get and validate the amount to convert (Requirement 3)
    private static double getAmountInput(Scanner scanner) {
        double amount;
        while (true) {
            System.out.print("Enter the amount to convert: $");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount > 0) {
                    return amount; // Return valid amount
                } else {
                    System.out.println("Error: Amount must be positive. Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Method to convert the currency amount (Requirement 4)
    private static double performConversion(String baseCurrency, String targetCurrency, double amount) {
        // If both currencies are the same, no conversion needed
        if (baseCurrency.equals(targetCurrency)) {
            return amount;
        }

        // Convert to USD first (assuming USD as intermediate currency)
        double amountInUSD = amount / exchangeRates.get(baseCurrency);
        // Convert from USD to target currency
        return amountInUSD * exchangeRates.get(targetCurrency);
    }

    // Method to display the conversion result (Requirement 5)
    private static void showConversionResult(String baseCurrency, String targetCurrency, 
                                          double amount, double convertedAmount) {
        String baseSymbol = currencySymbols.getOrDefault(baseCurrency, "$");
        String targetSymbol = currencySymbols.getOrDefault(targetCurrency, "");
        
        System.out.println("\n=== Conversion Result ===");
        System.out.printf("%s%.2f %s = %s%.2f %s%n", 
                          baseSymbol, amount, baseCurrency, 
                          targetSymbol, convertedAmount, targetCurrency);
        System.out.println("Conversion completed. Thank you for using the Currency Converter! 🌟");
    }
}
