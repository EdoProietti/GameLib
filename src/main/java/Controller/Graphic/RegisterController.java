package Controller.Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class RegisterController {

    @FXML private TextField userField;
    @FXML private TextField emailField;
    @FXML private PasswordField passField;
    @FXML private PasswordField confirmPassField;
    @FXML private Label statusLabel;
    @FXML private CheckBox isPublisher;

    @FXML
    public void initialize(){
        isPublisher.setSelected(false);
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String user = userField.getText();
        String email = emailField.getText();
        String pass = passField.getText();
        String confirm = confirmPassField.getText();

        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showError("Errore: Compila tutti i campi!");
            return;
        }

        if (!pass.equals(confirm)) {
            showError("Le password non coincidono!");
            return;
        }
        if(isPublisher.isSelected()){
            // Mandiamo la registrazione dell'utente publisher.
        }

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Account creato con successo!");
        System.out.println("Nuovo utente registrato: " + user);

        // Opzionale: torna al login dopo 2 secondi o cliccando un tasto
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        SceneManager.swichScene(event, "/view/Login.fxml");
    }

    private void showError(String message) {
        statusLabel.setTextFill(Color.RED);
        statusLabel.setText(message);
    }

    @FXML
    private void handleBack(ActionEvent event){
        SceneManager.swichScene(event, "/view/Homepage.fxml");
    }
}
