package Controller.Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class BuyerHomeController {
    @FXML
    private FlowPane featuredGamesPane;

    @FXML
    public void initialize() {
        // Qui potresti caricare alcune card di esempio nel featuredPane
        System.out.println("Home Utente inizializzata.");
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
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}
