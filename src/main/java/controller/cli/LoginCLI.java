package controller.cli;

import bean.UserBean;
import controller.logic.AuthController;
import exception.PasswordNotEquals;
import exception.UserNotFoundException;
import model.user.UserType;

import java.util.Scanner;
import java.io.Console;

public class LoginCLI {

    private final Scanner scanner = new Scanner(System.in);

    public void displayLogin() {
        boolean back = false;
        while (!back) {
            System.out.println("1) Login");
            System.out.println("2) Register new account");
            System.out.println("3) Quit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: startLogin(); break;
                case 2: handleRegister(); break;
                case 3: back = true; break;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }

    private void startLogin() {
        System.out.println("      LOGIN A GAMELIB");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        String password = readPassword();
        executeLogin(username, password);
    }

    private String readPassword() {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword("Password: ");
            return new String(passwordArray);
        } else {
            System.out.print("Password: ");
            return scanner.nextLine();
        }
    }

    private void executeLogin(String user, String pass) {
        System.out.println("\n[INFO] Tentativo di accesso in corso...");

        if (user.isEmpty() || pass.isEmpty()) {
            System.out.println("[ERRORE] Username o password mancanti!" + "\033[0m");
        } else {
            try{
                AuthController auth = new AuthController();
                UserType type = auth.loginUser(new UserBean(user, pass));
                System.out.println("Accesso eseguito con successo! Benvenuto, " + user + ".");
                if(type.equals(UserType.PUBLISHER)){
                    PublisherHomeCLI publisher = new PublisherHomeCLI();
                    publisher.display();
                }else{
                    BuyerHomeCLI buyer = new BuyerHomeCLI();
                    buyer.displayBuyerHome();
                }
            }catch(UserNotFoundException e){
                System.out.println(e.getMessage());
            }catch(PasswordNotEquals e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void handleRegister() {
        System.out.println("Reindirizzamento alla pagina di registrazione...");
        RegisterCLI registerCLI = new RegisterCLI();
        registerCLI.displayRegister();
    }
}