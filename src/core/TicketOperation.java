package core;

import utill.Ticket;

//Operations for managing tickets in a ticketing system are defined by an interface.
 //There are ways to add and remove tickets using this interface.


public interface TicketOperation {


     // Adds a ticket to the system.

     //  ticket the ticket to be added


    void addTicket(Ticket ticket) ;

      //Removes and returns a ticket from the system.
     //return the removed ticket


    Ticket removeTicket() ;

}
