package controller.cli;

import bean.CartBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import exception.UserNotLogged;
import exception.UserTypeMissmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartCLI {
    private final Scanner scanner = new Scanner(System.in);

    private List<CartBean> cartItems = new ArrayList<>();

    public void display() {
        boolean back = false;
        while (!back) {

            cartItems = getCartItems();
            if (cartItems.isEmpty()) {
                System.out.println("\n      [ Il tuo carrello è vuoto ]");
                System.out.println("   Torna allo store per aggiungere giochi.");
            } else {
                printTable();
                printSummary();
            }

            System.out.println("\nOpzioni:");
            System.out.println("1. Rimuovi un gioco");
            System.out.println("2. PROCEDI AL PAGAMENTO");
            System.out.println("0. < Continua lo Shopping");
            System.out.print("\nScelta: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleRemove();
                case "2" -> handleCheckout();
                case "0" -> back = true;
                default -> System.out.println("Scelta non valida.");
            }
        }
    }

    private List<CartBean> getCartItems(){
        BuyGameController buy = new BuyGameController();
        return buy.getCartItems();
    }

    private void printTable() {
        System.out.println(" INDICE | TITOLO | PIATTAFORMA | PREZZO  ");
        System.out.println("-".repeat(60));

        for (int i = 0; i < cartItems.size(); i++) {
            CartBean g = cartItems.get(i);
            System.out.println(i+1+". "+g.getGameTitle()+"|"+g.getPlatform()+"|"+g.getPrice());
        }
    }

    private void printSummary() {
        double subtotal = cartItems.stream().mapToDouble(g -> Double.parseDouble(g.getPrice())).sum();
        System.out.println("RIEPILOGO CARRELLO");
        System.out.printf("TOTALE: € %.2f", subtotal);
    }

    private void handleRemove() {
        System.out.print("Inserisci l'ID del gioco da rimuovere: ");
        try {
            int id = Integer.parseInt(scanner.nextLine()) - 1;
            if (id >= 0 && id < cartItems.size()) {
                BuyGameController buy = new BuyGameController();
                buy.removeCartItem(cartItems.get(id));
                System.out.println("Rimosso: " + cartItems.remove(id));
            } else {
                System.out.println("ID non valido.");
            }
        } catch(UserTypeMissmatch e){
            System.out.println(e.getMessage());
            AuthController auth = new AuthController();
            auth.logoutUser();
            LoginCLI l = new LoginCLI();
            l.displayLogin();
        } catch(UserNotLogged e){
            System.out.println(e.getMessage());
            LoginCLI l = new LoginCLI();
            l.displayLogin();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleCheckout() {
        if (cartItems.isEmpty()) return;
        System.out.println("Caricamento pagina di checkout");
        CheckoutCLI check = new CheckoutCLI();
        check.handleCheckout();
        cartItems.clear();
    }
}