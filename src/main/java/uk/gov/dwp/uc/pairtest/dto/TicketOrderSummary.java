package uk.gov.dwp.uc.pairtest.dto;

public class TicketOrderSummary {

    private int totalTicketPrice;
    private int totalNumberOfTickets;
    private int totalSeat;
    private boolean adultTicketAvailable;

    public TicketOrderSummary(){}

    public TicketOrderSummary(int totalTicketPrice, int totalNumberOfTickets, int totalSeat, boolean adultTicketAvailable) {
        this.totalTicketPrice = totalTicketPrice;
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.totalSeat = totalSeat;
        this.adultTicketAvailable = adultTicketAvailable;
    }

    public int getTotalTicketPrice() {
        return totalTicketPrice;
    }

    public void setTotalTicketPrice(int totalTicketPrice) {
        this.totalTicketPrice = totalTicketPrice;
    }

    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public boolean isAdultTicketAvailable() {
        return adultTicketAvailable;
    }

    public void setAdultTicketAvailable(boolean adultTicketAvailable) {
        this.adultTicketAvailable = adultTicketAvailable;
    }
}
