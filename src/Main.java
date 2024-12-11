import configuration.Configurations;
import core.TicketPool;
import logging.Logger;
import threads.Customer;
import threads.Vendor;
import ui.CommandLineInterFace;
import utill.Ticket;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Step 1: Load configurations through the command-line interface
        Configurations configurations = CommandLineInterFace.getConfiguration();

        // Step 2: Initialize the ticket pool with the maximum ticket pool size from configurations
        TicketPool ticketPool = new TicketPool(configurations.getMaxTicketPoolSize());

        // Step 3: Create arrays to hold vendor and customer threads
        Thread[] vendorThreads = new Thread[configurations.getNumberOfVendors()];
        Thread[] customerThreads = new Thread[configurations.getNumberOfCustomers()];

        // Step 4: Initialize and start vendor threads
        for (int i = 0; i < configurations.getNumberOfVendors(); i++) {
            vendorThreads[i] = new Thread(new Vendor(ticketPool, configurations.getReleaseRate(), configurations.getTotalTickets()));
            vendorThreads[i].setName("Vendor " + i); // Set a name for the thread for better logging
            vendorThreads[i].start();// Start the vendor thread


            Logger.log("Vendor " + i + " started");

        }
        // Step 5: Initialize and start customer threads
        for (int i = 0; i < configurations.getNumberOfCustomers(); i++) {
            customerThreads[i] = new Thread(new Customer(ticketPool, configurations.getRetrievalRate(), configurations.getTotalTickets()));
            customerThreads[i].setName("Customer " + i);// Set a name for the thread for better logging
            customerThreads[i].start();// Start the customer thread
            Logger.log("Customer " + i + " started");

        }

        // Step 6: Wait for all vendor threads to complete
        for(Thread vendor: vendorThreads){
            vendor.join();// Ensure the main thread waits for the vendor threads to finish

        }

        // Step 7: Wait for all customer threads to complete
        for(Thread customer: customerThreads){
            customer.join();
        }
    }
}