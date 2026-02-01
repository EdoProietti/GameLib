package controller.cli;

import bean.GameBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import exception.UserNotLogged;
import exception.UserTypeMissmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerLibraryCLI {
    private final Scanner scanner = new Scanner(System.in);
    private List<GameBean> myGames = new ArrayList<>();

    public void displayLibrary() {
        loadUserLibrary();

        boolean back = false;
        while (!back) {
            System.out.println("         LA MIA LIBRERIA");
            if (myGames.isEmpty()) {
                System.out.println("La tua libreria è vuota. Corri allo Store!");
            } else {
                for (int i = 0; i < myGames.size(); i++) {
                    System.out.println(i+1 + ". " + myGames.get(i).toString());
                }
            }
            System.out.print("0. Torna allo Store\nScelta: ");
            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                back = true;
            }
        }
    }

    private void loadUserLibrary() {
        BuyGameController buy = new BuyGameController();
        try {
            myGames = buy.getLibraryItems();
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
    }
}