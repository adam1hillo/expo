package be.vdab.expo.tickets;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class BestellingTest {

    @Test
    void bestellingDieLukt() {
        new Bestelling(5, "test1", 1);
    }

    @Test
    void deNaamMagNietLeegZijn() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Bestelling(5, "", 1));
    }
    @Test
    void deNaamMagNietEnkelBlankSPaceBevatten() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Bestelling(5, " ", 1));
    }
    @Test
    void deNaamMagNietNullZijn() {
        assertThatNullPointerException().isThrownBy(() -> new Bestelling(5, null, 1));
    }
    @Test
    void ticketTypeMagNietKleinerDan1Zijn() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Bestelling(5, "test1", 0));
    }
    @Test
    void ticketTypeMagNietGroterDan3Zijn() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Bestelling(5, "test1", 4));
    }
}
