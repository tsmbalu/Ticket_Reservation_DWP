package uk.gov.dwp.uc.pairtest;

public class InfantTicketReservationStrategy implements TicketReservationStrategy {

    @Override
    public int calculatePrice(int numberOfTickets) {
        return 0;
    }

    @Override
    public int calculateTotalSeat(int numberOfTickets) {
        return 0;
    }

    @Override
    public boolean isAdultTicket() {
        return false;
    }
}
