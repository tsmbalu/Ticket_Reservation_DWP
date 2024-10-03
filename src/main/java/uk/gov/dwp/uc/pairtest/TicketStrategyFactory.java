package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 * This class is the factory class for Ticket reservation strategy.
 * Based on the ticket type corresponding reservation strategy will be applied
 */
public class TicketStrategyFactory {

    /**
     * Factory method for initializing strategy based ticket type
     *
     * @param type Ticket Type
     * @return
     */
    public static TicketReservationStrategy getStrategy(TicketTypeRequest.Type type) {
        if (type == null){
            throw new IllegalArgumentException("Unknown ticket type");
        }
        switch (type) {
            case ADULT:
                return new AdultTicketReservationStrategy();
            case CHILD:
                return new ChildTicketReservationStrategy();
            case INFANT:
                return new InfantTicketReservationStrategy();
            default:
                throw new IllegalArgumentException("Unknown ticket type");
        }
    }
}
