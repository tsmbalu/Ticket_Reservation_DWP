package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TicketPricingStrategyFactoryTest {
    @Test
    public void testGetStrategyForAdult() {
        TicketReservationStrategy strategy = TicketStrategyFactory.getStrategy(TicketTypeRequest.Type.ADULT);

        assertThat(strategy, instanceOf(AdultTicketReservationStrategy.class));
    }

    @Test
    public void testGetStrategyForChild() {
        TicketReservationStrategy strategy = TicketStrategyFactory.getStrategy(TicketTypeRequest.Type.CHILD);

        assertThat(strategy, instanceOf(ChildTicketReservationStrategy.class));
    }

    @Test
    public void testGetStrategyForInfant() {
        TicketReservationStrategy strategy = TicketStrategyFactory.getStrategy(TicketTypeRequest.Type.INFANT);

        assertThat(strategy, instanceOf(InfantTicketReservationStrategy.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetStrategyForNull() {
        TicketStrategyFactory.getStrategy(null);
    }

}


