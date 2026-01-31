package controller.cli;

import java.util.Scanner;

public class HomeCLI {

    public void startHomepage() {
        boolean exit = false;

        while(!exit){
            System.out.println("-----------------------------------");
            System.out.println("Benvenuto sulla Homepage di GameLib");
            System.out.println("1) Cerca Giochi");
            System.out.println("2) Login");
            System.out.println("0) Esci");
            System.out.println("Seleziona un'opzione: ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    SearchCLI search = new SearchCLI();
                    search.displaySearchCLI();
                    break;
                case "2":
                    LoginCLI login = new LoginCLI();
                    login.displayLogin();
                    break;
                case "0":
                    System.out.println("Chiusura applicazione.");
                    exit = true;
                    break;
                default:
                    System.out.println("Inserisci un'opzione valida");
            }
        }
    }
}