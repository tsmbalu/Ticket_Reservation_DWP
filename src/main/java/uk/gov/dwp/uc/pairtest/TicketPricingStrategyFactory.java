package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

/**
 * This class is the factory class for Ticket pricing strategy.
 * Based on the ticket type corresponding pricing strategy will be applied
 */
public class TicketPricingStrategyFactory {

    /**
     * Factory method for pricing strategy
     *
     * @param type Ticket Type
     * @return
     */
    public static TicketPricingStrategy getStrategy(TicketTypeRequest.Type type) {
        if (type == null){
            throw new IllegalArgumentException("Unknown ticket type");
        }
        switch (type) {
            case ADULT:
                return new AdultTicketPricingStrategy();
            case CHILD:
                return new ChildTicketPricingStrategy();
            case INFANT:
                return new InfantTicketPricingStrategy();
            default:
                throw new IllegalArgumentException("Unknown ticket type");
        }
    }
}
