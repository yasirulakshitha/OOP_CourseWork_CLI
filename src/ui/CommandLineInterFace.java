package ui;

import configuration.Configurations;
import logging.Logger;

import java.util.Scanner;

public class CommandLineInterFace {

    // Method to get the configuration for the ticketing system, either from saved settings or user input
    public static Configurations getConfiguration() {
        Scanner scanner = new Scanner(System.in);

        Logger.log("System configuration started"); // Logging the start of the configuration process

        String useSavedConfig;

        // Loop until the user enters "yes" or "no"
        while (true) {
            System.out.print("Do you want to use the saved configuration (yes/no)? ");
            useSavedConfig = scanner.next().trim().toLowerCase(); // Convert input to lowercase and trim spaces

            if (useSavedConfig.equals("yes") || useSavedConfig.equals("no")) {
                break; // Exit the loop if input is valid
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        Configurations configurations = null;

        if (useSavedConfig.equals("yes")) {
            // Load the saved configurations from JSON file (only the 4 main configurations)
            Configurations savedConfigurations = Configurations.loadSavedConfiguration("configurations.json");
            if (savedConfigurations != null) {
                Logger.log("Loaded saved configurations."); // Log if configurations were successfully loaded
                configurations = savedConfigurations;
            } else {
                System.out.println("No saved configurations found. Please enter new configurations.");
            }
        }

        if (configurations == null) {
            // If saved configurations are unavailable, prompt the user for new inputs
            int totalTickets = getInputs(scanner, "Enter total tickets: ");
            int maxTicketPoolSize = getMaxTicketPoolSize(scanner, totalTickets);
            int retrievalRate = getInputs(scanner, "Enter ticket retrieval rate: ");
            int releaseRate = getInputs(scanner, "Enter ticket release rate: ");

            // Create a new Configurations object with only the 4 main configurations
            configurations = new Configurations(totalTickets, releaseRate, retrievalRate, maxTicketPoolSize);
        }

        // Prompt user for the number of vendors and customers (not saved in JSON)
        int numberOfCustomers = getInputs(scanner, "Enter number of customers: ");
        int numberOfVendors = getInputs(scanner, "Enter number of vendors: ");

        // Set the number of vendors and customers (entered at runtime)
        configurations.setNumberOfVendors(numberOfVendors);
        configurations.setNumberOfCustomers(numberOfCustomers);

        Logger.log("System configuration finished");

        // Save the 4 configurations (not the number of vendors/customers) to a JSON file
        Configurations.saveConfiguration(configurations, "configurations.json");

        // Now that the configurations are loaded and set, run the simulation
        runSimulation(configurations);

        return configurations; // Return the configured object
    }

    // Method to run the simulation with the loaded or entered configurations
    private static void runSimulation(Configurations configurations) {
        Logger.log("Starting simulation with configurations:");

        // Access the configuration values and start the simulation here
        System.out.println("Total Tickets: " + configurations.getTotalTickets());
        System.out.println("Ticket Release Rate: " + configurations.getReleaseRate());
        System.out.println("Ticket Retrieval Rate: " + configurations.getRetrievalRate());
        System.out.println("Max Ticket Pool Size: " + configurations.getMaxTicketPoolSize());
        System.out.println("Number of Vendors: " + configurations.getNumberOfVendors());
        System.out.println("Number of Customers: " + configurations.getNumberOfCustomers());

        // Trigger the actual simulation logic here
        // For example:
        // TicketingSystem.runSimulation(configurations);
    }

    // Helper method to get and validate numeric inputs from the user
    private static int getInputs(Scanner scanner, String prompt) { // Display the prompt to the user
        int value;

        while (true) {
            System.out.print(prompt);

            try {
                value = Integer.parseInt(scanner.next());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Number must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // New method to get and validate maxTicketPoolSize
    private static int getMaxTicketPoolSize(Scanner scanner, int totalTickets) {
        int maxTicketPoolSize;

        while (true) {
            System.out.print("Enter max ticket pool size: ");
            try {
                maxTicketPoolSize = Integer.parseInt(scanner.next());

                if (maxTicketPoolSize >= totalTickets) {
                    return maxTicketPoolSize; // Exit loop if valid
                } else {
                    System.out.println("Error: Max ticket pool size must be greater than or equal to total tickets. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
