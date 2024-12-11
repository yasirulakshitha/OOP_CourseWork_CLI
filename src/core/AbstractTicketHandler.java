package core;


  //Abstract class for handling ticket operations.
public abstract class AbstractTicketHandler {

    protected TicketPool ticketPool;

     //Constructor to set the ticket pool.
      //ticketPool the ticket pool to use

    public AbstractTicketHandler(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

      //Abstract method to handle tickets.
      //Must be implemented by subclasses.


    protected abstract void handlerTicket();
}