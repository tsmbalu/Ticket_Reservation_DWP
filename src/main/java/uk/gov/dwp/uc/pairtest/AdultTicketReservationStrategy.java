package uk.gov.dwp.uc.pairtest;

public class AdultTicketReservationStrategy implements TicketReservationStrategy {
    private static final int ADULT_TICKET_PRICE = 25;

    @Override
    public int calculatePrice(int numberOfTickets) {
        return numberOfTickets * ADULT_TICKET_PRICE;
    }

    @Override
    public int calculateTotalSeat(int numberOfTickets) {
        return numberOfTickets;
    }

    @Override
    public boolean isAdultTicket() {
        return true;
    }
}
