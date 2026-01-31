package controller.cli;

import bean.UserBean;
import controller.logic.AuthController;

import java.util.Scanner;

public class BuyerHomeCLI {
    private final Scanner scanner = new Scanner(System.in);

    public void displayBuyerHome() {
        boolean logout = false;
        while (!logout) {
            UserBean loggedUser = AuthController.getLoggedUserBean();
            System.out.println("Benvenuto nel tuo GameLib, "+loggedUser.getUsername());
            System.out.println("1.SCOPRI GIOCHI (Store)");
            System.out.println("2.APRI LIBRERIA (I tuoi giochi)");
            System.out.println("3.CARRELLO");
            System.out.println("0. ESCI (Logout)");
            System.out.print("\nSeleziona un'area: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> goToStore();
                case "2" -> goToLibrary();
                case "3" -> goToCart();
                case "0" -> {
                    handleLogout();
                    logout = true;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }
    }

    private void goToStore() {
        SearchCLI search = new SearchCLI();
        search.displaySearchCLI();
    }

    private void goToLibrary() {
        System.out.println("Apertura Libreria...");
        BuyerLibraryCLI buyer = new BuyerLibraryCLI();
        buyer.displayLibrary();
    }

    private void goToCart() {
        System.out.println("Apertura Carrello...");
        ShoppingCartCLI shoppingCart = new ShoppingCartCLI();
        shoppingCart.display();
    }

    private void handleLogout() {
        AuthController auth = new AuthController();
        auth.logoutUser();
    }
}