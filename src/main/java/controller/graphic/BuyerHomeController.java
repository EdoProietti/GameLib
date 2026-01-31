package controller.graphic;

import controller.logic.AuthController;
import session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class BuyerHomeController {
    @FXML
    private FlowPane featuredGamesPane;
    @FXML
    private Label userName;

    @FXML
    public void initialize() {
        System.out.println("Home Utente inizializzata.");
        userName.setText(AuthController.getLoggedUserBean().getUsername());
    }

    @FXML
    private void goToStore(MouseEvent event) {
        SceneManager.swichScene(event, "/view/Search.fxml");
    }

    @FXML
    private void goToLibrary(MouseEvent event) {
        SceneManager.swichScene(event, "/view/BuyerLibrary.fxml");
    }

    @FXML
    private void goToCart(ActionEvent event) {
        SceneManager.swichScene(event, "/view/ShoppingCartPage.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        AuthController auth = new AuthController();
        auth.logoutUser();
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}
