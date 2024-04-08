package be.vdab.expo.tickets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BestellingRepository.class)
public class BestellingRepositoryTest {
    private final BestellingRepository bestellingRepository;
    private final JdbcClient jdbcClient;
    private static final String BESTELLINGEN_TABLE = "bestellingen";

    public BestellingRepositoryTest(BestellingRepository bestellingRepository, JdbcClient jdbcClient) {
        this.bestellingRepository = bestellingRepository;
        this.jdbcClient = jdbcClient;
    }
    @Test
    void createVoegtEenBestellingToe() {
        Bestelling bestelling = new Bestelling(0, "test1", 1);
        long id = bestellingRepository.create(bestelling);
        assertThat(id).isPositive();
        var aantalToeGevoegdeRecords = JdbcTestUtils.countRowsInTableWhere(jdbcClient, BESTELLINGEN_TABLE,
                "naam = 'test1' and ticketType = 1 and id = " + id);
        assertThat(aantalToeGevoegdeRecords).isOne();
    }

}
