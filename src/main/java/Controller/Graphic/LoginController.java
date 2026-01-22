package Controller.Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML private Label errorMessage;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Logica di autenticazione (per ora, un esempio molto semplice)
        if (username.equals("test") && password.equals("password")) {
            errorMessage.setTextFill(Color.GREEN);
            errorMessage.setText("Accesso effettuato! Benvenuto " + username);
            System.out.println("Login riuscito per: " + username);
            // Qui potresti navigare alla dashboard utente o a un'altra pagina
            SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");

        } else {
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Username o password errati.");
            System.out.println("Tentativo di login fallito per: " + username);
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        System.out.println("Navigazione alla pagina di registrazione...");
        SceneManager.swichScene(event, "/view/Register.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/homepage.fxml");
    }
}