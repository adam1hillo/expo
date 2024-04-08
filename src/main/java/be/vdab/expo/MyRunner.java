package be.vdab.expo;

import be.vdab.expo.tickets.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    private final BestellingService bestellingService;

    public MyRunner(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Naam:");
        String naam = scanner.nextLine();
        System.out.println("Ticket type (1, 2, 3):");
        try {
            int ticketType = Integer.parseInt(scanner.nextLine());
            Bestelling bestelling = new Bestelling(0, naam, ticketType);
            System.out.println("Bestelling goedgekeurd, id: " + bestellingService.bestelEenTicket(bestelling));
        } catch (NumberFormatException ex) {
            System.err.println("Onjuiste format van ticket type. Het type moet een getal zijn");
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        } catch (OnvoldoendeTicketsBeschikbaarException ex) {
            System.err.println("Er zijn onvoldoende tickets beschikbaar");
        }

    }
}
