package be.vdab.expo.tickets;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TicketStockRepository {
    private final JdbcClient jdbcClient;

    public TicketStockRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public TicketStock findAndLock() {
        String sql = """
                select juniorDag, seniorDag
                from tickets
                for update
                """;
        return jdbcClient.sql(sql)
                .query(TicketStock.class)
                .single();
    }
    public void update(TicketStock ticketStock) {
        String sql = """
                update tickets
                set juniorDag = ?, seniorDag = ?
                """;
        jdbcClient.sql(sql)
                .params(ticketStock.getJuniorDag(), ticketStock.getSeniorDag())
                .update();
    }
}
