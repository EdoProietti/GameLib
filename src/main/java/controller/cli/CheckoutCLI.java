package controller.cli;

import bean.PaymentBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import exception.UserNotLogged;
import exception.UserTypeMissmatch;

import java.util.Scanner;
import java.io.Console;

public class CheckoutCLI {
    private final Scanner scanner = new Scanner(System.in);

    public void handleCheckout() {
        System.out.println("\n" + "=".repeat(40));
        System.out.print("          CHECKOUT - PAGAMENTO\n[Digitare 'b' per tornare al carrello]\nIntestatario Carta (Nome Cognome): ");
        String holder = scanner.nextLine();
        if (holder.equalsIgnoreCase("b")) return;
        System.out.print("Numero Carta (16 cifre): ");
        String cardNumber = scanner.nextLine();
        System.out.print("Scadenza (MM/AA): ");
        String expiry = scanner.nextLine();
        String cvv = readSecureCVV();
        confirmPayment(holder, cardNumber, expiry, cvv);
    }

    private String readSecureCVV() {
        // nascondiamo il cvv con questo metodo.
        Console console = System.console();
        if (console != null) {
            char[] cvvArray = console.readPassword("CVV (3 cifre): ");
            return new String(cvvArray);
        } else {
            System.out.print("CVV (3 cifre): ");
            return scanner.nextLine();
        }
    }

    private void confirmPayment(String holder, String num, String exp, String cvv) {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("Riepilogo Inserimento:\nTitolare: " + holder);
        String maskedCard = "**** **** **** " + (num.length() > 4 ? num.substring(num.length() - 4) : "****");
        System.out.println("Carta: " + maskedCard+"\nConfermare il pagamento definitivo? (s/n): ");

        if (scanner.nextLine().equalsIgnoreCase("s")) {
            PaymentBean pay = new PaymentBean();
            pay.setNameSurname(holder);
            pay.setCardNumber(num);
            pay.setExpirationDate(exp);
            pay.setCvv(cvv);
            BuyGameController controller = new BuyGameController();
            try {
                controller.checkout();
                System.out.println("Pagamento autorizzato!");
            } catch (UserNotLogged e) {
                System.out.println(e.getMessage());
                LoginCLI login = new LoginCLI();
                login.displayLogin();
            } catch (UserTypeMissmatch e) {
                System.out.println(e.getMessage());
                AuthController auth = new AuthController();
                auth.logoutUser();
                LoginCLI login = new LoginCLI();
                login.displayLogin();
            }
        } else {
            System.out.println("Il pagamento è stato interrotto.");
        }
    }
}