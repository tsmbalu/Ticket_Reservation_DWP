package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class TicketPricingStrategyFactory {
    public static TicketPricingStrategy getStrategy(TicketTypeRequest.Type type) {
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
