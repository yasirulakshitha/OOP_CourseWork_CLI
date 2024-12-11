package threads;

import core.AbstractTicketHandler;
import core.TicketPool;
import logging.Logger;
import utill.Ticket; // Importing the Ticket class


public class Vendor extends AbstractTicketHandler implements Runnable {
    private final int releaseRate; // The rate at which the vendor releases tickets (in milliseconds).
    private final int totalTickets;// The total number of tickets the vendor is responsible for adding to the pool.


    public Vendor(TicketPool ticketPool, int releaseRate, int totalTickets) {
        super(ticketPool);              // Calling the parent constructor to initialize the ticket pool.
        this.releaseRate = releaseRate;   // Setting the rate at which the vendor will release tickets.
        this.totalTickets = totalTickets; // Setting the total number of tickets the vendor will add.
    }

    // This method is designed to handle tickets but simply calls the `run` method in this implementation.
    @Override
    protected void handlerTicket() {
        run(); // Directly invoking the run method to handle ticket-related operations.
    }


    // The run method simulates the vendor's process of releasing tickets to the ticket pool.
    @Override
    public void run() {
        // Continue adding tickets until the desired total number of tickets is reached.
        while (ticketPool.getCounter() < totalTickets) {

            // Create a new ticket with a unique ID based on the current system time and the thread name.
            Ticket ticket = new Ticket(("ID" + System.nanoTime()),Thread.currentThread().getName());
            // Add the newly created ticket to the ticket pool.
            ticketPool.addTicket(ticket);

            // Log the details of the ticket addition, including its ID and the current count of tickets.
            Logger.log("Vendor (" + Thread.currentThread().getName() + ") added Ticket " + ticketPool.getCounter()
                    + " to the pool. Ticket ID: " + ticket.getTicketId());

            // Simulate a delay between ticket releases, based on the vendor's release rate.
            try {
                Thread.sleep(releaseRate);

            } catch (InterruptedException e) {
                // If the thread is interrupted during sleep, throw a runtime exception.
                throw new RuntimeException(e);
            }
        }
// Once the vendor has added all the tickets, log a completion message and terminate the thread.
        Logger.log("Vendor (" + Thread.currentThread().getName() + ") has finished adding tickets. Thread terminated.");
    }

}
