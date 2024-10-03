package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.dto.TicketOrderSummary;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private static final int MAX_TICKETS_ALLOWED = 25;
    private SeatReservationService seatReservationService;
    private TicketPaymentService ticketPaymentService;


    public TicketServiceImpl(){
        this.seatReservationService = new SeatReservationServiceImpl();
        this.ticketPaymentService = new TicketPaymentServiceImpl();
    }

    public TicketServiceImpl(SeatReservationService seatReservationService, TicketPaymentService ticketPaymentService){
        this.seatReservationService = seatReservationService;
        this.ticketPaymentService = ticketPaymentService;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        validateAccount(accountId);

        TicketOrderSummary ticketOrderSummary = createOrderSummary(ticketTypeRequests);

        if(ticketOrderSummary.getTotalNumberOfTickets() > MAX_TICKETS_ALLOWED) {
            throw new InvalidPurchaseException("Cannot purchase more than 25 tickets in single order.");
        }

        if(!ticketOrderSummary.isAdultTicketAvailable()){
            throw new InvalidPurchaseException("At least one adult ticket is required.");
        }

        seatReservationService.reserveSeat(accountId, ticketOrderSummary.getTotalNumberOfTickets());
        ticketPaymentService.makePayment(accountId, ticketOrderSummary.getTotalTicketPrice());
    }

    private void validateAccount(Long accountId) throws InvalidPurchaseException {
        if (accountId == null || accountId <= 0) {
            throw new InvalidPurchaseException("Invalid account ID.");
        }
    }

    private TicketOrderSummary createOrderSummary(TicketTypeRequest... ticketTypeRequests) {
        int totalTicketPrice = 0;
        int totalNumberOfTicket = 0;
        boolean isAdultTicketAvailableInOrder = false;
                for(TicketTypeRequest ticketTypeRequest : ticketTypeRequests) {

            TicketPricingStrategy ticketPricingStrategy = TicketPricingStrategyFactory.getStrategy(ticketTypeRequest.getTicketType());

            totalTicketPrice += ticketPricingStrategy.calculatePrice(ticketTypeRequest.getNoOfTickets());
            totalNumberOfTicket += ticketTypeRequest.getNoOfTickets();

            if (ticketPricingStrategy.isAdultTicket()) {
                isAdultTicketAvailableInOrder = true;
            }
        }

        return new TicketOrderSummary(totalTicketPrice, totalNumberOfTicket, isAdultTicketAvailableInOrder);
    }

}
