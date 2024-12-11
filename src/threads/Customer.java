package threads;

import core.AbstractTicketHandler;
import core.TicketPool;
import logging.Logger;
import utill.Ticket; // Import the Ticket class.

public class Customer extends AbstractTicketHandler implements Runnable {

    private int retrievalRate; // The rate at which the customer retrieves tickets (in milliseconds).
    private int totalTickets; // The total number of tickets the customer aims to purchase.

    // Constructor to initialize the customer with ticket pool, retrieval rate, and total tickets to be purchased.
    public Customer(TicketPool ticketPool, int retrievalRate, int totalTickets) {
        super(ticketPool);// Calling the parent constructor to initialize the ticket pool.
        this.ticketPool = ticketPool;       // Assigning the ticket pool to the customer.
        this.retrievalRate = retrievalRate;  // Setting the rate at which the customer will retrieve tickets.
        this.totalTickets = totalTickets;   // Setting the total number of tickets the customer wants to buy.
    }

    @Override
    protected void handlerTicket() {
        // Not implemented in this context.
    }


    // The run method that simulates the customer's process of purchasing tickets.
    @Override
    public void run() {
        while (ticketPool.getSoldTicketCounter() < totalTickets) {

            // Assuming removeTicket() returns a Ticket object.
            Ticket ticket = ticketPool.removeTicket();


            // If a ticket was successfully retrieved, log the ticket purchase details.
            if (ticket != null) {
                Logger.log("Customer (" + Thread.currentThread().getName() + ") removed Ticket "
                        + ticketPool.getSoldTicketCounter() + " from pool. Ticket ID: "
                        + ticket.getTicketId() + "Total tickets sold: "
                        + ticketPool.getSoldTicketCounter());
            } else {
                // If no ticket is available, log that no tickets were found.
                Logger.log("Customer (" + Thread.currentThread().getName() + ") found no tickets available.");
            }
            // Simulate a delay between ticket retrievals, based on the customer's retrieval rate.
            try {
                Thread.sleep(retrievalRate);
            } catch (InterruptedException e) {
                // If the thread is interrupted, throw a runtime exception.
                throw new RuntimeException(e);
            }
        }
        // Once the customer has purchased all the tickets, log the completion message.
        Logger.log("Customer (" + Thread.currentThread().getName() + ") has finished purchasing all tickets. Thread terminated.");
    }
}
