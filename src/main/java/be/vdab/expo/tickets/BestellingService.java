package be.vdab.expo.tickets;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BestellingService {
    private final BestellingRepository bestellingRepository;
    private final TicketStockRepository ticketStockRepository;

    public BestellingService(BestellingRepository bestellingRepository, TicketStockRepository ticketStockRepository) {
        this.bestellingRepository = bestellingRepository;
        this.ticketStockRepository = ticketStockRepository;
    }

    @Transactional
    public long bestelEenTicket(Bestelling bestelling) {
        TicketStock ticketStock = ticketStockRepository.findAndLock();
        ticketStock.bestelEenTicket(bestelling.getTicketType());
        ticketStockRepository.update(ticketStock);
        return bestellingRepository.create(bestelling);
    }
}
