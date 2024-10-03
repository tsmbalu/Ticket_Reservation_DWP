package uk.gov.dwp.uc.pairtest;

public class ChildTicketPricingStrategy implements TicketPricingStrategy {
    private static final int CHILD_TICKET_PRICE = 15;

    @Override
    public int calculatePrice(int numberOfTickets) {
        return numberOfTickets * CHILD_TICKET_PRICE;
    }

    @Override
    public boolean isAdultTicket() {
        return false;
    }
}
