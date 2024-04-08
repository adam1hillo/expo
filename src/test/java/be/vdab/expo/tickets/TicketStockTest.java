package be.vdab.expo.tickets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class TicketStockTest {

    private TicketStock ticketStock;

    @Test
    void boekEenTicketType1WijzigtHetAantalJuniorDagTickets() {
        ticketStock = new TicketStock(1, 1);
        ticketStock.bestelEenTicket(1);
        assertThat(ticketStock.getJuniorDag()).isZero();
    }
    @Test
    void boekEenTicketType2WijzigtHetAantalSeniorDagTickets() {
        ticketStock = new TicketStock(1, 1);
        ticketStock.bestelEenTicket(2);
        assertThat(ticketStock.getSeniorDag()).isZero();
    }
    @Test
    void boekEenTicketType3WijzigtHetAantalJuniorDagEnSeniorDagTickets() {
        ticketStock = new TicketStock(1, 1);
        ticketStock.bestelEenTicket(3);
        assertThat(ticketStock.getJuniorDag()).isZero();
        assertThat(ticketStock.getSeniorDag()).isZero();
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 4})
    void boekEenTicketMetOnbestaandeTicketTypeMislukt(int ticketType) {
        ticketStock = new TicketStock(1,1);
        assertThatIllegalArgumentException().isThrownBy(()
                -> ticketStock.bestelEenTicket(ticketType));
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void boekEenTicketMetOnvoldoendeTicketsBeschikbaarMislukt(int ticketType) {
        ticketStock = new TicketStock(0, 0);
        assertThatExceptionOfType(OnvoldoendeTicketsBeschikbaarException.class).isThrownBy(
                () -> ticketStock.bestelEenTicket(ticketType));
    }
    @Test
    void boekEenTicketType3MetOnvoldoendeJuniorTicketsMislukt() {
        ticketStock = new TicketStock(0, 1);
        assertThatExceptionOfType(OnvoldoendeTicketsBeschikbaarException.class).isThrownBy(
                () -> ticketStock.bestelEenTicket(3));
    }
    @Test
    void boekEenTicketType3MetOnvoldoendeSeniorTicketsMislukt() {
        ticketStock = new TicketStock(1, 0);
        assertThatExceptionOfType(OnvoldoendeTicketsBeschikbaarException.class).isThrownBy(
                () -> ticketStock.bestelEenTicket(3));
    }
}
