package be.vdab.expo.tickets;

public class TicketStock {

    private int juniorDag;
    private int seniorDag;

    public TicketStock(int juniorDag, int seniorDag) {
        this.juniorDag = juniorDag;
        this.seniorDag = seniorDag;
    }

    public void bestelEenTicket (int ticketType) {
        if (ticketType < 1 || ticketType > 3) {
            throw new IllegalArgumentException("Ticket type moet 1, 2 of 3 zijn.");
        }
        if (ticketType == 1) {
            if (juniorDag < 1) {
                throw new OnvoldoendeTicketsBeschikbaarException();
            }
            juniorDag -= 1;
        }
        if (ticketType == 2) {
            if (seniorDag < 1) {
                throw new OnvoldoendeTicketsBeschikbaarException();
            }
            seniorDag -= 1;
        }
        if (ticketType == 3) {
            if (juniorDag < 1 || seniorDag < 1) {
                throw new OnvoldoendeTicketsBeschikbaarException();
            }
            juniorDag -= 1;
            seniorDag -= 1;
        }
    }

    public int getJuniorDag() {
        return juniorDag;
    }

    public int getSeniorDag() {
        return seniorDag;
    }
}
