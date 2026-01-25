package Controller.Graphic;

import Bean.UserBean;
import Controller.Logic.AuthController;
import Model.User.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import Exception.*;

import java.util.concurrent.TimeUnit;

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
        UserBean userBean;

        if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            showError("Errore: Compila tutti i campi!");
            return;
        }
        if(isPublisher.isSelected()){
            userBean = new UserBean(user, pass, confirm, UserType.PUBLISHER);
        }
        else{
            userBean = new UserBean(user, pass, confirm, UserType.BUYER);
        }


        try{
            userBean.passwordAreEqual();
            AuthController authController = new AuthController();
            authController.registerNewUser(userBean);
            showSuccessPopup();
            SceneManager.swichScene(event, "/view/Login.fxml");
        } catch(UserFoundException | PasswordMissmatch e) {
            showError(e.getMessage());
        }
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

    private void showSuccessPopup() {
        // creo alert di tipo informazione
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrazione Completata");
        alert.setHeaderText("Benvenuto su GameLib!");
        alert.setContentText("Il tuo account è stato creato con successo. Ora puoi effettuare il login.");

        // Personalizzazione dell'alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #2a2a3d;");
        dialogPane.lookupAll(".label").forEach(node -> node.setStyle("-fx-text-fill: white;"));

        // Mostriamo il popup.
        alert.showAndWait();
    }
}
