package be.vdab.expo.tickets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BestellingService.class, BestellingRepository.class, TicketStockRepository.class})
public class BestellingServiceTest {

    private final JdbcClient jdbcClient;

    private final BestellingService bestellingService;
    private static final String TICKETS_TABLE = "tickets";
    private static final String BESTELLINGEN_TABLE = "bestellingen";

    public BestellingServiceTest(JdbcClient jdbcClient, BestellingService bestellingService) {
        this.jdbcClient = jdbcClient;
        this.bestellingService = bestellingService;
    }

    @Test
    void bestelEenTicketVoegtEenBestellingToeEnWijzigdTicketStock() {
        int juniorDag = jdbcClient.sql("select juniorDag from tickets").query(Integer.class).single();
        int seniorDag = jdbcClient.sql("select seniorDag from tickets").query(Integer.class).single();
        bestellingService.bestelEenTicket(new Bestelling(0, "test1", 3));
        assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, BESTELLINGEN_TABLE,
                "naam = 'test1' and ticketType = 3")).isOne();
        assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, TICKETS_TABLE,
                "juniorDag = " + (juniorDag-1) + " and seniorDag = " + (seniorDag - 1))).isOne();
    }
}
