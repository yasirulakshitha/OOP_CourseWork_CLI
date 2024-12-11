package utill;

public class Ticket {

    public Ticket(String ticketId, String vendorId) {
        this.ticketId = ticketId;
        this.vendorId = vendorId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    private String ticketId;
    private String vendorId;

    public String getTicketId() {
        return ticketId;
    }

    public String getVendorId() {
        return vendorId;
    }




}
