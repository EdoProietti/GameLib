package Controller.Graphic;

import Bean.UserBean;
import Controller.Logic.AuthController;
import Model.User.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Exception.*;
import javafx.scene.paint.Color;

public class LoginController {

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML private Label errorMessage;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try{
            AuthController authController = new AuthController();
            UserBean userBean = new UserBean(username, password);
            if(authController.loginUser(userBean).equals(UserType.PUBLISHER)){
                SceneManager.swichScene(event, "/view/PublisherHome.fxml");
            }
            else{
                SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
            }
        }catch(UserNotFoundException e){
            showError(e.getMessage());
        } catch (PasswordNotEquals e){
            showError(e.getMessage());
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

    private void showError(String message) {
        errorMessage.setTextFill(Color.RED);
        errorMessage.setText(message);
    }
}