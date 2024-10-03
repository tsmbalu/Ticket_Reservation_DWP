package uk.gov.dwp.uc.pairtest.dto;

public class TicketOrderSummary {

    private int totalTicketPrice;
    private int totalNumberOfTickets;
    private boolean adultTicketAvailable;

    public TicketOrderSummary(){}

    public TicketOrderSummary(int totalTicketPrice, int totalNumberOfTickets, boolean adultTicketAvailable) {
        this.totalTicketPrice = totalTicketPrice;
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.adultTicketAvailable = adultTicketAvailable;
    }

    public void setTotalTicketPrice(int totalTicketPrice) {
        this.totalTicketPrice = totalTicketPrice;
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    public void setAdultTicketAvailable(boolean adultTicketAvailable) {
        this.adultTicketAvailable = adultTicketAvailable;
    }

    public int getTotalTicketPrice() {
        return totalTicketPrice;
    }

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public boolean isAdultTicketAvailable() {
        return adultTicketAvailable;
    }

}
