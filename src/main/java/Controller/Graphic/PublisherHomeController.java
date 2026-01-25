package Controller.Graphic;

import Controller.Logic.AuthController;
import Session.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PublisherHomeController {

    @FXML
    private ListView<String> notificationList;
    @FXML
    private Label publisherName;

    @FXML
    public void initialize() {
        // Simuliamo le notifiche delle vendite
        ObservableList<String> sales = FXCollections.observableArrayList(
                "✅ Venduta copia di 'Cyber Adventure' a user99 - 2 minuti fa",
                "✅ Venduta copia di 'Space Warrior' a pro_gamer - 15 minuti fa",
                "✅ Venduta copia di 'Cyber Adventure' a luca_88 - 1 ora fa",
                "✅ Venduta copia di 'Old School RPG' a player_one - 3 ore fa"
        );
        notificationList.setItems(sales);
        publisherName.setText(SessionManager.getInstance().getLoggedUser().getUsename());
    }

    @FXML
    private void openAddGameDialog(Event event) {
        SceneManager.swichScene(event, "/view/AddNewGame.fxml");
    }

    @FXML
    private void showDashboard(Event event) {
        System.out.println("Ricarica dashboard...");
    }

    @FXML
    private void showMyGames(Event event) {
        SceneManager.swichScene(event, "/view/PublisherGame.fxml");
    }

    @FXML
    private void handleLogout(Event event) {
        AuthController auth = new AuthController();
        auth.logoutUser();
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}