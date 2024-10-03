package uk.gov.dwp.uc.pairtest;

public class InfantTicketPricingStrategy implements TicketPricingStrategy {

    @Override
    public int calculatePrice(int numberOfTickets) {
        return 0;
    }

    @Override
    public boolean isAdultTicket() {
        return false;
    }
}
