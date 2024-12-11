package core;

import logging.Logger;
import utill.Ticket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool implements TicketOperation {

    private final List<Ticket> ticketPool = Collections.synchronizedList(new ArrayList<>()); // Ticket pool list

    private final int maxPoolTickets; // Maximum tickets allowed in the pool
    private int soldTicketCounter = 0;// Counter for sold tickets
    private int counter = 0;// Counter for total tickets added to the pool

    //Constructor to initialize maxPoolTickets
    public TicketPool(int maxPoolTickets) {
        this.maxPoolTickets = maxPoolTickets;
    }

    //Adds a ticket to the pool
    @Override
    public synchronized void addTicket(Ticket ticket) {
        if (ticketPool.size() >= maxPoolTickets) {
            Logger.log("ticket pool is full. waiting for space...");
            try {
                wait();  // Wait if the pool is full
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ticketPool.add(ticket);
        counter++;
        notifyAll();  // Notify all threads waiting on the ticket pool
    }

    // Removes and returns a ticket from the pool
    @Override
    public synchronized Ticket removeTicket() {  // Now returns a Ticket object*
        Ticket ticket = null;
        if (!ticketPool.isEmpty()) {
            ticket = ticketPool.remove(0);  // Remove the first ticket from the pool
  soldTicketCounter++;
            notifyAll();  // Notify all threads that the pool has changed
        } else {
            Logger.log("Ticket pool is empty. Waiting for tickets...");
            try {
                wait();  //Wait if the pool is empty
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return ticket;  //Return the removed ticket (or null if no tickets were available)
    }

    // Getter for the counter
    public int getCounter() {
        return counter;
    }

    // Getter for the sold ticket counter
    public int getSoldTicketCounter() {
        return soldTicketCounter;
    }


}
