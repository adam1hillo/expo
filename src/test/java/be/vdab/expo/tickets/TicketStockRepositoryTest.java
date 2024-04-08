package be.vdab.expo.tickets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(TicketStockRepository.class)
class TicketStockRepositoryTest {

    private final TicketStockRepository ticketStockRepository;
    private final JdbcClient jdbcClient;
    private static final String TICKETS_TABLE = "tickets";

    TicketStockRepositoryTest(TicketStockRepository ticketStockRepository, JdbcClient jdbcClient) {
        this.ticketStockRepository = ticketStockRepository;
        this.jdbcClient = jdbcClient;
    }
    @Test
    void findAndLockVindtAantalBeschikbaareTickets() {
        var aantalReords = JdbcTestUtils.countRowsInTable(jdbcClient, TICKETS_TABLE);
        assertThat(aantalReords).isOne();
        int juniorDag = jdbcClient.sql("select juniorDag from tickets").query(Integer.class).single();
        int seniorDag = jdbcClient.sql("select seniorDag from tickets").query(Integer.class).single();
        assertThat(ticketStockRepository.findAndLock().getJuniorDag()).isEqualTo(juniorDag);
        assertThat(ticketStockRepository.findAndLock().getSeniorDag()).isEqualTo(seniorDag);

    }
    @Test
    void updateWijzigtJuniorDagEnSeniorDagTickets() {
        TicketStock ticketStock = new TicketStock(Integer.MAX_VALUE,Integer.MAX_VALUE);
        ticketStockRepository.update(ticketStock);
        int juniorDag = jdbcClient.sql("select juniorDag from tickets").query(Integer.class).single();
        int seniorDag = jdbcClient.sql("select seniorDag from tickets").query(Integer.class).single();
        assertThat(juniorDag).isEqualTo(Integer.MAX_VALUE);
        assertThat(seniorDag).isEqualTo(Integer.MAX_VALUE);
        ticketStock = new TicketStock(1,1);
        ticketStockRepository.update(ticketStock);
        juniorDag = jdbcClient.sql("select juniorDag from tickets").query(Integer.class).single();
        seniorDag = jdbcClient.sql("select seniorDag from tickets").query(Integer.class).single();
        assertThat(juniorDag).isOne();
        assertThat(seniorDag).isOne();
    }
}
