package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketPricingStrategyFactory {
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
