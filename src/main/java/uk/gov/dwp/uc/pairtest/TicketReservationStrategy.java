package uk.gov.dwp.uc.pairtest;

public interface TicketReservationStrategy {
    int calculatePrice(int numberOfTickets);
    int calculateTotalSeat(int numberOfTickets);
    boolean isAdultTicket();
}
