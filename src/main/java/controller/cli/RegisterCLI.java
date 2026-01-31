package controller.cli;

import bean.UserBean;
import controller.logic.AuthController;
import exception.UserFoundException;
import model.user.UserType;

import java.util.Scanner;

public class RegisterCLI {
    private final Scanner scanner = new Scanner(System.in);

    public void displayRegister() {
        boolean back = false;
        while (!back) {
            System.out.println("1) Crea account.");
            System.out.println("2) Torna al login.");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1: createAccount(); break;
                case 2: back = true; break;
                default: System.out.println("Inserisci un valore valido"); break;
            }
        }
    }

    private void createAccount(){
        System.out.println("      CREA ACCOUNT");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        String password = "";
        boolean passwordsMatch = false;
        String confirm;
        while (!passwordsMatch) {
            System.out.print("Password: ");
            password = scanner.nextLine();
            System.out.print("Conferma Password: ");
            confirm = scanner.nextLine();
            if(password.isEmpty() || confirm.isEmpty()){
                System.out.println("Inserisci una password.");
            } else if (password.equals(confirm)) {
                passwordsMatch = true;
                System.out.print("Vuoi registrarti come Publisher? (s/n): ");
                boolean isPublisher = scanner.nextLine().equalsIgnoreCase("s");
                executeRegistration(username, password, confirm, isPublisher);
            } else {
                System.out.println("Le password non coincidono.");
            }
        }
    }

    private void executeRegistration(String user, String pass, String confirmPass, boolean publisher) {
        System.out.println("\n[INFO] Elaborazione registrazione...");
        UserType type;
        if(publisher){
            type = UserType.PUBLISHER;
        } else{
            type = UserType.BUYER;
        }
        UserBean userBean = new UserBean(user, pass, confirmPass, type);
        AuthController authController = new AuthController();
        try {
            authController.registerNewUser(userBean);
            System.out.println("Account creato con successo per: " + user);
            System.out.println("Premi INVIO per tornare al Login.");
            scanner.nextLine();
        }catch(UserFoundException e){
            System.out.println(e.getMessage());
        }
    }
}


