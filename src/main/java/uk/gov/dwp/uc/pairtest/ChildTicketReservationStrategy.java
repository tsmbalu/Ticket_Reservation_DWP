package uk.gov.dwp.uc.pairtest;

public class ChildTicketReservationStrategy implements TicketReservationStrategy {
    private static final int CHILD_TICKET_PRICE = 15;

    @Override
    public int calculatePrice(int numberOfTickets) {
        return numberOfTickets * CHILD_TICKET_PRICE;
    }

    @Override
    public int calculateTotalSeat(int numberOfTickets) {
        return numberOfTickets;
    }

    @Override
    public boolean isAdultTicket() {
        return false;
    }
}
