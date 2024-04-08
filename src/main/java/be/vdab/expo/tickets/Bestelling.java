package be.vdab.expo.tickets;

public class Bestelling {
    private final long id;
    private final String naam;
    private final int ticketType;

    public Bestelling(long id, String naam, int ticketType) {
        if (naam.isBlank()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        if (ticketType < 1 || ticketType > 3) {
            throw new IllegalArgumentException("Ticket type moet 1, 2 of 3 zijn.");
        }
        this.id = id;
        this.naam = naam;
        this.ticketType = ticketType;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getTicketType() {
        return ticketType;
    }
}
