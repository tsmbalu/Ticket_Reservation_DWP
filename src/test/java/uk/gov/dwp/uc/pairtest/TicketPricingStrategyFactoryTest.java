package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TicketPricingStrategyFactoryTest {
    @Test
    public void testGetStrategyForAdult() {
        TicketPricingStrategy strategy = TicketPricingStrategyFactory.getStrategy(TicketTypeRequest.Type.ADULT);

        assertThat(strategy, instanceOf(AdultTicketPricingStrategy.class));
    }

    @Test
    public void testGetStrategyForChild() {
        TicketPricingStrategy strategy = TicketPricingStrategyFactory.getStrategy(TicketTypeRequest.Type.CHILD);

        assertThat(strategy, instanceOf(ChildTicketPricingStrategy.class));
    }

    @Test
    public void testGetStrategyForInfant() {
        TicketPricingStrategy strategy = TicketPricingStrategyFactory.getStrategy(TicketTypeRequest.Type.INFANT);

        assertThat(strategy, instanceOf(InfantTicketPricingStrategy.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetStrategyForNull() {
        TicketPricingStrategyFactory.getStrategy(null);
    }

}


