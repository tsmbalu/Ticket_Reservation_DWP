package uk.gov.dwp.uc.pairtest;

public interface TicketPricingStrategy {
    int calculatePrice(int numberOfTickets);
    boolean isAdultTicket();
}
