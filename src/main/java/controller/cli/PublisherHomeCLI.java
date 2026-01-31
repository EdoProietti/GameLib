package controller.cli;

import bean.GameBean;
import bean.NotifyBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import controller.logic.ManageGameController;
import exception.GameExist;
import exception.UserTypeMissmatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublisherHomeCLI {
    private final Scanner scanner = new Scanner(System.in);

    private String publisherName;
    private BigDecimal grossing = BigDecimal.ZERO;
    private int copiesSold = 0;
    private List<NotifyBean> notifications = new ArrayList<>();

    public void display() {
        // carico le notifiche del publisher.
        loadNotifications();

        boolean logout = false;
        while (!logout) {

            System.out.println("\nMENU AZIONI:");
            System.out.println("1. Inserisci Nuovo Gioco");
            System.out.println("2. Mostra I Miei Giochi");
            System.out.println("3. Mostra Notifiche");
            System.out.println("0. Logout");
            System.out.print("\nSeleziona: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> openAddGameDialog();
                case "2" -> showMyGames();
                case "3" -> printDashboard();
                case "0" -> logout = true;
                default -> System.out.println("Opzione non valida.");
            }
        }
    }

    private void printDashboard() {
        System.out.println("   AREA PUBLISHER: " + publisherName);
        System.out.printf("Totale Guadagni: € %.2f \n", grossing);
        System.out.printf("Copie Totali Vendute: %d unità \n", copiesSold);

        System.out.println("NOTIFICHE VENDITE:");
        if (notifications.isEmpty()) {
            System.out.println("   Nessuna nuova notifica.");
        } else {
            for(NotifyBean n:notifications){
                System.out.println("Copie vendute di: "+n.getGameTitle()+" : "+n.getGameCopySold());
            }
        }
        System.out.println("Fine notifiche vendite.");
    }

    private void loadNotifications() {
        publisherName = AuthController.getLoggedUser().getUsername();
        BuyGameController b = new BuyGameController();
        notifications = b.getPublisherNotification();
        for(NotifyBean n : notifications){
            copiesSold += n.getGameCopySold();
            BigDecimal subtotal = n.getPrice().multiply(BigDecimal.valueOf(n.getGameCopySold()));
            grossing = grossing.add(subtotal);
        }
    }

    private void openAddGameDialog() {
        System.out.println("\n--- INSERISCI NUOVO GIOCO ---");
        System.out.print("Titolo: ");
        String title = scanner.nextLine();
        System.out.print("Prezzo: ");
        String stringPrice = scanner.nextLine();
        double price = Double.parseDouble(stringPrice.replace(",","."));
        System.out.print("Genere (Action, RPG, FPS, SPORT): ");
        String genre = scanner.nextLine();
        System.out.print("Piattaforma (PC, PS5, XBOX, SWITCH): ");
        String platform = scanner.nextLine();
        GameBean g = new GameBean(title, genre, platform, BigDecimal.valueOf(price));
        ManageGameController m = new ManageGameController();
        try {
            m.addNewGame(g);
            System.out.println("Gioco aggiunto con successo!");
        } catch (UserTypeMissmatch e) {
            System.out.println(e.getMessage());
            AuthController a = new AuthController();
            a.logoutUser();
            LoginCLI l = new LoginCLI();
            l.displayLogin();
        } catch (GameExist e) {
            System.out.println(e.getMessage());
        }
    }

    private void showMyGames() {
        System.out.println("\n--- I TUOI TITOLI ---");
        ManageGameController mg = new ManageGameController();
        try {
            List<GameBean> games = mg.getGameList();
            for(GameBean g : games){
                System.out.println("Titolo: "+g.getTitle()+
                        " Prezzo: "+g.getPrice()+
                        " Piattaforma: "+g.getPlatform()+
                        " Genere: "+g.getGenre()
                );
            }
            System.out.println("Fine Giochi inseriti.");
        } catch (UserTypeMissmatch e) {
            System.out.println(e.getMessage());
            AuthController auth = new AuthController();
            auth.logoutUser();
            LoginCLI login = new LoginCLI();
            login.displayLogin();
        }
    }
}