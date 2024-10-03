package uk.gov.dwp.uc.pairtest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceTest {

    private TicketService ticketService;
    private SeatReservationService seatReservationService;
    private TicketPaymentService ticketPaymentService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        seatReservationService = Mockito.mock(SeatReservationService.class);
        ticketPaymentService = Mockito.mock(TicketPaymentService.class);
        ticketService = new TicketServiceImpl(seatReservationService, ticketPaymentService);
    }

    @Test
    public void testPurchaseTickets(){
        Long accountId = 1L;
        TicketTypeRequest ticketTypeRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4);
        TicketTypeRequest ticketTypeRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);
        TicketTypeRequest ticketTypeRequest3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);

        ticketService.purchaseTickets(accountId, ticketTypeRequest1, ticketTypeRequest2, ticketTypeRequest3);

        verify(seatReservationService, times(1)).reserveSeat(accountId, 5);
        verify(ticketPaymentService, times(1)).makePayment(accountId, 115);
    }

    @Test
    public void testPurchaseTicketsWithNullValueShouldThrowException(){
        Long accountId = 1L;
        TicketTypeRequest ticketTypeRequest = null;

        exceptionRule.expect(InvalidPurchaseException.class);
        exceptionRule.expectMessage("Invalid ticket request.");
        ticketService.purchaseTickets(accountId, ticketTypeRequest);

        verify(seatReservationService, never()).reserveSeat(anyLong(), anyInt());
        verify(ticketPaymentService, never()).makePayment(anyLong(), anyInt());
    }

    @Test
    public void testPurchaseTicketsWithInvalidAccountIdShouldThrowException() {
        Long invalidAccountId = 0L;
        TicketTypeRequest adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);

        exceptionRule.expect(InvalidPurchaseException.class);
        exceptionRule.expectMessage("Invalid account ID.");
        ticketService.purchaseTickets(invalidAccountId, adultTicketRequest);

        verify(seatReservationService, never()).reserveSeat(anyLong(), anyInt());
        verify(ticketPaymentService, never()).makePayment(anyLong(), anyInt());
    }

    @Test
    public void testPurchaseTicketsWithoutAdultTicketShouldThrowException() {

        Long accountId = 1L;
        TicketTypeRequest childTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        TicketTypeRequest infantTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        exceptionRule.expect(InvalidPurchaseException.class);
        exceptionRule.expectMessage("At least one adult ticket is required.");
        ticketService.purchaseTickets(accountId, childTicketRequest, infantTicketRequest);

        verify(seatReservationService, never()).reserveSeat(anyLong(), anyInt());
        verify(ticketPaymentService, never()).makePayment(anyLong(), anyInt());
    }

    @Test
    public void testPurchaseTicketsWithMoreThan25TicketsShouldThrowException() {
        Long accountId = 1L;
        TicketTypeRequest adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 21);
        TicketTypeRequest childTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);

        exceptionRule.expect(InvalidPurchaseException.class);
        exceptionRule.expectMessage("Cannot purchase more than 25 tickets in single order.");
        ticketService.purchaseTickets(accountId, adultTicketRequest, childTicketRequest);

        verify(seatReservationService, never()).reserveSeat(anyLong(), anyInt());
        verify(ticketPaymentService, never()).makePayment(anyLong(), anyInt());
    }

    @Test
    public void testPurchaseTicketsWithInvalidTicketTypeShouldThrowException() {
        Long accountId = 1L;
        TicketTypeRequest adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        TicketTypeRequest childTicketRequest = new TicketTypeRequest(null, 2);

        exceptionRule.expect(InvalidPurchaseException.class);
        exceptionRule.expectMessage("Unknown ticket type");
        ticketService.purchaseTickets(accountId, adultTicketRequest, childTicketRequest);

        verify(seatReservationService, never()).reserveSeat(anyLong(), anyInt());
        verify(ticketPaymentService, never()).makePayment(anyLong(), anyInt());
    }
}

